package com.lex4hex.securedShop.dao;

import com.lex4hex.securedShop.model.Customer;
import com.lex4hex.securedShop.model.Customer_;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerDAOImpl implements BaseDAO<Customer>, CustomerDAO {

    private final SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Customer findById(Integer id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = criteriaBuilder.createQuery(Customer.class);

        Root<Customer> root = criteria.from(Customer.class);
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get(Customer_.id), id));

        Customer result;

        try {
            result = entityManager.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return result;
    }

    @Override
    public Customer findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = criteriaBuilder.createQuery(Customer.class);

        Root<Customer> root = criteria.from(Customer.class);
        criteria.select(root);
        criteria.where(criteriaBuilder.like(root.get(Customer_.name), name));

        Customer result;

        try {
            result = entityManager.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return result;
    }

    @Override
    public boolean checkIfCustomerExists(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = criteriaBuilder.createQuery(Customer.class);

        Root<Customer> root = criteria.from(Customer.class);
        criteria.select(root);
        criteria.where(criteriaBuilder.like(root.get(Customer_.name), name));

        Customer result;

        try {
            result = entityManager.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return false;
        }

        return result != null;
    }

    @Override
    public List<Customer> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Customer";
        TypedQuery<Customer> query = entityManager.createQuery(hql, Customer.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(customer);

    }

    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.load(Customer.class, id);

        if (customer != null) {
            session.delete(customer);
        }
    }
}
