import React, { useState, useRef } from 'react';

import { preventDefaultEvent } from '@utils/inputUtils';

const FileUploader = ({ StyledTag, defaultImage, handleUpload }) => {
  const [preview, setPreview] = useState(defaultImage);
  const fileRef = useRef();

  const makePreview = file => {
    const fileReader = new FileReader();

    fileReader.readAsDataURL(file);

    fileReader.onloadend = () => {
      setPreview(fileReader.result);
    };
  };

  const handleFileChange = e => {
    const file = e.target.files ? e.target.files[0] : e.dataTransfer.files[0];
    makePreview(file);
    handleUpload(e);
  };

  const handleSelect = () => {
    fileRef.current.click();
  };

  const handleDrop = e => {
    preventDefaultEvent(e);
    handleFileChange(e);
  };

  const handleReSelect = () => {
    if (confirm('이미지를 변경하시겠습니까?')) {
      setPreview('');
      handleSelect();
    }
  };

  return (
    <>
      <input type='file' accept='image/*' ref={fileRef} onChange={handleFileChange} hidden />
      {preview ? (
        <StyledTag className='uploaded' _preview={preview}>
          <i className='material-icons-outlined' onClick={handleReSelect}>
            close
          </i>
        </StyledTag>
      ) : (
        <StyledTag onClick={handleSelect} onDrop={handleDrop} onDragOver={preventDefaultEvent}>
          <span>Upload Your Image!</span>
        </StyledTag>
      )}
    </>
  );
};

export default FileUploader;
