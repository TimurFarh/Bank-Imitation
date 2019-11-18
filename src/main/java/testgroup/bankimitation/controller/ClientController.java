package testgroup.bankimitation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import testgroup.bankimitation.model.Client;
import testgroup.bankimitation.service.ServiceFactory;

import java.util.List;

@Controller
//@RequestMapping(value = "")
public class ClientController {
    private ServiceFactory service;

    @Autowired
    public void setService(ServiceFactory service) {
        this.service = service;
    }


    @GetMapping(value = "/")
    public ModelAndView allClients() {
        List<Client> clients = service.clients();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("listOfClients");
        modelAndView.addObject("allClients", clients);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        Client client = service.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addNewClient");
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView editClient(@ModelAttribute("client") Client client) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        service.edit(client);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addNewClient");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addClient(@ModelAttribute("client") Client client) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        service.add(client);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteClient(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Client client = service.getById(id);
        service.delete(client);
        return modelAndView;
    }
}
