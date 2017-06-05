package com.lex4hex.securedShop.service;

import com.lex4hex.securedShop.dao.CartDAOImpl;
import com.lex4hex.securedShop.model.Cart;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartServiceImpl implements CartService {

    private final CartDAOImpl cartDAO;

    @Autowired
    public CartServiceImpl(CartDAOImpl cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    public Cart findById(Integer id) {
        return cartDAO.findById(id);
    }

    @Override
    public void saveCart(Cart cart) {
        cartDAO.save(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        cartDAO.update(cart);
    }

    @Override
    public void deleteCartById(Integer id) {
        cartDAO.deleteById(id);
    }

    @Override
    public void addProduct(Integer cartId, Integer productId) {
        cartDAO.addProduct(cartId, productId);
    }

    @Override
    public List<Cart> findAllCarts() {
        return cartDAO.findAll();
    }

    @Override
    public Cart findByCustomerId(Integer id) {
        return cartDAO.findByCustomerId(id);
    }
}
