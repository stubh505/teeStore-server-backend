package com.teestore.backend.service;

import com.teestore.backend.dao.ImagesDAO;
import com.teestore.backend.model.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "imagesService")
@Transactional
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    private ImagesDAO imagesDAO;

    @Override
    public List<Images> getImagesByReference(String reference) throws Exception {

        if (reference == null)
            throw new Exception("ImagesService.INVALID_REFERENCE");

        List<Images> imagesList = imagesDAO.getImagesByReference(reference);
        if (imagesList == null || imagesList.isEmpty())
            throw new Exception("ImagesService.IMAGES_LIST_NOT_FOUND");

        return imagesList;
    }

    @Override
    public List<String> addImages(List<Images> imagesList) throws Exception {

        if (imagesList == null || imagesList.isEmpty())
            throw new Exception("ImagesService.IMAGES_LIST_NOT_FOUND");

        List<String> imageIdList = imagesDAO.addImages(imagesList);
        if (imageIdList == null || imageIdList.isEmpty())
            throw new Exception("ImagesService.UNABLE_TO_ADD_IMAGES_LIST");

        return imageIdList;
    }

    @Override
    public List<String> removeImages(List<String> imagesIdList) throws Exception {

        if (imagesIdList == null || imagesIdList.isEmpty())
            throw new Exception("ImagesService.IMAGES_ID_LIST_NOT_FOUND");

        List<String> iIdList = imagesDAO.removeImages(imagesIdList);
        if (iIdList == null || iIdList.isEmpty())
            throw new Exception("ImagesService.UNABLE_TO_DELETE_IMAGES_LIST");

        return iIdList;
    }

    @Override
    public String removeImage(String imageId) throws Exception {

        if (imageId == null || imageId.equals(""))
            throw new Exception("ImagesService.INVALID_IMAGE_ID");

        String iId = imagesDAO.removeImage(imageId);
        if (iId == null || iId.equals(""))
            throw new Exception("ImagesService.UNABLE_TO_DELETE_IMAGE");

        return iId;
    }

    @Override
    public Images getImageById(String imageId) throws Exception {
        if (imageId == null || imageId.equals(""))
            throw new Exception("ImagesService.INVALID_IMAGE_ID");

        Images iId = imagesDAO.getImageById(imageId);
        if (iId == null)
            throw new Exception("ImagesService.IMAGE_NOT_RETRIEVED");

        return iId;
    }
}
