package com.lex4hex.securedShop.dao;


import com.lex4hex.securedShop.model.Product;

interface ProductDAO {

    Product findByName(String name);

    /**
     * Check if provided product exists
     *
     * @param product product object
     * @return boolean
     */
    boolean checkIfProductExists(Product product);

}
