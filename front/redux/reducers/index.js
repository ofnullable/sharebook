import { combineReducers } from 'redux';

import user from './user';
import book from './book';
import category from './category';
import lending from './lending';
import review from './review';
import register from './register';

export default combineReducers({ user, book, category, lending, review, register });
