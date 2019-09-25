import { combineReducers } from 'redux';

import user from './user';
import book from './book';
import category from './category';
import rental from './rental';
import file from './file';

export default combineReducers({ user, book, category, rental, file });
