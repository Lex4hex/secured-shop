package com.lex4hex.securedShop.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.List;

/**
 * The type Product metamodel.
 */
@StaticMetamodel(Order.class)
public class Order_ {

    /**
     * The constant id.
     */
    public static volatile SingularAttribute<Order, Integer> id;
    /**
     * The constant customer.
     */
    public static volatile SingularAttribute<Order, Customer> customer;
    /**
     * The constant products.
     */
    public static volatile SingularAttribute<Order, List<Product>> products;
}