package com.lex4hex.securedShop.service;

import com.lex4hex.securedShop.model.Product;

import java.util.List;

public interface ProductService {

    Product findById(Integer id);

    Product findByName(String name);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProductById(Integer id);

    List<Product> findAllProducts();

    boolean checkIfProductExists(Product product);

}