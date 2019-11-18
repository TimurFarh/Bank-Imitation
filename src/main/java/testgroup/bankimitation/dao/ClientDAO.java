package testgroup.bankimitation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.bankimitation.model.Client;

import java.util.List;


@Repository
public class ClientDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Client> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Client").list();
    }

    public void add(Client object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
    }

    public void delete(Client object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    public void edit(Client object) {
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
    }

    public Client getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }
}
