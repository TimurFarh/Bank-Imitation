package testgroup.bankimitation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import testgroup.bankimitation.model.Account;
import testgroup.bankimitation.model.Client;
import testgroup.bankimitation.model.Transaction;
import testgroup.bankimitation.service.AccountService;
import testgroup.bankimitation.service.ClientService;
import testgroup.bankimitation.service.TransactionService;

import java.sql.Date;
import java.util.List;

@Controller
public class TransactionController {
    private AccountService accountService;
    private TransactionService transactionService;
    private ClientService clientService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/transaction/{clientId}/{accountId}/{operation}")
    public ModelAndView addTransaction(@ModelAttribute Transaction transaction, @PathVariable int accountId, @PathVariable int operation, @PathVariable int clientId) {
        ModelAndView modelAndView = new ModelAndView();
        Account account = accountService.getById(accountId);
        Client client = clientService.getById(clientId);
        transaction.setAccount(account);
        transaction.setClient(client);
        transactionService.add(transaction);
        modelAndView.setViewName(String.format("redirect:/transaction-account/%d/%d/%d/%d", clientId, accountId, operation, transaction.getId()));
        return modelAndView;
    }

    @GetMapping(value = "/client-transactions/{clientId}")
    public ModelAndView getClientTransactions(@PathVariable int clientId, @ModelAttribute("after") Date after, @ModelAttribute("before") Date before) {
        ModelAndView modelAndView = new ModelAndView();
        List<Transaction> transactions = transactionService.getAllByClient(clientId, after, before);
        Client client = clientService.getById(clientId);
        modelAndView.setViewName("transaction/historyTransactions");
        modelAndView.addObject("transactions", transactions);
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @GetMapping(value = "/account-transactions/{accountId}/{clientId}")
    public ModelAndView getAccountTransactions(@PathVariable int accountId, @PathVariable int clientId) {
        ModelAndView modelAndView = new ModelAndView();
        List<Transaction> transactions = transactionService.getAllByAccount(accountId);
        Client client = clientService.getById(clientId);
        modelAndView.setViewName("transaction/historyTransactions");
        modelAndView.addObject("transactions", transactions);
        modelAndView.addObject("client", client);
        return modelAndView;
    }
}
