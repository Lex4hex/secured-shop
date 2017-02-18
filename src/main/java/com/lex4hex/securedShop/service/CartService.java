package com.lex4hex.securedShop.service;

import com.lex4hex.securedShop.model.Cart;
import java.util.List;

public interface CartService {

  Cart findById(Integer id);

  void saveCart(Cart cart);

  void updateCart(Cart cart);

  void deleteCartById(Integer id);

  void addProduct(Integer cartId, Integer productId);

  List<Cart> findAllCarts();

  Cart findByCustomerId(Integer id);

}