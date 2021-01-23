package com.teestore.backend.service;

import com.teestore.backend.model.Images;

import java.util.List;

public interface ImagesService {

    List<Images> getImagesByReference(String reference) throws Exception;

    List<String> addImages(List<Images> imagesList) throws Exception;

    List<String> removeImages(List<String> imagesIdList) throws Exception;

    String removeImage(String imageId) throws Exception;

    Images getImageById(String id) throws Exception;
}
