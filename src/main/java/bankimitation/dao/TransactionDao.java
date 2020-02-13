package bankimitation.dao;

import org.springframework.stereotype.Repository;

import bankimitation.model.Transaction;

@Repository
public class TransactionDao extends AbstractDao<Transaction>{
    
	public TransactionDao() {
		setClazz(Transaction.class);
	}
}
