package com.chandan.rc.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Products")
public class Product {
    @Id
    private String id;
    private String productName;
//    @Indexed(direction = IndexDirection.ASCENDING)
    private String categoryId;
    private Integer price;

    public Product(String productName, String categoryId, Integer price) {
        this.productName = productName;
        this.categoryId = categoryId;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
