package controller;

import bankimitation.config.WebConfig;
import bankimitation.model.Account;
import bankimitation.model.Client;
import bankimitation.model.Operations;
import bankimitation.service.AccountService;
import bankimitation.service.ClientService;
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

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, TestHibernateConfig.class})
public class AccountControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientService clientService;

    private MockMvc mockMvc;
    private Account account;
    private Client client;

    @Before
    public void setup() {
        client = new Client("Ivan", "Ivanov", 33, "Moscow");
        account = new Account("Test", 50000, client);
        clientService.add(client);
        accountService.add(account);
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testAccountOfClient() throws Exception {
        this.mockMvc.perform(get("/{clientId}/accounts", 1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("client", equalTo(clientService.getById(1))))
                .andExpect(model().attributeExists("accounts"))
                .andExpect(view().name("account/accountsOfClient"));
    }

    @Test
    public void testDepositToAccount() throws Exception {
        this.mockMvc.perform(get("/deposit-account/{clientId}/{accountId}", client.getId(), account.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("client", equalTo(client)))
                .andExpect(model().attribute("account", equalTo(account)))
                .andExpect(model().attribute("operation", equalTo(Operations.Deposit)))
                .andExpect(view().name("transaction/transaction"));
    }

    @Test
    public void testWithdrawFromAccount() throws Exception {
        this.mockMvc.perform(get("/withdraw-account/{clientId}/{accountId}", client.getId(), account.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("client", equalTo(client)))
                .andExpect(model().attribute("account", equalTo(account)))
                .andExpect(model().attribute("operation", equalTo(Operations.Withdraw)))
                .andExpect(view().name("transaction/transaction"));
    }

    @Test
    public void testDeleteAccount() throws Exception{
        this.mockMvc.perform(get("/delete-account/{clientId}/{accountId}", client.getId(), account.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("client", equalTo(client)))
                .andExpect(model().attribute("account", equalTo(account)))
                .andExpect(model().attribute("operation", equalTo(Operations.Close)))
                .andExpect(view().name("transaction/transaction"));
    }

    @Test
    public void testAddAccountGet() throws Exception {
        this.mockMvc.perform(get("/add-new-account/{id}", client.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("client", equalTo(client)))
                .andExpect(view().name("account/addNewAccount"));
    }

    @Test
    public void testAddAccountPost() throws Exception {
        this.mockMvc.perform(post("/add-new-account/{id}", client.getId()))
                .andExpect(status().isFound())
                .andExpect(model().attributeExists("account"))
                .andExpect(view().name(String.format("redirect:/%d/accounts/", client.getId())));
    }
}