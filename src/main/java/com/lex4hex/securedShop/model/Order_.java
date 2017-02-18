package com.lex4hex.securedShop.model;

import java.util.List;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

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