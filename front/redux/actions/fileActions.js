import { FILE } from '@redux/actionTypes';

export const uploadImageRequest = file => ({
  type: FILE.UPLOAD_IMAGE_REQUEST,
  file,
});
export const uploadImageSuccess = data => ({
  type: FILE.UPLOAD_IMAGE_SUCCESS,
  data,
});
export const uploadImageFailure = error => ({
  type: FILE.UPLOAD_IMAGE_FAILURE,
  error,
});
