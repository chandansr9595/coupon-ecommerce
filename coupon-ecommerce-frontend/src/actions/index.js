import ProductsAPI from '../apis/products';
import history from '../history';
import CategoryAPI from '../apis/categoryAPI';

export const fetchProducts = () => {
    return async (dispatch) => {
        const response = await ProductsAPI.get('/all');
        dispatch({
            type: ACTIONS.FETCH_PRODUCTS,
            payload: response.data
        });
    }
}

export const fetchCategories = () => {
    return async (dispatch) => {
        const response = await CategoryAPI.get('/all');
        dispatch({
            type: ACTIONS.FETCH_CATEGRY,
            payload: response.data
        });
    }
}

export const createProduct = (formData) => {
    console.log(formData);
    return async (dispatch) => {
        await ProductsAPI.post('', 
            {
                ...formData,
            }
        );
        dispatch({
            type: ACTIONS.ADD_PRODUCTS,
            payload: {
                formData
            }
        });
        history.push('/');
    }
}

export const ACTIONS = {
    FETCH_PRODUCTS: 'FETCH_PRODUCTS',
    ADD_PRODUCTS: 'ADD_PRODUCTS',
    FETCH_CATEGRY: 'FETCH_CATEGRY',
}