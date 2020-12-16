package com.chandan.rc.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Deals")
public class Deals {
    @Id
    private String id;
    private int percentageOfOffer;
    @Indexed(direction = IndexDirection.ASCENDING)
    private String productId;
    private long validTill;

    public Deals(int percentageOfOffer, String productId, long validTill) {
        this.percentageOfOffer = percentageOfOffer;
        this.productId = productId;
        this.validTill = validTill;
    }

    public String getId() {
        return id;
    }

    public int getPercentageOfOffer() {
        return percentageOfOffer;
    }

    public void setPercentageOfOffer(int percentageOfOffer) {
        this.percentageOfOffer = percentageOfOffer;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getValidTill() {
        return validTill;
    }

    public void setValidTill(long validTill) {
        this.validTill = validTill;
    }
}
