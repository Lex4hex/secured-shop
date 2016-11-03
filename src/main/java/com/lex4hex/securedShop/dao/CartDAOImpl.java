package com.lex4hex.securedShop.dao;

import com.lex4hex.securedShop.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class CartDAOImpl implements BaseDAO<Cart>, CartDAO {

    private final SessionFactory sessionFactory;
    private final EntityManager  entityManager;

    @Autowired
    public CartDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.entityManager = sessionFactory.createEntityManager();
    }

    @Override
    public Cart findById(Integer id) {
        CriteriaBuilder     criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cart> criteria        = criteriaBuilder.createQuery(Cart.class);

        Root<Cart> root = criteria.from(Cart.class);
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get(Cart_.id), id));

        Cart result;

        try {
            result = entityManager.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return result;
    }

    @Override
    public List<Cart> findAll() {
        Session          session = sessionFactory.getCurrentSession();
        String           hql     = "from Cart";
        TypedQuery<Cart> query   = session.createQuery(hql, Cart.class);

        return query.getResultList();
    }

    @Override
    public void save(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(cart);
    }

    @Override
    public void update(Cart cart) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cart);
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Cart    cart    = session.load(Cart.class, id);

        if (cart != null) {
            session.delete(cart);
        }
    }

    @Override
    public Cart findByCustomerId(Integer id) {
        CriteriaBuilder     criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cart> criteria        = criteriaBuilder.createQuery(Cart.class);

        Root<Cart> root = criteria.from(Cart.class);
        criteria.select(root);

        Join<Cart, Customer> customerJoin = root.join(Cart_.customer);
        criteria.where(criteriaBuilder.equal(customerJoin.get(Customer_.id), id));

        Cart result;

        try {
            result = entityManager.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return result;
    }

    @Override
    public void addProduct(Integer cartId, Integer productId) {
        Session session = sessionFactory.getCurrentSession();

        Cart    cart    = session.find(Cart.class, cartId);
        Product product = session.find(Product.class, productId);

        if (cart != null && product != null) {
            cart.getProducts().add(product);
            session.persist(cart);
        }
    }

}