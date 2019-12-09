package bankimitation.controller;

import bankimitation.error.NotEnoughMoneyException;
import bankimitation.error.WrongAccountException;
import bankimitation.service.AccountService;
import bankimitation.service.ClientService;
import bankimitation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import bankimitation.model.Account;
import bankimitation.model.Client;
import bankimitation.model.Transaction;

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

    @PostMapping(value = "/transaction/{clientId}/{accountId}")
    public ModelAndView transactionsHandler(@ModelAttribute("transaction") Transaction transaction, @PathVariable int clientId, @PathVariable int accountId) {
        ModelAndView modelAndView = new ModelAndView();
        Account account = accountService.getById(accountId);
        Client client = clientService.getById(clientId);
        transaction.setAccount(account);
        transaction.setClient(client);
        try {
            transactionService.add(transaction);
            Transaction depositTransaction = accountService.edit(account, transaction);
            if (depositTransaction != null) {
                transactionService.add(depositTransaction);
            }
            modelAndView.setViewName(String.format("redirect:/%d/accounts/", client.getId()));
        } catch (NotEnoughMoneyException | WrongAccountException exception) {
            transactionService.delete(transaction);
            modelAndView.setViewName("information/errors");
            modelAndView.addObject(client);
            modelAndView.addObject("error", exception.getClass().getSimpleName());
        }
        return modelAndView;
    }
}
