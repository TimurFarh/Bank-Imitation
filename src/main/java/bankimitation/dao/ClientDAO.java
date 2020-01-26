package bankimitation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import bankimitation.model.Client;

import java.util.List;


@Repository
public class ClientDAO implements GenericDAO<Client>{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Client").list();
    }

    @Override
    public void add(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(client);
    }

    @Override
    public void delete(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(client);
    }

    @Override
    public void edit(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.update(client);
    }

    @Override
    public Client getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }
}
