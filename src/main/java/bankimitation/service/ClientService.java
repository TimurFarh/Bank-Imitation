package bankimitation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bankimitation.dao.AbstractDao;
import bankimitation.dao.ClientDao;
import bankimitation.model.Client;

import java.util.List;

@Service
@Transactional
public class ClientService extends AbstractService<Client>{

	public List<Client> getAll() {
        return getDao().getAll();
    }

    public void edit(Client client) {
        getDao().edit(client);
    }
}
