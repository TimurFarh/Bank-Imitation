package bankimitation.dao;

import org.springframework.stereotype.Repository;
import bankimitation.model.Account;

@Repository
public class AccountDao extends AbstractDao<Account>{
	
	public AccountDao() {
		setClazz(Account.class);
	}
}
