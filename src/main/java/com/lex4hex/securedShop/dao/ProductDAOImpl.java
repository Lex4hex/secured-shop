package com.lex4hex.securedShop.dao;

import com.lex4hex.securedShop.model.Product;
import com.lex4hex.securedShop.model.Product_;
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
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl implements BaseDAO<Product>, ProductDAO {

    private final SessionFactory sessionFactory;
    private final EntityManager  entityManager;

    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.entityManager = sessionFactory.createEntityManager();
    }

    @Override
    public Product findById(Integer id) {
        CriteriaBuilder        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria        = criteriaBuilder.createQuery(Product.class);

        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get(Product_.id), id));

        Product result;

        try {
            result = entityManager.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return result;
    }

    @Override
    public Product findByName(String name) {
        CriteriaBuilder        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria        = criteriaBuilder.createQuery(Product.class);

        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);
        criteria.where(criteriaBuilder.like(root.get(Product_.name), name));

        Product result;

        try {
            result = entityManager.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        return result;
    }

    @Override
    public boolean checkIfProductExists(Product product) {
        CriteriaBuilder        criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria        = criteriaBuilder.createQuery(Product.class);

        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);
        criteria.where(criteriaBuilder.like(root.get(Product_.name), product.getName()));

        Product result;

        try {
            result = entityManager.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return false;
        }

        return result != null;
    }

    @Override
    public List<Product> findAll() {
        Session             session = sessionFactory.getCurrentSession();
        String              hql     = "from Product";
        TypedQuery<Product> query   = session.createQuery(hql, Product.class);

        return query.getResultList();
    }

    @Override
    public void save(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(product);
    }

    @Override
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
    }

    @Override
    public void deleteById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.load(Product.class, id);
        session.delete(product);
    }
}