package com.lex4hex.securedShop.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "cart", schema = "public", catalog = "shop")
public class Cart {
    private int           id;
    private Timestamp     creationTime;
    private List<Product> products;
    private Customer      customer;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "creation_time", insertable = false, updatable = false)
    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cart that = (Cart) o;

        return id == that.id &&
               (creationTime != null ? creationTime.equals(that.creationTime) : that.creationTime == null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "cart_products", joinColumns = @JoinColumn(name = "cart_id"),
               inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
