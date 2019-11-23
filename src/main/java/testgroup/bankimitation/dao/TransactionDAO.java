package testgroup.bankimitation.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.bankimitation.model.Transaction;

import java.util.List;

@Repository
public class TransactionDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Transaction> getAll(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Transaction where account.id = :id");
        query.setParameter("id", id);
        return query.list();
    }

    public void add(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(transaction);
    }

    public void delete(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(transaction);
    }

    public Transaction getById (int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Transaction.class, id);
    }
}
