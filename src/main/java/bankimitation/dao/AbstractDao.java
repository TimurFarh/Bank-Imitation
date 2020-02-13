package bankimitation.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> {
	private SessionFactory sessionFactory;
	protected Class<T> clazz;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected final void setClazz(final Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	public void add(T object) {
		sessionFactory.getCurrentSession().persist(object);
	}

	public void delete(T object) {
		sessionFactory.getCurrentSession().delete(object);
	}

	public void edit(T object) {
		sessionFactory.getCurrentSession().update(object);
	}

	public T getById(int id) {
		return sessionFactory.getCurrentSession().get(clazz, id);
	}
}
