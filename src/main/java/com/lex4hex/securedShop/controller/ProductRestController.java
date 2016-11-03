package com.lex4hex.securedShop.controller;

import com.lex4hex.securedShop.model.Product;
import com.lex4hex.securedShop.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.PersistenceException;
import java.util.List;

@RestController
public class ProductRestController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductRestController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    /**
     * Get list of all products
     */
    @RequestMapping(value = "/api/shop/products", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products;

        try {
            products = productService.findAllProducts();
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (products.isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Get product with provided ID
     *
     * @param id ID of product
     */
    @RequestMapping(value = "/api/shop/products/{id}", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
        Product product;

        try {
            product = productService.findById(id);

            if (product == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Create product with provided JSON object
     *
     * @param product JSON product object after auto-mapping
     */
    @RequestMapping(value = "/api/shop/products", method = RequestMethod.POST)
    public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
        try {
            if (productService.checkIfProductExists(product)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            productService.saveProduct(product);
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/shop/products/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Update product with provided JSON object
     *
     * @param product JSON product object after auto-mapping
     */
    @RequestMapping(value = "/api/shop/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        try {
            if (productService.checkIfProductExists(product)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            productService.updateProduct(product);
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Delete product by provided ID
     *
     * @param id ID of product to delete
     */
    @RequestMapping(value = "/api/shop/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
        try {
            Product product = productService.findById(id);

            if (product == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            productService.deleteProductById(id);
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}