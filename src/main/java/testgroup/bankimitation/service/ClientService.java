package testgroup.bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.bankimitation.dao.ClientDAO;

import testgroup.bankimitation.model.Client;

import java.util.List;

@Service
public class ClientService {
    private ClientDAO clientDAO;

    @Autowired
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Transactional
    public List<Client> getAll() {
        return clientDAO.getAll();
    }

    @Transactional
    public void add(Client client) {
        clientDAO.add(client);
    }

    @Transactional
    public void delete(Client client) {
        clientDAO.delete(client);
    }

    @Transactional
    public void edit(Client client) {
        clientDAO.edit(client);
    }

    @Transactional
    public Client getById(int id) {
        return (Client) clientDAO.getById(id);
    }
}
