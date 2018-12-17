package com.lex4hex.securedShop.controller;

import com.lex4hex.securedShop.model.Customer;
import com.lex4hex.securedShop.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.PersistenceException;

@RestController
public class CustomerRestController {

    private final CustomerServiceImpl customerService;

    @Autowired
    public CustomerRestController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    /**
     * Create customer with provided name
     *
     * @param name Name of the customer to create
     */
    @RequestMapping(value = "/api/shop/customers/{name}", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@PathVariable("name") String name) {
        try {
            if (customerService.checkIfCustomerExists(name)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            Customer customer = new Customer();
            customer.setName(name);

            customerService.saveCustomer(customer);
        } catch (PersistenceException e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
