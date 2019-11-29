package testgroup.bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.bankimitation.dao.AccountDAO;
import testgroup.bankimitation.error.NotEnoughMoneyException;
import testgroup.bankimitation.error.WrongAccountException;
import testgroup.bankimitation.model.Account;
import testgroup.bankimitation.model.Operations;
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

    public List<Account> getAll(int id) {
        return accountDAO.getAll(id);
    }

    public void add(Account account) {
        accountDAO.add(account);
    }

    public Transaction edit(Account account, int operation, Transaction transaction) throws NotEnoughMoneyException, WrongAccountException {
        Transaction depositTransaction = null;
        switch (operation) {
            case 0: //deposit
                if (transaction.getFrom().matches("\\d+")) throw new WrongAccountException();
                account.setBalance(account.getBalance() + transaction.getAmount());
                break;

            case 1: //withdraw
                if (account.getBalance() < transaction.getAmount()) throw new NotEnoughMoneyException();
                depositTransaction = checkDepositAccount(account, transaction);
                account.setBalance(account.getBalance() - transaction.getAmount());
                break;

            case 2: //close
                depositTransaction = checkDepositAccount(account, transaction);
                accountDAO.delete(account);
                break;
        }
        accountDAO.edit(account);
        return depositTransaction;
    }

    public Account getById(int id) {
        return accountDAO.getById(id);
    }

    private Transaction checkDepositAccount(Account account, Transaction transaction) throws WrongAccountException {
        try {
            int depositNumber = Integer.parseInt(transaction.getTo());
            if (depositNumber == account.getId()) throw new WrongAccountException();
            Account depositAccount = getById(depositNumber);
            if (depositAccount == null) throw new WrongAccountException();
            depositAccount.setBalance(depositAccount.getBalance() + transaction.getAmount());
            Transaction depositTransaction = new Transaction(Operations.Deposit, transaction.getTo(), transaction.getFrom(), transaction.getAmount(), depositAccount, depositAccount.getClient());
            return depositTransaction;
        } catch (NumberFormatException e) {
        }
        return null;
    }
}
