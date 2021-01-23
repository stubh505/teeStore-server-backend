package com.teestore.backend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Carousel" )
public class CarouselEntity {

    @Id
    @GenericGenerator(name = "cIdGen", strategy = "com.teestore.backend.entity.generator.CarouselIdGenerator")
    @GeneratedValue(generator = "cIdGen")
    @Column(length = 7)
    private String carouselId;
    @Column(length = 250)
    private String linkImage;
    @Column(length = 250)
    private String linkRoute;

    public String getCarouselId() {
        return carouselId;
    }

    public void setCarouselId(String carouselId) {
        this.carouselId = carouselId;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getLinkRoute() {
        return linkRoute;
    }

    public void setLinkRoute(String linkRoute) {
        this.linkRoute = linkRoute;
    }
}
