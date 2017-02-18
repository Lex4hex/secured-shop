package com.lex4hex.securedShop.model;

import java.math.BigDecimal;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * The type Product metamodel.
 */
@StaticMetamodel(Product.class)
public class Product_ {

  /**
   * The constant id.
   */
  public static volatile SingularAttribute<Product, Integer> id;
  /**
   * The constant name.
   */
  public static volatile SingularAttribute<Product, String> name;
  /**
   * The constant price.
   */
  public static volatile SingularAttribute<Product, BigDecimal> price;
}