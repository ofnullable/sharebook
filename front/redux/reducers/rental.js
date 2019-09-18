import produce from 'immer';

import { RENTAL } from '@redux/actionTypes';

const initial = {
  histories: {
    data: [],
    isLoading: false,
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      default:
        break;
    }
  });
};
