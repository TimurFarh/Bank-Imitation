package bankimitation.dao;

import org.springframework.stereotype.Repository;
import bankimitation.model.Client;

@Repository
public class ClientDao extends AbstractDao<Client> {

	public ClientDao() {
		setClazz(Client.class);
	}
}
