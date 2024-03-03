package com.example.customermanagement.service;
import com.example.customermanagement.model.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Service
public class HibernateCustomerService implements ICustomerService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> fillALl() {
        String queryStr = "SELECT c FROM Customer as c";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr,Customer.class);
        return query.getResultList();
    }
    @Override
    public Customer findById(int id) {
        String queryStr = "select c from Customer as c where c.id=:id";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr,Customer.class);
        return query.getSingleResult();
    }

    @Override
    public void save(Customer customer) {
        Transaction transaction = null;
        Customer origin;
        if (customer.getId()==0) {
            origin = new Customer();
        } else {
            origin = findById(customer.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setName(customer.getName());
            origin.setEmail(customer.getEmail());
            origin.setAddress(customer.getAddress());
            session.saveOrUpdate(origin);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            if (transaction!=null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        String queryStr = "delete from Customer as c where c.id=:id";
        Query query = entityManager.createQuery(queryStr);
        query.setParameter("id",id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}