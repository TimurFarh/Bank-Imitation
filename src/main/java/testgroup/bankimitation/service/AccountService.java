package testgroup.bankimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.bankimitation.dao.AccountDAO;
import testgroup.bankimitation.exception.NotEnoughMoneyException;
import testgroup.bankimitation.exception.WrongAccountException;
import testgroup.bankimitation.exception.WrongAmountException;
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

    public void edit(BankAccount account, int operation, Transaction transaction) throws NotEnoughMoneyException, WrongAccountException, WrongAmountException {
        if (transaction.getAmount() < 0) throw new WrongAmountException();
        switch (operation) {
            case 0: //deposit
                if (transaction.getFrom().matches("\\d+")) throw new WrongAccountException();
                account.setBalance(account.getBalance() + transaction.getAmount());
                break;

            case 1: //withdraw
                if (account.getBalance() < transaction.getAmount()) throw new NotEnoughMoneyException();
                checkDepositAccount(account, transaction);
                account.setBalance(account.getBalance() - transaction.getAmount());
                break;

            case 2: //close
                checkDepositAccount(account, transaction);
                accountDAO.delete(account);
                return;
        }
        accountDAO.edit(account);
    }

    public BankAccount getById(int id) {
        return accountDAO.getById(id);
    }

    private void checkDepositAccount(BankAccount account, Transaction transaction) throws WrongAccountException {
        try {
            int depositNumber = Integer.parseInt(transaction.getTo());
            if (depositNumber == account.getId()) throw new WrongAccountException();
            BankAccount depositAccount = getById(depositNumber);
            if (depositAccount == null) throw new WrongAccountException();
            depositAccount.setBalance(depositAccount.getBalance() + transaction.getAmount());
        } catch (NumberFormatException e) {
        }
    }


}
