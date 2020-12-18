import {ACTIONS} from '../actions';

const ProductReducer = (state={}, action) => {
    switch(action.type){
        case ACTIONS.FETCH_PRODUCTS:
            return action.payload;
        case ACTIONS.ADD_PRODUCTS:
            const newProduct = {
                dealPercentage: 0,
                dealTimout: 0,
                product: action.payload,
            }
            return {...state,  newProduct}
        default:
            return state;
    }
}


export default ProductReducer;