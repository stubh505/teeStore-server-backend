package com.teestore.backend.dao;

import com.teestore.backend.enums.Category;
import com.teestore.backend.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class ProductDAOImplTest {

    @Autowired
    private ProductDAO productDAO;

    @Test
    public void getProductByIdValidTest() throws Exception{
        Product p= new Product();
        p.setProductId("P10001");

        Product product=productDAO.getProductById(p.getProductId());
        Assert.assertNotNull(product);
    }

    @Test
    public void getProductByIdInvalidTest() throws Exception{
        Product p= new Product();
        p.setProductId("P1001");

        Product product=productDAO.getProductById(p.getProductId());
        Assert.assertNull(product);
    }

    @Test
    public void getProductsByCategoryValidTest() throws Exception{
        Product p=new Product();
        p.setCategory(Category.JEANS);

        List<Product> productList= productDAO.getProductsByCategory(p.getCategory());
        Assert.assertNotNull(productList);
    }

    @Test
    public void getProductsByCategoryInvalidTest() throws Exception{
        Product p= new Product();
        p.setCategory(null);

        List<Product> productList= productDAO.getProductsByCategory(p.getCategory());
        Assert.assertNull(productList);
    }

    @Test
    public void getAllProductsValidTest() throws Exception{
        List<Product> productList= productDAO.getAllProducts();
        Assert.assertNotNull(productList);
    }

    @Test
    public void getNewArrivalsValidTest() throws Exception{
        List<Product> productList= productDAO.getNewArrivals();
        Assert.assertNotNull(productList);
    }

    @Test
    public void getProductByDiscountValidTest() throws Exception{
        Product p= new Product();
        p.setCategory(Category.SKIRTS);
        p.setDiscount(10.0);
        List<Product> productList= productDAO.getProductByDiscount(p.getCategory());
        Assert.assertNotNull(productList);
    }

    @Test
    public void getProductByDiscountInValidTest() throws Exception{
        Product p= new Product();
        p.setCategory(Category.TOPS);

        List<Product> productList= productDAO.getProductByDiscount(p.getCategory());
        Assert.assertNull(productList);
    }

    @Test
    public void getProductBySearchQueryValidTest1() throws Exception{
        List<Product> productList= productDAO.getProductBySearchQuery("jeans");
        Assert.assertNotNull(productList);
    }

    @Test
    public void getProductBySearchQueryValidTest2() throws Exception{
        List<Product> productList= productDAO.getProductBySearchQuery("cute");
        Assert.assertNotNull(productList);
    }

    @Test
    public void getProductBySearchQueryInValidTest1() throws Exception{
        List<Product> productList= productDAO.getProductBySearchQuery("amazon");
        Assert.assertNull(productList);
    }

    @Test
    public void getProductBySearchQueryInValidTest2() throws Exception{
        List<Product> productList= productDAO.getProductBySearchQuery("flipkart");
        Assert.assertNull(productList);
    }


}
