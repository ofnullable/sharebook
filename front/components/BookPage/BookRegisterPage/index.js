import React, { useState, useEffect, useCallback } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import ImageUploader from '@components/common/ImageUploader';
import ActionResultAlert from '@components/common/ActionResultAlert';
import { useForm, isEmptyObject, preventDefaultEvent } from '@utils';
import { clear, uploadImageRequest, registerBookRequest } from '@redux/actions/registerActions';

import { RegisterForm, BookImagePreview, RegisterButton } from './index.styled';
import {
  BookImageWrapper,
  BookDetailWrapper,
} from '@components/BookPage/BookDetailPage/index.styled';
import { InputGroup } from '@styles/common';

const initialForm = categoryList => ({
  categoryId: categoryList.data.length && categoryList.data[0].id,
  title: '',
  author: '',
  publisher: '',
  description: '',
});

const BookRegisterPage = () => {
  const { image, result } = useSelector(state => state.register);
  const categoryList = useSelector(state => state.category.list);

  const [showAlert, setShowAlert] = useState(false);
  const [registered, setRegistered] = useState(false);
  const { form, formHandler, resetForm } = useForm(initialForm(categoryList));

  const dispatch = useDispatch();

  useEffect(() => {
    return () => {
      dispatch(clear());
    };
  }, []);

  useEffect(() => {
    if (registered) {
      if (result.id) {
        resetForm();
      }
      if (result.id || !isEmptyObject(result.error)) {
        setShowAlert(true);
      }
      setRegistered(false);
    }
  }, [result.id, result.error]);

  const handleRegister = e => {
    preventDefaultEvent(e);

    const { categoryId, title, author, publisher, description } = form;
    const imageUri = image.uri;

    if (imageUri) {
      const registerData = {
        categoryId: categoryId || categoryList.data[0].id,
        title,
        author,
        publisher,
        description,
        imageUri,
      };

      dispatch(registerBookRequest(registerData));
      setRegistered(true);
    } else {
      alert('도서 이미지를 선택해주세요.');
    }
  };

  const handleImageUpload = useCallback(file => {
    const formData = new FormData();
    formData.append('image', file);

    dispatch(uploadImageRequest(formData));
  }, []);

  const closeAlert = useCallback(() => {
    setShowAlert(false);
    dispatch(clear());
  }, []);

  return (
    <RegisterForm onSubmit={handleRegister}>
      {showAlert && (
        <ActionResultAlert
          isSuccess={result.id}
          close={closeAlert}
          link={{ href: '/book/[id]', as: `/book/${result.id}` }}
        />
      )}
      <BookImageWrapper>
        <ImageUploader
          StyledTag={BookImagePreview}
          handleUpload={handleImageUpload}
          defaultImage={image.uri}
          clearPreview={registered && result.id}
        />
      </BookImageWrapper>
      <BookDetailWrapper>
        <InputGroup>
          <label htmlFor='categoryId'>Category</label>
          <select id='categoryId' value={form.categoryId} onChange={formHandler} required>
            {categoryList.data.map(category => (
              <option key={category.name} value={category.id}>
                {category.name}
              </option>
            ))}
          </select>
        </InputGroup>
        <InputGroup>
          <label htmlFor='title'>Title</label>
          <input
            type='text'
            id='title'
            placeholder='토비의 스프링 3.1'
            value={form.title}
            onChange={formHandler}
            required
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='author'>Author</label>
          <input
            type='text'
            id='author'
            placeholder='이일민'
            value={form.author}
            onChange={formHandler}
            required
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='publisher'>Publisher</label>
          <input
            type='text'
            id='publisher'
            placeholder='에이콘출판사'
            value={form.publisher}
            onChange={formHandler}
            required
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='description'>Description</label>
          <textarea
            style={{ minHeight: '150px', width: '100%' }}
            type='text'
            id='description'
            value={form.description}
            onChange={formHandler}
            required
          />
        </InputGroup>
      </BookDetailWrapper>
      <RegisterButton _color='primary' type='submit'>
        등록하기
      </RegisterButton>
    </RegisterForm>
  );
};

export default BookRegisterPage;
