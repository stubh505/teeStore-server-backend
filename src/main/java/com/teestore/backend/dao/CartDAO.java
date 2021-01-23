package com.teestore.backend.dao;

import com.teestore.backend.model.Cart;

public interface CartDAO {

    String addCart(Cart cart) throws Exception;

    Integer addProductToCart(String userId, String productId, String size) throws Exception;

    Integer removeProductFromCart(String userId, String productId, String size) throws Exception;

    String editCart(String cartId, Cart cart) throws Exception;

    Cart clearCart(String cartId) throws Exception;

    Cart getCart(String userId) throws Exception;

    String buyNow(String cartId) throws Exception;
}