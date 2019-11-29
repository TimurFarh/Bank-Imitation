package testgroup.bankimitation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.bankimitation.model.Account;

import java.util.List;

@Repository
public class AccountDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Account> getAll(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Account where client.id = :clientId");
        query.setParameter("clientId", id);
        return query.list();
    }

    public void add(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(account);
    }

    public void delete(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(account);
    }

    public void edit(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.update(account);
    }

    public Account getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Account.class, id);
    }
}
