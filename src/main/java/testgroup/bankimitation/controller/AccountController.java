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

    @GetMapping(value = "/{client}/{id}/accounts/")
    public ModelAndView accountsOfClient(@PathVariable("id") int id) {
        List<BankAccount> accounts = accountService.getAll(id);
        Client client = clientService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account/bankAccountsOfClient");
        modelAndView.addObject("accounts", accounts);
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @GetMapping(value = "/deposit-account/{numberOfAccount}")
    public ModelAndView depositToAccount(@PathVariable int numberOfAccount) {
        BankAccount account = accountService.getById(numberOfAccount);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account/transaction");
        modelAndView.addObject("account", account);
        return modelAndView;
    }

    @PostMapping(value = "/account")
    public ModelAndView depositToAccount(@ModelAttribute("account") BankAccount account) {
        return null;
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
    public ModelAndView addAccount(@ModelAttribute("account") BankAccount account, @PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Client client = clientService.getById(id);
        account.setClient(client);
        accountService.add(account);
        modelAndView.setViewName(String.format("redirect:/%s/%d/accounts/", client, client.getId()));
        return modelAndView;
    }
}
