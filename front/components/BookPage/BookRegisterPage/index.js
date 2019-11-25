import React, { useState, useRef, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import { useInput } from '@utils/inputUtils';
import { uploadImageRequest, registerBookRequest } from '@redux/actions/registerActions';
import RegisterResultAlert from './RegisterResultAlert';

import { RegisterForm, BookImagePreview, ImageUploadButton, RegisterButton } from './index.styled';
import {
  BookImageWrapper,
  BookDetailWrapper,
} from '@components/BookPage/BookDetailPage/index.styled';
import { InputGroup } from '@styles/common';

const BookRegisterPage = () => {
  const { image, result, showAlert } = useSelector(state => state.register);
  const categoryList = useSelector(state => state.category.list);

  const [imageUploaded, setImageUploaded] = useState(false);
  const [imagePreviewUrl, setImagePreviewUrl] = useState('');
  const [categoryId, categoryHandler] = useInput(categoryList.data[0].id);
  const [title, titleHandler] = useInput();
  const [author, authorHandler] = useInput();
  const [publisher, publisherHandler] = useInput();
  const [description, descriptionHandler] = useInput();

  const dispatch = useDispatch();
  const fileRef = useRef();

  const clearImageData = () => {
    setImageUploaded(false);
    setImagePreviewUrl('');
    fileRef.current.value = '';
  };

  useEffect(() => {
    if (Object.keys(image.error).length) {
      clearImageData();
    }
  }, [image.error]);

  useEffect(() => {}, [result]);

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
      clearImageData();
    } else {
      alert('도서 이미지를 선택해주세요.');
    }
  };

  const handleImageSelect = () => {
    if (imageUploaded) {
      // remove image request or mark image url for remove
    }
    fileRef.current.click();
  };
  const handleImageDrop = e => {
    e.preventDefault();
    handleImageUpload(e);
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
    const file = e.target.files ? e.target.files[0] : e.dataTransfer.files[0];

    if (!file.type.startsWith('image/')) {
      alert('이미지 파일만 업로드 할 수 있습니다.');
      return;
    }

    makeImagePreview(file);
    setImageUploaded(true);

    const formFile = makeFormData(file);
    dispatch(uploadImageRequest(formFile));
  };

  const handleImageChange = e => {
    if (confirm('이미지를 변경하시겠습니까?')) {
      handleImageSelect();
    }
  };

  return (
    <RegisterForm onSubmit={handleRegister}>
      {showAlert && <RegisterResultAlert id={result.id} />}
      <input type='file' accept='image/*' ref={fileRef} onChange={handleImageUpload} hidden />
      <BookImageWrapper>
        {imagePreviewUrl ? (
          <div>
            <img src={imagePreviewUrl} />
            <i className='material-icons-outlined' onClick={handleImageChange}>
              close
            </i>
          </div>
        ) : (
          <BookImagePreview
            onClick={handleImageSelect}
            onDrop={handleImageDrop}
            onDragOver={preventDefaultEvent}
          >
            <span>Upload Book Image!</span>
          </BookImagePreview>
        )}

        <ImageUploadButton _color='primary' onClick={handleImageSelect} type='button'>
          Image Upload
        </ImageUploadButton>
      </BookImageWrapper>
      <BookDetailWrapper>
        <InputGroup>
          <label htmlFor='category'>Category</label>
          <select id='category' value={categoryId} onChange={categoryHandler} required>
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
            id='title'
            placeholder='토비의 스프링 3.1'
            value={title}
            onChange={titleHandler}
            required
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='author'>Author</label>
          <input
            type='text'
            id='author'
            placeholder='이일민'
            value={author}
            onChange={authorHandler}
            required
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='publisher'>Publisher</label>
          <input
            type='text'
            id='publisher'
            placeholder='에이콘출판사'
            value={publisher}
            onChange={publisherHandler}
            required
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='description'>Description</label>
          <textarea
            style={{ minHeight: '150px', width: '100%' }}
            type='text'
            id='description'
            value={description}
            onChange={descriptionHandler}
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
