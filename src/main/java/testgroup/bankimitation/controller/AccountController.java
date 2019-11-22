package testgroup.bankimitation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import testgroup.bankimitation.model.BankAccount;

import testgroup.bankimitation.model.Client;
import testgroup.bankimitation.model.Operations;
import testgroup.bankimitation.service.AccountService;
import testgroup.bankimitation.service.ClientService;

import java.util.List;

@Controller
public class AccountController {
    private AccountService accountService;
    private ClientService clientService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }


    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/{client}/{clientId}/accounts")
    public ModelAndView accountsOfClient(@PathVariable("clientId") int clientId) {
        List<BankAccount> accounts = accountService.getAll(clientId);
        Client client = clientService.getById(clientId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account/bankAccountsOfClient");
        modelAndView.addObject("accounts", accounts);
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @GetMapping(value = "/deposit-account/{clientId}/{accountId}")
    public ModelAndView depositToAccount(@PathVariable int accountId, @PathVariable int clientId) {
        BankAccount account = accountService.getById(accountId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transaction/transaction");
        modelAndView.addObject("account", account);
        modelAndView.addObject("operation", Operations.DEPOSIT);
        return modelAndView;
    }

    @GetMapping(value = "/deposit-account/{clientId}/{accountId}/{operation}")
    public ModelAndView depositedToAccount(@PathVariable int accountId, @PathVariable int operation, @PathVariable int clientId) {
        ModelAndView modelAndView = new ModelAndView();
        BankAccount account = accountService.getById(accountId);
        accountService.edit(accountId, operation);
        Client client = clientService.getById(clientId);
        modelAndView.setViewName(String.format("redirect:/%s/%d/accounts/", client, client.getId()));
        return modelAndView;
    }

    @GetMapping(value ="/add-new-account/{id}")
    public ModelAndView addAccount(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.getById(id);
        modelAndView.addObject("client", client);
        modelAndView.setViewName("account/addNewAccount");
        return modelAndView;
    }

    @PostMapping(value = "/add-new-account/{id}")
    public ModelAndView addAccount(@ModelAttribute("account") BankAccount account, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.getById(id);
        account.setClient(client);
        accountService.add(account);
        modelAndView.setViewName(String.format("redirect:/%s/%d/accounts/", client, client.getId()));
        return modelAndView;
    }

    @GetMapping(value = "/delete-account/{accountId}/{clientId}")
    public ModelAndView deleteAccount(@PathVariable int accountId, @PathVariable int clientId) {
        ModelAndView modelAndView = new ModelAndView();
        BankAccount account = accountService.getById(accountId);
        modelAndView.addObject("method", "close");
        modelAndView.addObject("account", account);
        accountService.delete(account);
        Client client = clientService.getById(clientId);
        modelAndView.setViewName(String.format("redirect:/%s/%d/accounts/", client, client.getId()));
        return modelAndView;
    }
}
