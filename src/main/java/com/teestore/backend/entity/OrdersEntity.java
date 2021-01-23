package com.teestore.backend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class OrdersEntity {

    @Id
    @GenericGenerator(name = "oIdGen", strategy = "com.teestore.backend.entity.generator.OrderIdGenerator")
    @GeneratedValue(generator = "oIdGen")
    @Column(length = 5)
    private String orderId;
    @Column(length = 1000)
    private String productIds;
    @Column(length = 1000)
    private String quantities;
    @Column(length = 1000)
    private String prices;
    @Column
    private String sizes;
    private Double totalCost;
    private LocalDateTime timeOfOrder;
    @Column(length = 50)
    private String paymentType;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_address")
    private AddressEntity deliverAddress;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public String getSizes() {
        return sizes;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public AddressEntity getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(AddressEntity deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getQuantities() {
        return quantities;
    }

    public void setQuantities(String quantities) {
        this.quantities = quantities;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public LocalDateTime getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(LocalDateTime timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
