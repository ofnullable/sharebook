import { CATEGORY } from '@redux/actionTypes';

export const loadCategoryListRequest = () => ({
  type: CATEGORY.LOAD_CATEGORY_LIST_REQUEST,
});
export const loadCategoryListSuccess = data => ({
  type: CATEGORY.LOAD_CATEGORY_LIST_SUCCESS,
  data,
});
export const loadCategoryListFailure = error => ({
  type: CATEGORY.LOAD_CATEGORY_LIST_FAILURE,
  error,
});
