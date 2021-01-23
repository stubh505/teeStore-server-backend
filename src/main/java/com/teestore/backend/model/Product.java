package com.teestore.backend.model;

import com.teestore.backend.enums.Category;
import com.teestore.backend.enums.Sex;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Product {
    private String productId;
    private String productName;
    private Double cost;
    private Map<String, Integer> sizeAndQuantity;
    private Sex sex;
    private Category category;
    private LocalDateTime dateOfAddition;
    private String productInfo;
    private Double discount;
    private String avgRating;
    private String totalRaters;
    private List<Images> images;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Map<String, Integer> getSizeAndQuantity() {
        return sizeAndQuantity;
    }

    public void setSizeAndQuantity(Map<String, Integer> sizeAndQuantity) {
        this.sizeAndQuantity = sizeAndQuantity;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(LocalDateTime dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public String getTotalRaters() {
        return totalRaters;
    }

    public void setTotalRaters(String totalRaters) {
        this.totalRaters = totalRaters;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }
}
