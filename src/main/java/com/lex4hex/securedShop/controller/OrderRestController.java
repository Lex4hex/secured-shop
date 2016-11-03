package com.lex4hex.securedShop.controller;

import com.lex4hex.securedShop.model.Customer;
import com.lex4hex.securedShop.model.Order;
import com.lex4hex.securedShop.service.CustomerServiceImpl;
import com.lex4hex.securedShop.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.PersistenceException;
import java.util.List;

@RestController
public class OrderRestController {
    private final CustomerServiceImpl customerService;
    private final OrderServiceImpl    orderService;

    @Autowired
    public OrderRestController(CustomerServiceImpl customerService,
                               OrderServiceImpl orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    /**
     * Get list of all orders with related products
     */
    @RequestMapping(value = "/api/shop/orders", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> listAllOrders() {
        List<Order> orders;

        try {
            orders = orderService.findAllOrders();
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (orders.isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    /**
     * Create order for customer with provided ID. After order creation delete cart of customer.
     *
     * @param customerId ID of customer to create order for
     */
    @RequestMapping(value = "/api/shop/customer/{id}/order", method = RequestMethod.POST)
    public ResponseEntity<Void> createOrder(@PathVariable("id") int customerId) {
        Customer customer;

        try {
            customer = customerService.findById(customerId);
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (customer == null) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND);
        }

        orderService.createOrder(customerId);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
