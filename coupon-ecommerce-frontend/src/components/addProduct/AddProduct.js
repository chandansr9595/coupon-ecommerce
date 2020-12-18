import React from 'react';
import { connect } from 'react-redux';

import history from '../../history';
import {fetchCategories, createProduct} from '../../actions'

class AddProdcut extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            productName: '',
            price: 0,
            categoryId: 'SelectCategory',
        }
    }

    componentDidMount() {
        this.props.fetchCategories();
    }

    validateForm = () => {
        var isError = false;
        if(this.state.productName.length === 0 ){
            isError = true
        }

        if(this.state.price <= 0 ){
            isError = true
        }

        if(this.state.categoryId === 'SelectCategory' ){
            isError = true
        }
        return isError;
    }

    handleSubmit = (event) => {
        event.preventDefault();
        if(this.validateForm()){
            alert('Please fill all the feilds')
            return;
        }
        this.props.createProduct(this.state);
    }
    
    renderCategory = () => {
        console.log(this.props.categories);
        return Object.keys(this.props.categories).length === 0 && this.props.categories.constructor === Object ? (
            ''
        ) : (
            this.props.categories.map((category) => {
                return <option key={category.id} value={category.id}>{category.categoryName}</option>
            })
        )
        
    }

    render() {
        console.log(this.props.categories)
        return this.props.categories ? (
            (
                <div className="ui container">
                    <form className="ui form"
                        onSubmit={this.handleSubmit}
                    >
                        <div className="field">
                            <label>Product Name</label>
                            <input 
                                type="text" 
                                name="Product-name" 
                                value={this.state.productName} 
                                placeholder="First Name" 
                                onChange={(event) => {
                                    this.setState({
                                        productName: event.target.value
                                    })
                                }}
                            />
                        </div>
                        <div className="field">
                            <label>Product Price</label>
                            <input 
                                type="number" 
                                name="Product-Price" 
                                value={this.state.price} 
                                placeholder="Last Name" 
                                onChange={(event) => {
                                    this.setState({
                                        price: event.target.value
                                    })
                                }}
                            />
                        </div>
                        <div className="field">
                            <label>Category</label>
                            <div className="ui select">
                                <select id="cars" name="cars" 
                                    onChange={(event) => {
                                        this.setState({
                                            categoryId: event.target.value
                                        })
                                    }}
                                    value={this.state.categoryId}
                                >
                                    <option value="SelectCategory">Select Category</option>
                                    {this.renderCategory()}
                                </select>
                            </div>
                        </div>
                        <button className="ui button negative" type="submit">Submit</button>
                        <button className="ui button primary" type="submit" onClick={() => {
                            history.push('/')
                        }}>Cancel</button>
                    </form>
                </div>
            )
        ) : (
            <div>Loading ...</div>
        )
        
    }
}

const mapStateToProps = (state) => {
    return {
        categories: state.category
    }
}

export default connect(mapStateToProps, {
    fetchCategories,
    createProduct
})(AddProdcut);