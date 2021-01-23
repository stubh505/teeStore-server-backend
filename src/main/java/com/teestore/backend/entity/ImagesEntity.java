package com.teestore.backend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Images")
public class ImagesEntity {

    @Id
    @GenericGenerator(name = "iIdGen", strategy = "com.teestore.backend.entity.generator.ImageIdGenerator")
    @GeneratedValue(generator = "iIdGen")
    @Column(length = 7)
    private String imageId;
    @Column(length = 200)
    private String linkImage;
    @Column(length = 9)
    private String reference;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
