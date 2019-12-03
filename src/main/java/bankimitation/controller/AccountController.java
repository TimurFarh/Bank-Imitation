package bankimitation.controller;

import bankimitation.service.AccountService;
import bankimitation.service.ClientService;
import bankimitation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import bankimitation.error.NotEnoughMoneyException;
import bankimitation.error.WrongAccountException;
import bankimitation.model.Account;

import bankimitation.model.Client;
import bankimitation.model.Operations;
import bankimitation.model.Transaction;

import java.util.List;

@Controller
public class AccountController {
    private AccountService accountService;
    private ClientService clientService;
    private TransactionService transactionService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }


    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/{client}/{clientId}/accounts")
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
        modelAndView.addObject(client);
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
        modelAndView.addObject(client);
        return modelAndView;
    }

    @GetMapping(value = "/delete-account/{clientId}/{accountId}")
    public ModelAndView deleteAccount(@PathVariable int accountId, @PathVariable int clientId) {
        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.getById(clientId);
        Account account = accountService.getById(accountId);
        modelAndView.addObject("operation", Operations.Close);
        modelAndView.addObject("account", account);
        modelAndView.setViewName("transaction/transaction");
        modelAndView.addObject(client);
        return modelAndView;
    }

    @GetMapping(value = "/transaction-account/{clientId}/{accountId}/{operation}/{transactionId}")
    public ModelAndView transactionHandler(@PathVariable int accountId, @PathVariable int operation, @PathVariable int clientId, @PathVariable int transactionId) {
        ModelAndView modelAndView = new ModelAndView();
        Account account = accountService.getById(accountId);
        Transaction transaction = transactionService.getById(transactionId);
        Client client = clientService.getById(clientId);
        try {
            Transaction depositTransaction = accountService.edit(account, operation, transaction);
            if (depositTransaction != null) {
                transactionService.add(depositTransaction);
            }
            modelAndView.setViewName(String.format("redirect:/%s/%d/accounts/", client, client.getId()));
        } catch (NotEnoughMoneyException | WrongAccountException exception) {
            transactionService.delete(transaction);
            modelAndView.setViewName("information/errors");
            modelAndView.addObject(client);
            modelAndView.addObject("error", exception.getClass().getSimpleName());
        }
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
    public ModelAndView addAccount(@ModelAttribute("account") Account account, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.getById(id);
        account.setClient(client);
        accountService.add(account);
        modelAndView.setViewName(String.format("redirect:/%s/%d/accounts/", client, client.getId()));
        return modelAndView;
    }
}
