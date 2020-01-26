package controller;

import bankimitation.config.WebConfig;
import bankimitation.model.Account;
import bankimitation.model.Client;
import bankimitation.model.Operations;
import bankimitation.model.Transaction;
import bankimitation.service.AccountService;
import bankimitation.service.ClientService;
import bankimitation.service.TransactionService;
import config.TestHibernateConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestHibernateConfig.class, WebConfig.class})
@WebAppConfiguration
public class TransactionControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    private Client client;
    private Account account;
    private Transaction transaction;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        client = new Client("Ivan", "Ivanov", 33, "Moscow");
        account = new Account("Test", 50000, client);
        transaction = new Transaction(Operations.Deposit, "Test", "1", 10000, account, client);
        transaction.setDate(Date.valueOf(LocalDate.of(2019, 11, 28)));
        clientService.add(client);
        accountService.add(account);
        transactionService.add(transaction);
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testClientTransactions() throws Exception {
        Date after = Date.valueOf(LocalDate.of(2019, 11, 27));
        Date before = Date.valueOf(LocalDate.of(2019, 11, 29));
        this.mockMvc.perform(get("/client-transactions/{clientId}", client.getId())
                            .flashAttr("after", after).flashAttr("before", before))
                .andExpect(status().isOk())
                .andExpect(model().attribute("transactions", equalTo(Arrays.asList(transaction))))
                .andExpect(model().attribute("client", equalTo(client)))
                .andExpect(view().name("transaction/historyTransactions"));
    }

    @Test
    public void testAccountTransactions() throws Exception {
        this.mockMvc.perform(get("/account-transactions/{accountId}/{clientId}", account.getId(), client.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("transactions", equalTo(Arrays.asList(transaction))))
                .andExpect(model().attribute("client", client))
                .andExpect(view().name("transaction/historyTransactions"));
    }

    @Test
    public void testTransactionHandler() throws Exception {
        Transaction testDeposit = new Transaction(Operations.Deposit, "Test", "1", 10000, account, client);
        this.mockMvc.perform(post("/transaction/{clientId}/{accountId}", client.getId(), account.getId())
                            .flashAttr("transaction", testDeposit))
                .andExpect(status().isFound())
                .andExpect(model().attribute("transaction", equalTo(testDeposit)))
                .andExpect(view().name(String.format("redirect:/%d/accounts/", client.getId())));
        
        Transaction testWithdraw = new Transaction(Operations.Deposit, "Test", "1", 10000, account, client);
        this.mockMvc.perform(post("/transaction/{clientId}/{accountId}", client.getId(), account.getId())
                .flashAttr("transaction", testWithdraw))
                .andExpect(status().isFound());
        
        Transaction testClose = new Transaction(Operations.Deposit, "Test", "1", 10000, account, client);
        this.mockMvc.perform(post("/transaction/{clientId}/{accountId}", client.getId(), account.getId())
                .flashAttr("transaction", testClose))
                .andExpect(status().isFound());
    }
}
