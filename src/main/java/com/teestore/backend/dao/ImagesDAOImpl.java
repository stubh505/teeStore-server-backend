package com.teestore.backend.dao;

import com.teestore.backend.entity.ImagesEntity;
import com.teestore.backend.model.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "imagesDAO")
public class ImagesDAOImpl implements ImagesDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Images> getImagesByReference(String reference) throws Exception {

        Query query = entityManager.createQuery("select i from ImagesEntity i where i.reference=:reference");
        query.setParameter("reference", reference);
        List<ImagesEntity> imagesEntityList = query.getResultList();
        List<Images> imagesList = null;

        if (imagesEntityList != null && !imagesEntityList.isEmpty()) {
            imagesList = new ArrayList<>();
            for (ImagesEntity imagesEntity : imagesEntityList) {
                Images images = new Images();
                images.setImageId(imagesEntity.getImageId());
                images.setLinkImage(imagesEntity.getLinkImage());
                images.setReference(imagesEntity.getReference());
                imagesList.add(images);
            }
        }
        return imagesList;
    }

    @Override
    public List<String> addImages(List<Images> imagesList) throws Exception {

        List<String> imageIdList = new ArrayList<>();

        for (Images images : imagesList) {
            ImagesEntity imagesEntity = new ImagesEntity();
            imagesEntity.setLinkImage(images.getLinkImage());
            imagesEntity.setReference(images.getReference());
            entityManager.persist(imagesEntity);
            imageIdList.add(imagesEntity.getImageId());
        }
        return imageIdList;
    }

    @Override
    public List<String> removeImages(List<String> imagesIdList) throws Exception {

        List<String> iIdList = null;

        for (String imageId : imagesIdList) {
            ImagesEntity imagesEntity = entityManager.find(ImagesEntity.class, imageId);
            if (imagesEntity != null)
                entityManager.remove(imagesEntity);
            iIdList.add(imagesEntity.getImageId());
        }
        return iIdList;
    }

    @Override
    public String removeImage(String imageId) throws Exception {

        ImagesEntity imagesEntity = entityManager.find(ImagesEntity.class, imageId);

        if (imagesEntity != null)
            entityManager.remove(imagesEntity);

        return imagesEntity.getImageId();
    }

    @Override
    public Images getImageById(String id) throws Exception {
        ImagesEntity imagesEntity = entityManager.find(ImagesEntity.class, id);
        Images image = null;

        if (imagesEntity != null) {
            image = new Images();
            image.setImageId(imagesEntity.getImageId());
            image.setLinkImage(imagesEntity.getLinkImage());
            image.setReference(imagesEntity.getReference());
        }

        return image;
    }
}
