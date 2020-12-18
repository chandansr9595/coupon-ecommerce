import {ACTIONS} from '../actions';

const CategoryReducer = (state={}, action) => {
    switch(action.type){
        case ACTIONS.FETCH_CATEGRY:
            return action.payload;
        default:
            return state;
    }
}


export default CategoryReducer;