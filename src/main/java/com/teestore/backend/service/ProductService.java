package com.teestore.backend.service;

import com.teestore.backend.enums.Category;
import com.teestore.backend.model.Product;

import java.util.List;

public interface ProductService {

    /**
     * Service method to persist a new product to the database
     *
     * @param product Product details to persist
     * @return persisted product id
     * @throws Exception Unable to persist new product
     */
    String addNewProduct(Product product) throws Exception;

    /**
     * Service method to retrieve product by id
     *
     * @param productId Id of product to be retrieved
     * @return Product
     * @throws Exception Not Found
     */
    Product getProductById(String productId) throws Exception;

    /**
     * Service method to get product by category
     *
     * @param category Product category
     * @return List of retrieved product
     * @throws Exception Not found
     */
    List<Product> getProductsByCategory(Category category) throws Exception;

    /**
     * Service method to get similar product to the one selected product
     *
     * @param category Product category
     * @return List of retrieved product
     * @throws Exception Not found
     */
    List<Product> getSimilarProducts(Category category) throws Exception;

    /**
     * Service method to get all product
     *
     * @return List of retrieved product
     * @throws Exception Not found
     */
    List<Product> getAllProducts() throws Exception;

    /**
     * Service method to get newly arrived products
     *
     * @return List of retrieved product
     * @throws Exception Not found
     */
    List<Product> getNewArrivals() throws Exception;

    /**
     * Service method to get product by category which has a discount
     *
     * @param category Product category
     * @return List of retrieved product
     * @throws Exception Not found
     */
    List<Product> getProductByDiscount(Category category) throws Exception;

    /**
     * Service method to get product by search term
     *
     * @param search Search term provided
     * @return List of retrieved product
     * @throws Exception Not found
     */
    List<Product> getProductBySearch(String search) throws Exception;
}
