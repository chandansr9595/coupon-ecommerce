import { combineReducers } from 'redux';
import ProductReducer from './productReducer';
import CategoryReducer from './categoryReducer';

export default combineReducers({
    products: ProductReducer,
    category: CategoryReducer
});