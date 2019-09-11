import produce from 'immer';

import { CATEGORY } from '@redux/actionTypes';

const initial = {
  list: {
    data: [],
    isLoading: false,
    error: {},
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case CATEGORY.LOAD_CATEGORY_LIST_REQUEST:
        draft.list.isLoading = true;
        draft.list.error = {};
        break;
      case CATEGORY.LOAD_CATEGORY_LIST_SUCCESS:
        draft.list.isLoading = false;
        draft.list.data = action.data;
        break;
      case CATEGORY.LOAD_CATEGORY_LIST_FAILURE:
        draft.list.isLoading = false;
        draft.list.error = action.error;

      default:
        break;
    }
  });
};
