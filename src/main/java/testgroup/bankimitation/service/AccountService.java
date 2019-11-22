package testgroup.bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.bankimitation.dao.AccountDAO;
import testgroup.bankimitation.model.BankAccount;
import testgroup.bankimitation.model.Transaction;

import java.util.List;

@Service
@Transactional
public class AccountService {
    private AccountDAO accountDAO;

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public List<BankAccount> getAll(int id) {
        return accountDAO.getAll(id);
    }

    public void add(BankAccount account) {
        accountDAO.add(account);
    }

    public void delete(BankAccount account) {
        accountDAO.delete(account);
    }

    public void edit(int id, int operation) {
        BankAccount account = getById(id);
        Transaction transaction = account.getTransactions().get(account.getTransactions().size() - 1);
        switch (operation) {
            case 0: account.setBalance(account.getBalance() + transaction.getAmount());
        }
        accountDAO.edit(account);
    }

    public BankAccount getById(int id) {
        return accountDAO.getById(id);
    }
}
