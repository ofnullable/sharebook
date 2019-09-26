import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { REGISTER } from '@redux/actionTypes';
import { uploadImageApi, registerBookApi } from '@redux/api/register';
import {
  uploadImageSuccess,
  uploadImageFailure,
  registerBookSuccess,
  registerBookFailure,
} from '@redux/actions/registerActions';

export default function*() {
  yield all([fork(watchUploadImageRequest), fork(watchRegisterBookRequest)]);
}

function* watchUploadImageRequest() {
  yield takeLatest(REGISTER.UPLOAD_IMAGE_REQUEST, uploadImage);
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

function* watchRegisterBookRequest() {
  yield takeLatest(REGISTER.REGISTER_BOOK_REQUEST, registerBook);
}
function* registerBook({ data }) {
  try {
    const response = yield call(registerBookApi, data);
    yield put(registerBookSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(registerBookFailure(e.response.data || e));
  }
}
