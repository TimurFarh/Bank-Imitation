package service;

import bankimitation.dao.AccountDAO;
import bankimitation.error.NotEnoughMoneyException;
import bankimitation.error.WrongAccountException;
import bankimitation.model.Account;
import bankimitation.model.Client;
import bankimitation.model.Operations;
import bankimitation.model.Transaction;
import bankimitation.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
	private Client client = new Client("Ivan", "Ivanov", 33, "Moscow");
    private final Account account1 = new Account("New Life", 50000, client);
    private final Account account2 = new Account("Travel", 10000, client);

    @Mock
    private AccountDAO accountDAOMock;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void getAllTest() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        client.setId(1);
        when(accountDAOMock.getAll()).thenReturn(Arrays.asList(account1, account2));
        List<Account> testAccounts = accountService.getAll(client.getId());
        Assert.assertEquals(accounts, testAccounts);
    }

    @Test
    public void depositTest() throws WrongAccountException, NotEnoughMoneyException {
        Transaction transaction = new Transaction(Operations.Deposit, "Sberbank", "1", 2000);
        account2.setBalance(52000);
        accountService.edit(account1, transaction);
        Assert.assertEquals(account2.getBalance(), account1.getBalance());
        verify(accountDAOMock).edit(account1);
    }

    @Test
    public void withdrawTest() throws WrongAccountException, NotEnoughMoneyException {
        Transaction transaction = new Transaction(Operations.Withdraw, "1", "PayPal", 2000);
        account2.setBalance(48000);
        accountService.edit(account1, transaction);
        Assert.assertEquals(account2.getBalance(), account1.getBalance());
        verify(accountDAOMock).edit(account1);
    }

    @Test
    public void closeTest() throws WrongAccountException, NotEnoughMoneyException {
        Transaction transaction = new Transaction(Operations.Close, "1", "PayPal", account1.getBalance());
        doThrow(new Exception("Account deleted")).when(accountDAOMock).delete(account1);
        accountService.edit(account1, transaction);
    }

    @Test
    public void getByIdTest() {
        when(accountDAOMock.getById(1)).thenReturn(account1);
        Account testAccount = accountService.getById(1);
        Assert.assertEquals(account1, testAccount);
    }
}
