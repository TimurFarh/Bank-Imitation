package bankimitation.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import bankimitation.model.Transaction;

import java.util.List;

@Repository
public class TransactionDAO implements GenericDAO<Transaction>{
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Transaction> query = session.createQuery("from Transaction");
        return query.list();
    }

	@Override
    public void add(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(transaction);
    }

    @Override
    public Transaction getById (int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Transaction.class, id);
    }


	@Override
	public void edit(Transaction transaction) {
		Session session = sessionFactory.getCurrentSession();
		session.update(transaction);
	}
}
