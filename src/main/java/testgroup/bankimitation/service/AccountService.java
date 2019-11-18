package testgroup.bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.bankimitation.dao.AccountDAO;
import testgroup.bankimitation.model.BankAccount;
import testgroup.bankimitation.model.Client;

import java.util.List;

@Service
public class AccountService {
    private AccountDAO accountDAO;

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Transactional
    public List<BankAccount> getAll(int id) {
        return accountDAO.getAll(id);
    }

    @Transactional
    public void add(BankAccount account) {
        accountDAO.add(account);
    }

    @Transactional
    public void delete(BankAccount account) {
        accountDAO.delete(account);
    }

    @Transactional
    public void edit(BankAccount account) {
        accountDAO.edit(account);
    }

    @Transactional
    public BankAccount getById(int id) {
        return accountDAO.getById(id);
    }
}
