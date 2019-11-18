package testgroup.bankimitation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.bankimitation.model.BankAccount;

import java.util.List;

@Repository
public class AccountDAO implements GenericDAO<BankAccount>{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BankAccount> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("").list();
    }

    @Override
    public void add(BankAccount object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
    }

    @Override
    public void delete(BankAccount object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    @Override
    public void edit(BankAccount object) {
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
    }

    @Override
    public BankAccount getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(BankAccount.class, id);
    }
}
