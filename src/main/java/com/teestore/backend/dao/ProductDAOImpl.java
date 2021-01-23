package com.teestore.backend.dao;

import com.teestore.backend.entity.ProductEntity;
import com.teestore.backend.enums.Category;
import com.teestore.backend.model.Product;
import com.teestore.backend.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "productDAO")
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ImagesService imagesService;

    @Override
    public String addNewProduct(Product product) throws Exception {
        ProductEntity entity = new ProductEntity();

        entity.setCategory(product.getCategory());
        entity.setCost(product.getCost());
        entity.setDateOfAddition(LocalDateTime.now());
        entity.setDiscount(product.getDiscount());
        entity.setProductInfo(product.getProductInfo());
        entity.setSex(product.getSex());
        entity.setProductName(product.getProductName());
        entity.setAvgRating("0.0");

        StringBuilder size = new StringBuilder();
        StringBuilder qty = new StringBuilder();

        for (Map.Entry<String, Integer> entry : product.getSizeAndQuantity().entrySet()) {
            size.append(entry.getKey()).append(",");
            qty.append(entry.getValue()).append(",");
        }

        if (size.length() > 1) {
            entity.setSize(size.substring(0, size.length() - 1));
            entity.setQuantity(qty.substring(0, qty.length() - 1));
        }

        entityManager.persist(entity);

        return entity.getProductId();
    }

    @Override
    public Product getProductById(String productId) throws Exception {

        ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);

        if (productEntity == null)
            return null;

        Product product = new Product();
        product.setProductId(productEntity.getProductId());
        product.setProductName(productEntity.getProductName());
        product.setCategory(productEntity.getCategory());
        product.setCost(productEntity.getCost());
        product.setDateOfAddition(productEntity.getDateOfAddition());
        product.setSex(productEntity.getSex());

        Map<String, Integer> sizeMap = new HashMap<>();
        String[] sizes = productEntity.getSize().split(",");
        String[] quantities = productEntity.getQuantity().split(",");

        for (int i = 0; i < sizes.length; i++) {
            sizeMap.put(sizes[i], Integer.parseInt(quantities[i]));
        }
        product.setSizeAndQuantity(sizeMap);
        product.setProductInfo(productEntity.getProductInfo());
        product.setDiscount(productEntity.getDiscount());
        product.setTotalRaters(productEntity.getAvgRating().split("\\.")[1]);
        double rating = Double.parseDouble(productEntity.getAvgRating().split("\\.")[0]);
        if (!product.getTotalRaters().equals("0"))
            rating = rating / (Integer.parseInt(product.getTotalRaters()));
        product.setAvgRating(rating + "");

        product.setImages(imagesService.getImagesByReference(product.getProductId()));

        return product;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) throws Exception {

        Query query = entityManager.createQuery("select p from ProductEntity p where p.category =:category");
        query.setParameter("category", category);

        List<ProductEntity> productEntityList = query.getResultList();
        List<Product> productList = null;

        if (productEntityList != null && !productEntityList.isEmpty()) {

            productList = new ArrayList<>();
            for (ProductEntity productEntity : productEntityList) {
                Product product = new Product();
                product.setProductId(productEntity.getProductId());
                product.setProductName(productEntity.getProductName());
                product.setCategory(productEntity.getCategory());
                product.setCost(productEntity.getCost());
                product.setDateOfAddition(productEntity.getDateOfAddition());
                product.setSex(productEntity.getSex());

                Map<String, Integer> sizeMap = new HashMap<>();
                String[] sizes = productEntity.getSize().split(",");
                String[] quantities = productEntity.getQuantity().split(",");

                for (int i = 0; i < sizes.length; i++) {
                    sizeMap.put(sizes[i], Integer.parseInt(quantities[i]));
                }
                product.setSizeAndQuantity(sizeMap);
                product.setProductInfo(productEntity.getProductInfo());
                product.setDiscount(productEntity.getDiscount());
                product.setTotalRaters(productEntity.getAvgRating().split("\\.")[1]);
                double rating = Double.parseDouble(productEntity.getAvgRating().split("\\.")[0]);
                if (!product.getTotalRaters().equals("0"))
                    rating = rating / (Integer.parseInt(product.getTotalRaters()));
                product.setAvgRating(rating + "");

                product.setImages(imagesService.getImagesByReference(product.getProductId()));

                productList.add(product);
            }

        }
        return productList;
    }

    @Override
    public List<Product> getSimilarProducts(Category category) throws Exception {

        Query query = entityManager.createQuery("select p from ProductEntity p where p.category =:category");
        query.setParameter("category", category);

        List<ProductEntity> productEntityList = query.setMaxResults(6).getResultList();
        List<Product> productList = null;

        if (productEntityList != null && !productEntityList.isEmpty()) {

            productList = new ArrayList<>();
            for (ProductEntity productEntity : productEntityList) {
                Product product = new Product();
                product.setProductId(productEntity.getProductId());
                product.setProductName(productEntity.getProductName());
                product.setCategory(productEntity.getCategory());
                product.setCost(productEntity.getCost());
                product.setDateOfAddition(productEntity.getDateOfAddition());
                product.setSex(productEntity.getSex());

                Map<String, Integer> sizeMap = new HashMap<>();
                String[] sizes = productEntity.getSize().split(",");
                String[] quantities = productEntity.getQuantity().split(",");

                for (int i = 0; i < sizes.length; i++) {
                    sizeMap.put(sizes[i], Integer.parseInt(quantities[i]));
                }
                product.setSizeAndQuantity(sizeMap);
                product.setProductInfo(productEntity.getProductInfo());
                product.setDiscount(productEntity.getDiscount());
                product.setTotalRaters(productEntity.getAvgRating().split("\\.")[1]);
                double rating = Double.parseDouble(productEntity.getAvgRating().split("\\.")[0]);
                if (!product.getTotalRaters().equals("0"))
                    rating = rating / (Integer.parseInt(product.getTotalRaters()));
                product.setAvgRating(rating + "");

                product.setImages(imagesService.getImagesByReference(product.getProductId()));

                productList.add(product);
            }

        }
        return productList;
    }

    @Override
    public List<Product> getAllProducts() throws Exception {

        Query query = entityManager.createQuery("select p from ProductEntity p");

        List<ProductEntity> productEntityList = query.getResultList();
        List<Product> productList = null;

        if (productEntityList != null && !productEntityList.isEmpty()) {

            productList = new ArrayList<>();
            for (ProductEntity productEntity : productEntityList) {
                Product product = new Product();
                product.setProductId(productEntity.getProductId());
                product.setProductName(productEntity.getProductName());
                product.setCategory(productEntity.getCategory());
                product.setCost(productEntity.getCost());
                product.setDateOfAddition(productEntity.getDateOfAddition());
                product.setSex(productEntity.getSex());

                Map<String, Integer> sizeMap = new HashMap<>();
                String[] sizes = productEntity.getSize().split(",");
                String[] quantities = productEntity.getQuantity().split(",");

                for (int i = 0; i < sizes.length; i++) {
                    sizeMap.put(sizes[i], Integer.parseInt(quantities[i]));
                }
                product.setSizeAndQuantity(sizeMap);
                product.setProductInfo(productEntity.getProductInfo());
                product.setTotalRaters(productEntity.getAvgRating().split("\\.")[1]);
                double rating = Double.parseDouble(productEntity.getAvgRating().split("\\.")[0]);
                if (!product.getTotalRaters().equals("0"))
                    rating = rating / (Integer.parseInt(product.getTotalRaters()));
                product.setAvgRating(rating + "");
                product.setDiscount(productEntity.getDiscount());

                product.setImages(imagesService.getImagesByReference(product.getProductId()));

                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<Product> getNewArrivals() throws Exception {

        Query query = entityManager.createQuery("select p from ProductEntity p order by p.dateOfAddition desc");

        List<ProductEntity> productEntityList = query.setMaxResults(6).getResultList();
        List<Product> productList = null;

        if (productEntityList != null && !productEntityList.isEmpty()) {

            productList = new ArrayList<>();
            for (ProductEntity productEntity : productEntityList) {
                Product product = new Product();
                product.setProductId(productEntity.getProductId());
                product.setProductName(productEntity.getProductName());
                product.setCategory(productEntity.getCategory());
                product.setCost(productEntity.getCost());
                product.setDateOfAddition(productEntity.getDateOfAddition());
                product.setSex(productEntity.getSex());

                Map<String, Integer> sizeMap = new HashMap<>();
                String[] sizes = productEntity.getSize().split(",");
                String[] quantities = productEntity.getQuantity().split(",");

                for (int i = 0; i < sizes.length; i++) {
                    sizeMap.put(sizes[i], Integer.parseInt(quantities[i]));
                }
                product.setSizeAndQuantity(sizeMap);
                product.setProductInfo(productEntity.getProductInfo());
                product.setTotalRaters(productEntity.getAvgRating().split("\\.")[1]);
                double rating = Double.parseDouble(productEntity.getAvgRating().split("\\.")[0]);
                if (!product.getTotalRaters().equals("0"))
                    rating = rating / (Integer.parseInt(product.getTotalRaters()));
                product.setAvgRating(rating + "");
                product.setDiscount(productEntity.getDiscount());

                product.setImages(imagesService.getImagesByReference(product.getProductId()));

                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<Product> getProductByDiscount(Category category) throws Exception {

        Query query = entityManager.createQuery("select p from ProductEntity p where p.category =:category and p.discount>0.0");
        query.setParameter("category", category);

        List<ProductEntity> productEntityList = query.getResultList();
        List<Product> productList = null;

        if (productEntityList != null && !productEntityList.isEmpty()) {

            productList = new ArrayList<>();
            for (ProductEntity productEntity : productEntityList) {
                Product product = new Product();
                product.setProductId(productEntity.getProductId());
                product.setProductName(productEntity.getProductName());
                product.setCategory(productEntity.getCategory());
                product.setCost(productEntity.getCost());
                product.setDateOfAddition(productEntity.getDateOfAddition());
                product.setSex(productEntity.getSex());

                Map<String, Integer> sizeMap = new HashMap<>();
                String[] sizes = productEntity.getSize().split(",");
                String[] quantities = productEntity.getQuantity().split(",");

                for (int i = 0; i < sizes.length; i++) {
                    sizeMap.put(sizes[i], Integer.parseInt(quantities[i]));
                }
                product.setSizeAndQuantity(sizeMap);
                product.setProductInfo(productEntity.getProductInfo());
                product.setTotalRaters(productEntity.getAvgRating().split("\\.")[1]);
                double rating = Double.parseDouble(productEntity.getAvgRating().split("\\.")[0]);
                if (!product.getTotalRaters().equals("0"))
                    rating = rating / (Integer.parseInt(product.getTotalRaters()));
                product.setAvgRating(rating + "");
                product.setDiscount(productEntity.getDiscount());

                product.setImages(imagesService.getImagesByReference(product.getProductId()));

                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<Product> getProductBySearchQuery(String search) throws Exception {
        search = search.toLowerCase();
        String[] categories = new String[]{"t-shirt", "t shirt", "tshirt", "skirt", "shirt", "top", "jean", "trouser"};

        Category category = null;
        List<ProductEntity> productEntityList = null;

        for (int i = 0; i < categories.length; i++) {
            if (search.contains(categories[i])) {
                if (i < 3) category = Category.TSHIRT;
                else if (i == 3) category = Category.SKIRTS;
                else if (i == 4) category = Category.SHIRT;
                else if (i == 5) category = Category.TOPS;
                else if (i == 6) category = Category.JEANS;
                else category = Category.TROUSERS;
                break;
            }
        }

        if (category == null) {
            Query query1 = entityManager.createQuery("select p from ProductEntity p where lower(p.productName) like :search");
            query1.setParameter("search", "%" + search.toLowerCase() + "%");

            productEntityList = query1.getResultList();

            Query query2 = entityManager.createQuery("select p from ProductEntity p where lower(p.productInfo) like :search");
            query2.setParameter("search", "%" + search.toLowerCase() + "%");

            List<ProductEntity> tempList = query2.getResultList();
            for (ProductEntity pe : tempList) {
                if (!productEntityList.contains(pe))
                    productEntityList.add(pe);
            }

            Query query3 = entityManager.createQuery("select p from ProductEntity p where lower(p.productName) like :search or lower(p.productInfo) like :search");
            query3.setParameter("search", "%" + search.replace(' ', '%').toLowerCase() + "%");
            tempList = query3.getResultList();
            for (ProductEntity pe : tempList) {
                if (!productEntityList.contains(pe))
                    productEntityList.add(pe);
            }
        } else {
            Query query1 = entityManager.createQuery("select p from ProductEntity p where lower(p.productName) like :search and p.category =:category");
            query1.setParameter("search", "%" + search.toLowerCase() + "%");
            query1.setParameter("category", category);

            productEntityList = query1.getResultList();

            Query query2 = entityManager.createQuery("select p from ProductEntity p where lower(p.productInfo) like :search and p.category =:category");
            query2.setParameter("search", "%" + search.toLowerCase() + "%");
            query2.setParameter("category", category);

            List<ProductEntity> tempList = query2.getResultList();
            for (ProductEntity pe : tempList) {
                if (!productEntityList.contains(pe))
                    productEntityList.add(pe);
            }

            Query query3 = entityManager.createQuery("select p from ProductEntity p where lower(p.productName) like :search or lower(p.productInfo) like :search and p.category =:category");
            query3.setParameter("search", "%" + search.replace(' ', '%').toLowerCase() + "%");
            query3.setParameter("category", category);
            tempList = query3.getResultList();
            for (ProductEntity pe : tempList) {
                if (!productEntityList.contains(pe))
                    productEntityList.add(pe);
            }
        }

        List<Product> productList = null;

        if (productEntityList != null && !productEntityList.isEmpty()) {

            productList = new ArrayList<>();
            for (ProductEntity productEntity : productEntityList) {
                Product product = new Product();
                product.setProductId(productEntity.getProductId());
                product.setProductName(productEntity.getProductName());
                product.setCategory(productEntity.getCategory());
                product.setCost(productEntity.getCost());
                product.setDateOfAddition(productEntity.getDateOfAddition());
                product.setSex(productEntity.getSex());

                Map<String, Integer> sizeMap = new HashMap<>();
                String[] sizes = productEntity.getSize().split(",");
                String[] quantities = productEntity.getQuantity().split(",");

                for (int i = 0; i < sizes.length; i++) {
                    sizeMap.put(sizes[i], Integer.parseInt(quantities[i]));
                }
                product.setSizeAndQuantity(sizeMap);
                product.setProductInfo(productEntity.getProductInfo());
                product.setTotalRaters(productEntity.getAvgRating().split("\\.")[1]);
                double rating = Double.parseDouble(productEntity.getAvgRating().split("\\.")[0]);
                if (!product.getTotalRaters().equals("0"))
                    rating = rating / (Integer.parseInt(product.getTotalRaters()));
                product.setAvgRating(rating + "");
                product.setDiscount(productEntity.getDiscount());

                product.setImages(imagesService.getImagesByReference(product.getProductId()));

                productList.add(product);
            }
        }
        return productList;
    }

}
