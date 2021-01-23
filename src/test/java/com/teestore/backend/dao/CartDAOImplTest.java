package com.teestore.backend.dao;


import com.teestore.backend.model.Cart;
import com.teestore.backend.model.Product;
import com.teestore.backend.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class CartDAOImplTest {

    @Autowired
    private CartDAO cartDAO;

    //@Test
    public void getCartValidTest () throws Exception {
        Cart res = cartDAO.getCart("C1001");
        Assert.assertNotNull(res);
    }

    @Test
    public void getCartInvalidTest () throws Exception {
        Cart res = cartDAO.getCart("C1000");
        Assert.assertNull(res);
    }

    @Test
    public void addCartValidTest () throws Exception {
        Cart c = new Cart();
        List<Product> products = new ArrayList<>();
        Product p = new Product();
        p.setProductId("P10001");
        products.add(p);
        p = new Product();
        p.setProductId("P10002");
        products.add(p);

        User u = new User();
        u.setUserId("U1001");

        c.setProducts(products);
        c.setUser(u);
        c.setTotalCost(7895.0);
        c.setQuantities(Arrays.asList(9, 7));

        String res = cartDAO.addCart(c);

        Assert.assertNotNull(res);
        Assert.assertTrue(res.matches("[C][0-9]{4}"));
    }

    @Test
    public void addCartInvalidTestInvalidUser () throws Exception {
        Cart c = new Cart();
        List<Product> products = new ArrayList<>();
        Product p = new Product();
        p.setProductId("P10001");
        products.add(p);
        p = new Product();
        p.setProductId("P10002");
        products.add(p);

        User u = new User();
        u.setUserId("U1000");

        c.setProducts(products);
        c.setUser(u);
        c.setTotalCost(7895.0);
        c.setQuantities(Arrays.asList(9, 7));

        String res = cartDAO.addCart(c);

        Assert.assertNull(res);
    }
}
