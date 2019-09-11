import { combineReducers } from 'redux';

import user from './user';
import book from './book';
import category from './category';

export default combineReducers({ user, book, category });
