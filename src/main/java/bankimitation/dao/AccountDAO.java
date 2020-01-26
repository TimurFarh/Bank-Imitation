package bankimitation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import bankimitation.model.Account;

import java.util.List;

@Repository
public class AccountDAO implements GenericDAO<Account>{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Account> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("from Account");
        return query.list();
    }

    @Override
    public void add(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(account);
    }

    @Override
    public void delete(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(account);
    }

    @Override
    public void edit(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.update(account);
    }

    @Override
    public Account getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Account.class, id);
    }
}
