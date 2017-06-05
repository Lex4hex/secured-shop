package com.lex4hex.securedShop.dao;

import com.lex4hex.securedShop.model.Cart;
import com.lex4hex.securedShop.model.Customer;
import com.lex4hex.securedShop.model.Customer_;
import com.lex4hex.securedShop.model.Order;
import com.lex4hex.securedShop.model.Order_;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderDAOImpl implements BaseDAO<Order>, OrderDAO {

    private final SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> findAllByCustomerId(Integer id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = criteriaBuilder.createQuery(Order.class);

        Root<Order> root = criteria.from(Order.class);
        criteria.select(root);

        Join<Order, Customer> customerJoin = root.join(Order_.customer);
        criteria.where(criteriaBuilder.equal(customerJoin.get(Customer_.id), id));

        List<Order> result;

        try {
            result = entityManager.createQuery(criteria).getResultList();
        } catch (NoResultException e) {
            return null;
        }

        return result;
    }

    @Override
    public Order findProductsByOrderId(Integer id) {
        return null;
    }

    @Override
    public Order findById(Integer id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = criteriaBuilder.createQuery(Order.class);

        Root<Order> root = criteria.from(Order.class);
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get(Order_.id), id));

        Order result;

        try {
            result = entityManager.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return result;
    }

    @Override
    public List<Order> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Order";
        TypedQuery<Order> query = entityManager.createQuery(hql, Order.class);

        return query.getResultList();
    }

    @Override
    public void save(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(order);
    }

    @Override
    public void createOrder(Integer customerId) {
        Session session = sessionFactory.getCurrentSession();
        Order order = new Order();

        Customer customer = session.get(Customer.class, customerId);
        Cart cart = customer.getCart();

        order.setCustomer(customer);
        order.setProducts(cart.getProducts());

        // Empty cart and
        customer.setCart(null);
        session.delete(cart);

        session.merge(order);
    }

    @Override
    public void update(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(order);
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Order order = session.load(Order.class, id);
        session.delete(order);
    }
}
