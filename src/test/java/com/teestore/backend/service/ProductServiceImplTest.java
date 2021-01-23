package com.teestore.backend.service;

import com.teestore.backend.dao.ProductDAO;
import com.teestore.backend.enums.Category;
import com.teestore.backend.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Mock
    private ProductDAO productDAO;

    @InjectMocks
    private final ProductService productService= new ProductServiceImpl();

    @Test
    public void getProductByIdValidTest() throws Exception{
        Mockito.when(productDAO.getProductById(Mockito.anyString())).thenReturn(new Product());
        Product product= productService.getProductById("P10001");
        Assert.assertNotNull(product);
    }

    @Test
    public void getProductByIdInvalidTest1() throws Exception{
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getProductById(null));
        Assert.assertEquals("ProductService.INVALID_PRODUCT_ID",e.getMessage());
    }

    @Test
    public void getProductByIdInvalidTest2() throws Exception{
        Mockito.when(productDAO.getProductById(Mockito.anyString())).thenReturn(null);
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getProductById("P1001"));
        Assert.assertEquals("ProductService.PRODUCT_NOT_FOUND",e.getMessage());
    }

    @Test
    public void getProductsByCategoryValidTest() throws Exception{
        Product p= new Product();
        p.setCategory(Category.TOPS);
        p.setProductId("P10001");

        List<Product> pList= new ArrayList<>();
        pList.add(p);

        Mockito.when(productDAO.getProductsByCategory(Mockito.any())).thenReturn(pList);
        List<Product> productList= productService.getProductsByCategory(p.getCategory());
        Assert.assertNotNull(productList);
    }

    @Test
    public void getProductsByCategoryInValidTes1t() throws Exception{
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getProductsByCategory(null));
        Assert.assertEquals("ProductService.INVALID_PRODUCT_CATEGORY",e.getMessage());
    }
    @Test
    public void getProductsByCategoryInValidTest2() throws Exception{
        Mockito.when(productDAO.getProductsByCategory(Mockito.any())).thenReturn(null);
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getProductsByCategory(Category.TOPS));
        Assert.assertNotNull("ProductService.PRODUCT_LIST_NOT_FOUND",e.getMessage());
    }

    @Test
    public void getProductsByCategoryInValidTest3() throws Exception{
        Mockito.when(productDAO.getProductsByCategory(Mockito.any())).thenReturn(new ArrayList<Product>());
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getProductsByCategory(Category.TOPS));
        Assert.assertNotNull("ProductService.PRODUCT_LIST_NOT_FOUND",e.getMessage());
    }

    @Test
    public void getAllProductsValidTest() throws Exception{
        Product p= new Product();
        p.setCategory(Category.TOPS);
        p.setProductId("P10001");

        List<Product> pList= new ArrayList<>();
        pList.add(p);
        Mockito.when(productDAO.getAllProducts()).thenReturn(pList);
        List<Product> productList= productService.getAllProducts();
        Assert.assertNotNull(productList);
    }

    @Test
    public void getAllProductsInvalidTest1() throws Exception{
        Mockito.when(productDAO.getAllProducts()).thenReturn(null);
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getAllProducts());
        Assert.assertEquals("ProductService.PRODUCT_LIST_NOT_FOUND",e.getMessage());
    }

    @Test
    public void getAllProductsInvalidTest2() throws Exception{
        Mockito.when(productDAO.getAllProducts()).thenReturn(new ArrayList<>());
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getAllProducts());
        Assert.assertEquals("ProductService.PRODUCT_LIST_NOT_FOUND",e.getMessage());
    }

    @Test
    public void getNewArrivalsValidTest() throws Exception{
        Product p= new Product();
        p.setCategory(Category.TOPS);
        p.setProductId("P10001");

        List<Product> pList= new ArrayList<>();
        pList.add(p);
        Mockito.when(productDAO.getNewArrivals()).thenReturn(pList);
        List<Product> productList= productService.getNewArrivals();
        Assert.assertNotNull(productList);
    }

    @Test
    public void getNewArrivalsInvalidTest1() throws Exception{
        Mockito.when(productDAO.getNewArrivals()).thenReturn(null);
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getNewArrivals());
        Assert.assertEquals("ProductService.PRODUCT_LIST_NOT_FOUND",e.getMessage());
    }

    @Test
    public void getNewArrivalsInvalidTest2() throws Exception{
        Mockito.when(productDAO.getNewArrivals()).thenReturn(new ArrayList<>());
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getNewArrivals());
        Assert.assertEquals("ProductService.PRODUCT_LIST_NOT_FOUND",e.getMessage());
    }

    @Test
    public void getProductByDiscountValidTest() throws Exception{
        Product p= new Product();
        p.setCategory(Category.TOPS);
        p.setProductId("P10001");

        List<Product> pList= new ArrayList<>();
        pList.add(p);
        Mockito.when(productDAO.getProductByDiscount(Mockito.any())).thenReturn(pList);
        List<Product> productList= productService.getProductByDiscount(p.getCategory());
        Assert.assertNotNull(productList);
    }

    @Test
    public void getProductByDiscountInValidTest1() throws Exception{
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getProductByDiscount(null));
        Assert.assertEquals("ProductService.INVALID_PRODUCT_CATEGORY",e.getMessage());
    }

    @Test
    public void getProductByDiscountInValidTest2() throws Exception{
        Mockito.when(productDAO.getProductByDiscount(Mockito.any())).thenReturn(null);
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getProductByDiscount(Category.TOPS));
        Assert.assertEquals("ProductService.PRODUCT_LIST_NOT_FOUND",e.getMessage());
    }

    @Test
    public void getProductByDiscountInValidTest3() throws Exception{
        Mockito.when(productDAO.getProductByDiscount(Mockito.any())).thenReturn(new ArrayList<>());
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getProductByDiscount(Category.TOPS));
        Assert.assertEquals("ProductService.PRODUCT_LIST_NOT_FOUND",e.getMessage());
    }

    @Test
    public void getProductBySearchValidTest() throws Exception{
        Product p= new Product();
        p.setCategory(Category.TOPS);
        p.setProductId("P10001");

        List<Product> pList= new ArrayList<>();
        pList.add(p);
        Mockito.when(productDAO.getProductBySearchQuery(Mockito.anyString())).thenReturn(pList);
        List<Product> productList= productService.getProductBySearch("tops");
        Assert.assertNotNull(productList);
    }

    @Test
    public void getProductBySearchInvalidTest1() throws Exception{
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getProductBySearch(null));
        Assert.assertEquals("ProductService.INVALID_PRODUCT_SEARCH",e.getMessage());
    }

    @Test
    public void getProductBySearchInvalidTest2() throws Exception{
        Mockito.when(productDAO.getProductBySearchQuery(Mockito.anyString())).thenReturn(null);
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getProductBySearch("flipkart"));
        Assert.assertEquals("ProductService.PRODUCT_LIST_NOT_FOUND",e.getMessage());
    }

    @Test
    public void getProductBySearchInvalidTest3() throws Exception{
        Mockito.when(productDAO.getProductBySearchQuery(Mockito.anyString())).thenReturn(new ArrayList<>());
        Exception e= Assert.assertThrows(Exception.class,
                ()-> productService.getProductBySearch("amazon"));
        Assert.assertEquals("ProductService.PRODUCT_LIST_NOT_FOUND",e.getMessage());
    }

}
