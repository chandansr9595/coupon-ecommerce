import React from 'react';
import { connect } from 'react-redux';
import {fetchProducts} from '../../actions';

interface IListProductsProps {
    fetchProducts: () => void,
    productWithDeals: [],
}


class ListProducts extends React.Component<IListProductsProps> {

    componentDidMount(){
        this.props.fetchProducts();
    }

    getProductPrice(price: number, deal: number){
        return price - (price*deal / 100);
    }

    renderHeader = () => {
        return (
            <div className="eachProduct">
                    <div className="productName boldText">
                        Product Name
                    </div>
                    <div className="productPriceActual boldText">
                        Actual Price
                    </div>
                    <div className="productPriceActual boldText">
                        Current Price
                    </div>
                    <div className="dealPercentage boldText">
                        Deal
                    </div>
                </div>
        )
    }

    renderProducts = (products: []) => {
        return products ? products.map((productDetails: {
            product: {
                categoryId: string
                id: string
                price: number
                productName: string
            },
            dealPercentage: number,
            dealTimout: number
        }) => {
            return (
                <div key={productDetails.product.id} className="eachProduct">
                    <div className="productName">
                        {productDetails.product.productName}
                    </div>
                    <div className="productPriceActual">
                        {productDetails.product.price} ₹
                    </div>
                    <div className="productPrice">
                        {
                            productDetails.dealPercentage > 0 ? (
                                <p className="price">{this.getProductPrice(productDetails.product.price, productDetails.dealPercentage)} ₹</p>
                            ) : (
                                <p className="price">{productDetails.product.price} ₹</p>
                            )
                        }
                    </div>
                    {
                        productDetails.dealPercentage > 0 ? (
                            <div className="dealPercentage">
                                {productDetails.dealPercentage}% off
                            </div>
                        ) : (
                            ""
                        )
                    }
                </div>
            )
        }) : (
            <div>Loading ...</div>
        );
    }

    render() {
        console.log(this.props.productWithDeals);
        return (
            <div className="contianer">
                <div className="productContainer">
                    {this.renderHeader()}
                    {this.renderProducts(this.props.productWithDeals)}
                </div>
            </div>
        );
    }
}
const mapStateToProps = (state: any) => {
    return {
        productWithDeals: state.products.productWithDeals
    }
}

export default connect(mapStateToProps, {
    fetchProducts
})(ListProducts);