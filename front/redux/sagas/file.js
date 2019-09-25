import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { FILE } from '@redux/actionTypes';
import { uploadImageApi } from '@redux/api/file';
import { uploadImageSuccess, uploadImageFailure } from '@redux/actions/fileActions';

export default function*() {
  yield all([fork(watchUploadImageRequest)]);
}

function* watchUploadImageRequest() {
  yield takeLatest(FILE.UPLOAD_IMAGE_REQUEST, uploadImage);
}
function* uploadImage({ file }) {
  try {
    const response = yield call(uploadImageApi, file);
    yield put(uploadImageSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(uploadImageFailure(e.response.data || e));
  }
}
