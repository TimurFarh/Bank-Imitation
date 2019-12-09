package bankimitation.controller;

import bankimitation.service.AccountService;
import bankimitation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import bankimitation.model.Account;

import bankimitation.model.Client;
import bankimitation.model.Operations;

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

    @GetMapping(value = "/{clientId}/accounts")
    public ModelAndView accountsOfClient(@PathVariable("clientId") int clientId) {
        ModelAndView modelAndView = new ModelAndView();
        List<Account> accounts = accountService.getAll(clientId);
        Client client = clientService.getById(clientId);
        modelAndView.setViewName("account/accountsOfClient");
        modelAndView.addObject("accounts", accounts);
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @GetMapping(value = "/deposit-account/{clientId}/{accountId}")
    public ModelAndView depositToAccount(@PathVariable int accountId, @PathVariable int clientId) {
        ModelAndView modelAndView = new ModelAndView();
        Account account = accountService.getById(accountId);
        Client client = clientService.getById(clientId);
        modelAndView.setViewName("transaction/transaction");
        modelAndView.addObject("account", account);
        modelAndView.addObject("operation", Operations.Deposit);
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @GetMapping(value = "/withdraw-account/{clientId}/{accountId}")
    public ModelAndView withdrawFromAccount(@PathVariable int accountId, @PathVariable int clientId) {
        Account account = accountService.getById(accountId);
        Client client = clientService.getById(clientId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transaction/transaction");
        modelAndView.addObject("account", account);
        modelAndView.addObject("operation", Operations.Withdraw);
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @GetMapping(value = "/delete-account/{clientId}/{accountId}")
    public ModelAndView deleteAccount(@PathVariable int accountId, @PathVariable int clientId) {
        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.getById(clientId);
        Account account = accountService.getById(accountId);
        modelAndView.addObject("operation", Operations.Close);
        modelAndView.addObject("account", account);
        modelAndView.addObject("client", client);
        if (account.getBalance() != 0) modelAndView.setViewName("transaction/transaction");
        else {
            modelAndView.setViewName(String.format("redirect:/%d/accounts/", client.getId()));
            accountService.delete(account);
        }
        return modelAndView;
    }

    @GetMapping(value ="/add-new-account/{id}")
    public ModelAndView addAccount(@PathVariable("id") int clientId) {
        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.getById(clientId);
        modelAndView.addObject("client", client);
        modelAndView.setViewName("account/addNewAccount");
        return modelAndView;
    }

    @PostMapping(value = "/add-new-account/{clientId}")
    public ModelAndView addAccount(@ModelAttribute("account") Account account, @PathVariable int clientId) {
        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.getById(clientId);
        account.setClient(client);
        accountService.add(account);
        modelAndView.setViewName(String.format("redirect:/%d/accounts/", clientId));
        return modelAndView;
    }
}
