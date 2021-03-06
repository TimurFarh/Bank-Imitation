package bankimitation.controller;

import bankimitation.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import bankimitation.model.Client;

import java.util.List;

@Controller
public class ClientController {
    private ClientService service;

    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public ModelAndView allClients() {
        List<Client> clients = service.getAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/listOfClients");
        modelAndView.addObject("allClients", clients);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editClient(@PathVariable("id") int id) {
        Client client = service.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/addOrEditClient");
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
    public ModelAndView addClient() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/addOrEditClient");
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
