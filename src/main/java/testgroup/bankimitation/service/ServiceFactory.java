package testgroup.bankimitation.service;

import testgroup.bankimitation.model.Client;

import java.util.List;

public interface ServiceFactory {
    List<Client> clients();
    void add(Client client);
    void delete (Client client);
    void edit (Client client);
    Client getById (int id);
}
