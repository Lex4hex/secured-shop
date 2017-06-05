package com.lex4hex.securedShop.dao;


import com.lex4hex.securedShop.model.Order;
import java.util.List;

public interface OrderDAO {

    /**
     * Find all orders by customer ID.
     *
     * @param id - ID of customer to search order with
     * @return All orders for provided customer
     */
    List<Order> findAllByCustomerId(Integer id);

    /**
     * Find all products by order ID.
     *
     * @param id - ID of customer to search order with
     * @return All orders for provided customer
     */
    Order findProductsByOrderId(Integer id);

    /**
     * Create an order for provided customer.
     *
     * @param customerId - ID of customer to create order for.
     */
    void createOrder(Integer customerId);
}
