package testgroup.bankimitation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import testgroup.bankimitation.model.BankAccount;
import testgroup.bankimitation.model.Transaction;
import testgroup.bankimitation.service.AccountService;
import testgroup.bankimitation.service.TransactionService;

@Controller
public class TransactionController {
    private AccountService accountService;
    private TransactionService transactionService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping (value = "/transaction/{clientId}/{accountId}/{operation}")
    public ModelAndView addTransaction(@ModelAttribute Transaction transaction, @PathVariable int accountId, @PathVariable int operation, @PathVariable int clientId) {
        ModelAndView modelAndView = new ModelAndView();
        BankAccount account = accountService.getById(accountId);
        transaction.setAccount(account);
        transactionService.add(transaction);
        modelAndView.setViewName(String.format("redirect:/transaction-account/%d/%d/%d/%d",clientId, accountId, operation, transaction.getId()));
        return modelAndView;
    }
}
