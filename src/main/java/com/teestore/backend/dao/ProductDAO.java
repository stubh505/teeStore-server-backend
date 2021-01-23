package com.teestore.backend.dao;

import com.teestore.backend.enums.Category;
import com.teestore.backend.model.Product;

import java.util.List;

public interface ProductDAO {

    /**
     * DAO method to persist a new product to the database
     *
     * @param product Product details to persist
     * @return persisted product id
     * @throws Exception Unable to persist new product
     */
    String addNewProduct(Product product) throws Exception;

    Product getProductById(String productId) throws Exception;

    List<Product> getProductsByCategory(Category category) throws Exception;

    List<Product> getSimilarProducts(Category category) throws Exception;

    List<Product> getAllProducts() throws Exception;

    List<Product> getNewArrivals() throws Exception;

    List<Product> getProductByDiscount(Category category) throws Exception;

    List<Product> getProductBySearchQuery(String query) throws Exception;
}
