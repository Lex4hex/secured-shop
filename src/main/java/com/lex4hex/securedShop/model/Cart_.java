package com.lex4hex.securedShop.model;

import java.util.List;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * The type Product metamodel.
 */
@StaticMetamodel(Product.class)
public class Cart_ {

    /**
     * The constant id.
     */
    public static volatile SingularAttribute<Cart, Integer> id;
    /**
     * The constant customer.
     */
    public static volatile SingularAttribute<Cart, Customer> customer;
    /**
     * The constant products.
     */
    public static volatile SingularAttribute<Cart, List<Product>> products;
}