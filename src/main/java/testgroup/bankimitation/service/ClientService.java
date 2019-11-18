package testgroup.bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.bankimitation.dao.GenericDAO;
import testgroup.bankimitation.model.Client;

import java.util.List;

@Service
public class ClientService implements ServiceFactory<Client> {
    private GenericDAO clientDAO;

    @Autowired
    public void setClientDAO(GenericDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    @Transactional
    public List<Client> getAll() {
        return clientDAO.getAll();
    }

    @Override
    @Transactional
    public void add(Client client) {
        clientDAO.add(client);
    }

    @Override
    @Transactional
    public void delete(Client client) {
        clientDAO.delete(client);
    }

    @Override
    @Transactional
    public void edit(Client client) {
        clientDAO.edit(client);
    }

    @Override
    @Transactional
    public Client getById(int id) {
        return (Client) clientDAO.getById(id);
    }
}
