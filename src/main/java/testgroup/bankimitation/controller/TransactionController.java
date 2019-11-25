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
        BankAccount account = accountService.getById(accountId);
        Client client = clientService.getById(clientId);
        transaction.setAccount(account);
        transaction.setClient(client);
        transactionService.add(transaction);
        modelAndView.setViewName(String.format("redirect:/transaction-account/%d/%d/%d/%d", clientId, accountId, operation, transaction.getId()));
        return modelAndView;
    }

    //TODO If after or before will be null
    @GetMapping(value = "/transactions/{client}/{clientId}")
    public ModelAndView getClientTransactions(@PathVariable int clientId, @ModelAttribute("after") Date after, @ModelAttribute("before") Date before) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transaction/historyTransactions");
        List<Transaction> transactions = transactionService.getAllByClient(clientId, after, before);
        modelAndView.addObject("transactions", transactions);
        return modelAndView;
    }

    @GetMapping(value = "/transactions/{accountId}")
    public ModelAndView getAccountTransactions(@PathVariable int accountId) {
        ModelAndView modelAndView = new ModelAndView();
        List<Transaction> transactions = transactionService.getAllByAccount(accountId);
        modelAndView.setViewName("transaction/historyTransactions");
        modelAndView.addObject("transactions", transactions);
        return modelAndView;
    }
}
