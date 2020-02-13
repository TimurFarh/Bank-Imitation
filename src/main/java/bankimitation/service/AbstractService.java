package bankimitation.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import bankimitation.dao.AbstractDao;

@Transactional
public abstract class AbstractService<T> {
	private AbstractDao<T> dao;
	
	@Autowired
	public void setDao(AbstractDao<T> dao) {
		this.dao = dao;
	}
	
	protected AbstractDao<T> getDao() {
		return dao;
	}
	
	public void add(T object) {
		dao.add(object);
	}
	
	public void delete(T object) {
		dao.delete(object);
	}
	
	public T getById(int id) {
		return dao.getById(id);
	}
}
