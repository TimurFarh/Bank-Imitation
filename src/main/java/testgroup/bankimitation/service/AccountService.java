package testgroup.bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import testgroup.bankimitation.dao.GenericDAO;
import testgroup.bankimitation.model.BankAccount;

import java.util.List;

public class AccountService implements ServiceFactory<BankAccount> {
    private GenericDAO genericDAO;

    @Autowired
    public void setGenericDAO(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public List<BankAccount> getAll() {
        return genericDAO.getAll();
    }

    @Override
    public void add(BankAccount T) {

    }

    @Override
    public void delete(BankAccount T) {

    }

    @Override
    public void edit(BankAccount T) {

    }

    @Override
    public BankAccount getById(int id) {
        return null;
    }
}
