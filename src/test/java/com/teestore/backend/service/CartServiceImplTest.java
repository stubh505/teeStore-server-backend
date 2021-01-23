package com.teestore.backend.service;


import com.teestore.backend.dao.CartDAO;
import com.teestore.backend.model.Cart;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CartServiceImplTest {

    @Mock
    private CartDAO cartDAO;

    @InjectMocks
    private final CartService cartService = new CartServiceImpl();

    @Test
    public void addCartValidTest () throws Exception {
        Cart c = new Cart();
        Mockito.when(cartDAO.addCart(Mockito.any(Cart.class))).thenReturn(Mockito.anyString());
        String res = cartService.addCart(c);
        Assert.assertNotNull(res);
    }

    @Test
    public void addCartInvalidTest () throws Exception {
        Mockito.when(cartDAO.addCart(Mockito.any(Cart.class))).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.addCart(new Cart()));
        Assert.assertEquals("CartService.UNABLE_TO_ADD_CART", e.getMessage());
    }

    @Test
    public void addCartInvalidTestNull () throws Exception {
        Cart c = new Cart();
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.addCart(null));
        Assert.assertEquals("CartService.INVALID_CART", e.getMessage());
    }

    @Test
    public void addProductToCartValidTest () throws Exception {
        Mockito.when(cartDAO.addProductToCart(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(1);
        Integer res = cartService.addProductToCart("C1001", "P", "XS");
        Assert.assertNotNull(res);
    }

    @Test
    public void addProductToCartInvalidTest () throws Exception {
        Mockito.when(cartDAO.addProductToCart(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.addProductToCart("C", "P", "XS"));
        Assert.assertEquals("CartService.UNABLE_TO_ADD_PRODUCT_TO_CART", e.getMessage());
    }

    @Test
    public void addProductToCartInvalidTestNull () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.addProductToCart("C", "", "XS"));
        Assert.assertEquals("CartService.INVALID_CART_ID", e.getMessage());
    }

    @Test
    public void removeProductToCartValidTest () throws Exception {
        Mockito.when(cartDAO.removeProductFromCart(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(1);
        Integer res = cartService.removeProductFromCart("C1001", "P", "XS");
        Assert.assertNotNull(res);
    }

    @Test
    public void removeProductToCartInvalidTest () throws Exception {
        Mockito.when(cartDAO.removeProductFromCart(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.removeProductFromCart("C", "P", "XS"));
        Assert.assertEquals("CartService.UNABLE_TO_REMOVE_PRODUCT_FROM_CART", e.getMessage());
    }

    @Test
    public void removeProductToCartInvalidTestNull () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.removeProductFromCart("C", "", "XS"));
        Assert.assertEquals("CartService.INVALID_CART_ID", e.getMessage());
    }

    @Test
    public void clearCartValidTest () throws Exception {
        Mockito.when(cartDAO.clearCart(Mockito.anyString())).thenReturn(Mockito.any());
        Cart res = cartService.clearCart("C1001");
        Assert.assertNotNull(res);
    }

    @Test
    public void clearCartInvalidTest () throws Exception {
        Mockito.when(cartDAO.clearCart(Mockito.anyString())).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.clearCart("C"));
        Assert.assertEquals("CartService.UNABLE_TO_CLEAR_CART", e.getMessage());
    }

    @Test
    public void clearCartInvalidTestNull () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.clearCart(""));
        Assert.assertEquals("CartService.INVALID_CART_ID", e.getMessage());
    }

    @Test
    public void getCartValidTest () throws Exception {
        Mockito.when(cartDAO.getCart(Mockito.anyString())).thenReturn(new Cart());
        Cart res = cartService.getCart("C1001");
        Assert.assertNotNull(res);
    }

    @Test
    public void getCartInvalidTest () throws Exception {
        Mockito.when(cartDAO.getCart(Mockito.anyString())).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.getCart("C"));
        Assert.assertEquals("CartService.UNABLE_TO_RETRIEVE_CART", e.getMessage());
    }

    @Test
    public void getCartInvalidTestNull () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.getCart(""));
        Assert.assertEquals("CartService.INVALID_CART_ID", e.getMessage());
    }

    @Test
    public void buyNowValidTest () throws Exception {
        Mockito.when(cartDAO.buyNow(Mockito.anyString())).thenReturn(Mockito.anyString());
        String res = cartService.buyNow("C1001");
        Assert.assertNotNull(res);
    }

    @Test
    public void buyNowInvalidTest () throws Exception {
        Mockito.when(cartDAO.buyNow(Mockito.anyString())).thenReturn(null);
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.buyNow("C"));
        Assert.assertEquals("CartService.UNABLE_TO_PLACE_ORDER", e.getMessage());
    }

    @Test
    public void buyNowInvalidTestNull () throws Exception {
        Exception e = Assert.assertThrows(Exception.class,
                () -> cartService.buyNow(""));
        Assert.assertEquals("CartService.INVALID_CART_ID", e.getMessage());
    }
}
