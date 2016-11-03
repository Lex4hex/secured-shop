package com.lex4hex.securedShop.dao;


import com.lex4hex.securedShop.model.Customer;

interface CustomerDAO {
    Customer findByName(String name);

    boolean checkIfCustomerExists(String name);
}
