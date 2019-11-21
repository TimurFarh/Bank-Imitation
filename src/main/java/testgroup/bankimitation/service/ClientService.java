package testgroup.bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.bankimitation.dao.ClientDAO;

import testgroup.bankimitation.model.Client;

import java.util.List;

@Service
@Transactional
public class ClientService {
    private ClientDAO clientDAO;

    @Autowired
    public void setClientDAO(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public List<Client> getAll() {
        return clientDAO.getAll();
    }

    public void add(Client client) {
        clientDAO.add(client);
    }

    public void delete(Client client) {
        clientDAO.delete(client);
    }

    public void edit(Client client) {
        clientDAO.edit(client);
    }

    public Client getById(int id) {
        return (Client) clientDAO.getById(id);
    }
}
