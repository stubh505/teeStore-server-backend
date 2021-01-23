package com.teestore.backend.service;

import com.teestore.backend.dao.CartDAO;
import com.teestore.backend.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "cartService")
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    CartDAO cartDAO;

    @Override
    public String addCart(Cart cart) throws Exception {
        if (cart == null)
            throw new Exception("CartService.INVALID_CART");
        String res = cartDAO.addCart(cart);
        if (res == null)
            throw new Exception("CartService.UNABLE_TO_ADD_CART");
        return res;
    }

    @Override
    public Integer addProductToCart(String userId, String productId, String size) throws Exception {
        if (userId == null || productId == null || userId.equals("") || productId.equals(""))
            throw new Exception("CartService.INVALID_CART_ID");
        Integer res = cartDAO.addProductToCart(userId, productId, size);
        if (res == null)
            throw new Exception("CartService.UNABLE_TO_ADD_PRODUCT_TO_CART");
        else if (res == 0)
            throw new Exception("CartService.PRODUCT_ALREADY_IN_CART");
        return res;
    }

    @Override
    public Integer removeProductFromCart(String userId, String productId, String size) throws Exception {
        if (userId == null || productId == null || userId.equals("") || productId.equals(""))
            throw new Exception("CartService.INVALID_CART_ID");
        Integer res = cartDAO.removeProductFromCart(userId, productId, size);
        if (res == null)
            throw new Exception("CartService.UNABLE_TO_REMOVE_PRODUCT_FROM_CART");
        return res;
    }

    @Override
    public Cart clearCart(String cartId) throws Exception {
        if (cartId == null || cartId.equals(""))
            throw new Exception("CartService.INVALID_CART_ID");
        Cart res = cartDAO.clearCart(cartId);
        if (res == null)
            throw new Exception("CartService.UNABLE_TO_CLEAR_CART");
        return res;
    }

    @Override
    public String editCart(String cartId, Cart cart) throws Exception {
        if (cartId == null || cartId.equals(""))
            throw new Exception("CartService.INVALID_CART_ID");
        String res = cartDAO.editCart(cartId, cart);
        if (res == null)
            throw new Exception("CartService.UNABLE_TO_UPDATE_CART");
        return res;
    }

    @Override
    public Cart getCart(String userId) throws Exception {
        if (userId == null || userId.equals(""))
            throw new Exception("CartService.INVALID_USER_ID");
        Cart res = cartDAO.getCart(userId);
        if (res == null)
            throw new Exception("CartService.UNABLE_TO_RETRIEVE_CART");
        return res;
    }

    @Override
    public String buyNow(String cartId) throws Exception {
        if (cartId == null || cartId.equals(""))
            throw new Exception("CartService.INVALID_CART_ID");
        String res = cartDAO.buyNow(cartId);
        if (res == null)
            throw new Exception("CartService.UNABLE_TO_PLACE_ORDER");
        return res;
    }
}
