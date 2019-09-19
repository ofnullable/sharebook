import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { CATEGORY } from '@redux/actionTypes';
import { loadCategoryListApi } from '@redux/api/category';
import { loadCategoryListSuccess, loadCategoryListFailure } from '@redux/actions/categoryActions';

export default function*() {
  yield all([fork(watchLoadCategoryListRequest)]);
}

function* watchLoadCategoryListRequest() {
  yield takeLatest(CATEGORY.LOAD_CATEGORY_LIST_REQUEST, loadCategoryList);
}
function* loadCategoryList() {
  try {
    const response = yield call(loadCategoryListApi);
    yield put(loadCategoryListSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadCategoryListFailure(e.response.data || e));
  }
}
