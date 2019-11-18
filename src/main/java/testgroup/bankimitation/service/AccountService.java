package testgroup.bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testgroup.bankimitation.dao.AccountDAO;
import testgroup.bankimitation.model.BankAccount;

import java.util.List;

@Service
public class AccountService {
    private AccountDAO accountDAO;

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public List<BankAccount> getAll() {
        return accountDAO.getAll();
    }

    public void add(BankAccount T) {

    }

    public void delete(BankAccount T) {

    }

    public void edit(BankAccount T) {

    }

    public BankAccount getById(int id) {
        return null;
    }
}
