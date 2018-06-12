package com.bidding.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "request", catalog = "biddingdb")
public class Request {

    @Id
    @GeneratedValue
    @Column(name = "REQUEST_ID", unique = true, nullable = false)
    private Integer requestId;

    public static int SELL = 0;

    public static int BUY = 1;

    @Column(name = "QUALITY")
    private int quality;

    @Column(name = "PRODUCT")
    private String product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private User requester;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "COUNT")
    private int itemsCount;

    @Column(name = "TYPE")
    private int type;

    public Request() {
    }


    public Request(String product, User requester, int price, int itemsCount, int type, int quality) {
        this.product = product;
        this.setRequester(requester);
        this.price = price;
        this.itemsCount = itemsCount;
        this.type = type;
        this.quality = quality;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return quality == request.quality &&
                price == request.price &&
                itemsCount == request.itemsCount &&
                type == request.type &&
                Objects.equals(requestId, request.requestId) &&
                Objects.equals(product, request.product) &&
                Objects.equals(requester, request.requester);
    }

    @Override
    public int hashCode() {

        return Objects.hash(requestId, quality, product, requester, price, itemsCount, type);
    }

    public String toString() {
        return "Request{" +
                "id=" + getRequestId() +
                ", product='" + getProduct() + "'" +
                ", requester=" + getRequester() +
                ", price=" + getPrice() +
                ", itemsCount=" + getItemsCount() +
                ", type=" + getType() +
                ", quality=" + getQuality() +
                "}";
    }

}
