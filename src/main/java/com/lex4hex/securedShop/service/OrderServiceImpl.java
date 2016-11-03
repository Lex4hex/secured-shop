package com.lex4hex.securedShop.service;

import com.lex4hex.securedShop.dao.OrderDAOImpl;
import com.lex4hex.securedShop.model.Order;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private final OrderDAOImpl orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAOImpl orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    @Transactional
    public List<Order> findAllOrders() {
        List<Order> orders = orderDAO.findAll();
        orders.forEach(order -> Hibernate.initialize(order.getProducts()));

        return orders;
    }

    @Override
    public Order findById(Integer id) {
        return orderDAO.findById(id);
    }

    @Override
    public void saveOrder(Order order) {
        orderDAO.save(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.update(order);
    }

    @Override
    public void deleteOrderById(Integer id) {
        orderDAO.deleteById(id);
    }

    @Override
    public void createOrder(Integer customer) {
        orderDAO.createOrder(customer);
    }

    @Override
    public List<Order> findAllByCustomerId(Integer id) {
        return orderDAO.findAllByCustomerId(id);
    }
}
