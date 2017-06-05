package com.lex4hex.securedShop.service;

import com.lex4hex.securedShop.model.Order;
import java.util.List;

public interface OrderService {

    Order findById(Integer id);

    void saveOrder(Order order);

    void updateOrder(Order order);

    void deleteOrderById(Integer id);

    void createOrder(Integer customer);

    List<Order> findAllOrders();

    List<Order> findAllByCustomerId(Integer id);


}