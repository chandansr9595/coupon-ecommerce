import React from 'react';
import Timer from './showTimer/timer';
import ProductList from './listProducts/ListProducts';
import Header from '../components/Header';

const HomePage = () => {

    return (
        <div>
            <Header />
            <Timer />
            <ProductList />
        </div>
    );
}

export default HomePage;