import { REGISTER } from '@redux/actionTypes';

export const uploadImageRequest = file => ({
  type: REGISTER.UPLOAD_IMAGE_REQUEST,
  file,
});
export const uploadImageSuccess = data => ({
  type: REGISTER.UPLOAD_IMAGE_SUCCESS,
  data,
});
export const uploadImageFailure = error => ({
  type: REGISTER.UPLOAD_IMAGE_FAILURE,
  error,
});

export const registerBookRequest = data => ({
  type: REGISTER.REGISTER_BOOK_REQUEST,
  data,
});
export const registerBookSuccess = data => ({
  type: REGISTER.REGISTER_BOOK_SUCCESS,
  data,
});
export const registerBookFailure = error => ({
  type: REGISTER.REGISTER_BOOK_FAILURE,
  error,
});