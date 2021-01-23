package com.teestore.backend.api;

import com.teestore.backend.enums.Category;
import com.teestore.backend.model.Product;
import com.teestore.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("ProductAPI")
public class ProductAPI {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/addNewProduct")
    private ResponseEntity<String> addNewProduct(@RequestBody Product product) {

        try {
            return new ResponseEntity<>(productService.addNewProduct(product), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/getProductById/{productId}", method = RequestMethod.GET)
    private ResponseEntity<Product> getProductById(@PathVariable String productId) {

        try {
            return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @RequestMapping(value = "/getProductsByCategory/{category}", method = RequestMethod.GET)
    private ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Category category) {

        try {
            return new ResponseEntity<>(productService.getProductsByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/getSimilarProducts/{category}", method = RequestMethod.GET)
    private ResponseEntity<List<Product>> getSimilarProducts(@PathVariable Category category) {

        try {
            return new ResponseEntity<>(productService.getSimilarProducts(category), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/getAllProducts", method = RequestMethod.GET)
    private ResponseEntity<List<Product>> getAllProducts() {

        try {
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/getNewArrivals", method = RequestMethod.GET)
    private ResponseEntity<List<Product>> getNewArrivals() {

        try {
            return new ResponseEntity<>(productService.getNewArrivals(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/getProductByDiscount/{category}", method = RequestMethod.GET)
    private ResponseEntity<List<Product>> getProductByDiscount(@PathVariable Category category) {

        try {
            return new ResponseEntity<>(productService.getProductByDiscount(category), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/getProducts")
    public ResponseEntity<List<Product>> getProductBySearch(@RequestParam String search) throws Exception {
        try {
            return new ResponseEntity<>(productService.getProductBySearch(search), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
