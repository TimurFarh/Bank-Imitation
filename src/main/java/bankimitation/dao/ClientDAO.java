package bankimitation.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import bankimitation.model.Client;

import java.util.List;


@Repository
public class ClientDAO {
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

    public void add(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(client);
    }

    public void delete(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(client);
    }

    public void edit(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.update(client);
    }

    public Client getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }
}
