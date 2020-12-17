import axios from 'axios';

const ProdcutsAPI = axios.create({
    baseURL: 'http://localhost:8080/products'
})

export default ProdcutsAPI;