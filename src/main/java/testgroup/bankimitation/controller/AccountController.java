package testgroup.bankimitation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import testgroup.bankimitation.model.BankAccount;
import testgroup.bankimitation.model.Client;
import testgroup.bankimitation.service.AccountService;

import java.util.List;

@Controller
public class AccountController {
    private AccountService service;

    @Autowired
    public void setService(AccountService service) {
        this.service = service;
    }

    @GetMapping(value = "/accounts/{client}")
    public ModelAndView accountsOfClient(@PathVariable Client client) {
        List<BankAccount> accounts = service.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account/bankAccountsOfClient");
        modelAndView.addObject("accounts", accounts);
        return modelAndView;
    }
}
