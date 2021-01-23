package com.teestore.backend.entity;

import com.teestore.backend.enums.Category;
import com.teestore.backend.enums.Sex;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Product")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class ProductEntity {

    @Id
    @GenericGenerator(name = "pIdGen", strategy = "com.teestore.backend.entity.generator.ProductIdGenerator")
    @GeneratedValue(generator = "pIdGen")
    @Column(length = 7)
    private String productId;
    @Column(length = 150)
    private String productName;
    private Double cost;
    @Column(length = 15)
    private String size;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "sex_type")
    @Type(type = "pgsql_enum")
    private Sex sex;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "category_type")
    @Type(type = "pgsql_enum")
    private Category category;
    @Column(length = 30)
    private String quantity;
    private LocalDateTime dateOfAddition;
    @Column(length = 2000)
    private String productInfo;
    private Double discount;
    @Column(length = 10)
    private String avgRating;

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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
}