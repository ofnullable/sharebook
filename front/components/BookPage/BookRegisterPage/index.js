import React, { useState, useRef, useEffect } from 'react';
import { useRouter } from 'next/router';
import { useDispatch, useSelector } from 'react-redux';

import { useInput } from '@utils/InputUtils';
import { uploadImageRequest, registerBookRequest } from '@redux/actions/registerActions';

import { RegisterForm, BookImagePreview, ImageUploadButton, RegisterButton } from './index.styled';
import {
  BookImageWrapper,
  BookDetailWrapper,
  BookInfoWrapper,
} from '@components/BookPage/BookDetailPage/index.styled';
import { InputGroup } from '@styles/common';

const BookRegisterPage = () => {
  const { image, result } = useSelector(state => state.register);
  const categoryList = useSelector(state => state.category.list);

  const [imageUploaded, setImageUploaded] = useState(false);
  const [imagePreviewUrl, setImagePreviewUrl] = useState('');
  const [categoryId, categoryHandler] = useInput(categoryList.data[0].id);
  const [title, titleHandler] = useInput();
  const [author, authorHandler] = useInput();
  const [publisher, publisherHandler] = useInput();
  const [description, descriptionHandler] = useInput();

  const dispatch = useDispatch();
  const router = useRouter();
  const fileRef = useRef();

  const cleanImageData = () => {
    setImageUploaded(false);
    setImagePreviewUrl('');
    fileRef.current.value = '';
  };

  //TODO: useEffect 사용하여 image upload 오류 시 처리
  useEffect(() => {
    if (Object.keys(image.error).length) {
      cleanImageData();
    }
  }, [image.error]);

  useEffect(() => {
    if (Object.keys(result.data).length) {
      router.back();
    } else if (Object.keys(result.error).length) {
    }
  }, [result]);

  const handleRegister = e => {
    e.preventDefault();
    if (image.url) {
      const registerData = {
        categoryId,
        title,
        author,
        publisher,
        description,
        imageUrl: image.url,
      };
      dispatch(registerBookRequest(registerData));
      cleanImageData();
    } else {
      alert('도서 이미지를 선택해주세요.');
    }
  };

  const handleImageSelect = () => {
    if (!imageUploaded) {
      fileRef.current.click();
    }
  };

  const makeImagePreview = file => {
    const fileReader = new FileReader();

    fileReader.readAsDataURL(file);

    fileReader.onloadend = () => {
      setImagePreviewUrl(fileReader.result);
    };
  };

  const makeFormData = file => {
    const formData = new FormData();
    formData.append('image', file);
    return formData;
  };

  const handleImageUpload = e => {
    const file = e.target.files[0];
    makeImagePreview(file);
    setImageUploaded(true);

    const formFile = makeFormData(file);
    dispatch(uploadImageRequest(formFile));
  };

  return (
    <RegisterForm onSubmit={handleRegister}>
      <input type='file' accept='image/*' hidden ref={fileRef} onChange={handleImageUpload} />
      <BookImageWrapper>
        {imagePreviewUrl ? (
          <img src={imagePreviewUrl} />
        ) : (
          <BookImagePreview onClick={handleImageSelect}>
            <span>Upload Book Image!</span>
          </BookImagePreview>
        )}

        {!imageUploaded && (
          <ImageUploadButton _color='primary' onClick={handleImageSelect} type='button'>
            Image Upload
          </ImageUploadButton>
        )}
      </BookImageWrapper>
      <BookDetailWrapper>
        <BookInfoWrapper>
          <InputGroup>
            <label htmlFor='category'>Category</label>
            <select
              name='categoryId'
              id='category'
              value={categoryId}
              onChange={categoryHandler}
              required
            >
              {categoryList.data.map(c => (
                <option key={c.name} value={c.id}>
                  {c.name}
                </option>
              ))}
            </select>
          </InputGroup>
          <InputGroup>
            <label htmlFor='title'>Title</label>
            <input
              type='text'
              name='title'
              id='title'
              value={title}
              onChange={titleHandler}
              required
            />
          </InputGroup>
          <InputGroup>
            <label htmlFor='author'>Author</label>
            <input
              type='text'
              name='author'
              id='author'
              value={author}
              onChange={authorHandler}
              required
            />
          </InputGroup>
          <InputGroup>
            <label htmlFor='publisher'>Publisher</label>
            <input
              type='text'
              name='publisher'
              id='publisher'
              value={publisher}
              onChange={publisherHandler}
              required
            />
          </InputGroup>
          <InputGroup>
            <label htmlFor='description'>Description</label>
            <textarea
              style={{ minHeight: '150px' }}
              type='text'
              name='description'
              id='description'
              value={description}
              onChange={descriptionHandler}
              required
            />
          </InputGroup>
        </BookInfoWrapper>
      </BookDetailWrapper>
      <RegisterButton _color='primary' type='submit'>
        등록하기
      </RegisterButton>
    </RegisterForm>
  );
};

export default BookRegisterPage;
