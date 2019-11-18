package testgroup.bankimitation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.bankimitation.model.Client;

import java.util.List;


@Repository
public class ClientDAO implements GenericDAO<Client> {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Client> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Client").list();
    }

    @Override
    public void add(Client object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
    }

    @Override
    public void delete(Client object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    @Override
    public void edit(Client object) {
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
    }

    @Override
    public Client getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }
}
