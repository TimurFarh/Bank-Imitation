package controller;

import bankimitation.config.WebConfig;
import bankimitation.model.Client;
import bankimitation.service.ClientService;
import config.TestHibernateConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, TestHibernateConfig.class})
public class ClientControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ClientService clientService;

    private MockMvc mockMvc;
    private Client client;

    @Before
    public void setup() {
        client = new Client("Ivan", "Ivanov", 33, "Moscow");
        clientService.add(client);
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesClientController() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("clientController"));
    }

    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/listOfClients"))
                .andExpect(model().attributeExists("allClients"));
    }

    @Test
    public void testEditGet() throws Exception {
        this.mockMvc.perform(get("/edit/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("client", equalTo(clientService.getById(1))))
                .andExpect(view().name("client/addOrEditClient"));
    }

    @Test
    public void testEditPost() throws Exception {
        this.mockMvc.perform(post("/edit").param("id", "1"))
                .andExpect(status().isFound())
                .andExpect(model().attribute("client", equalTo(client)))
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void testAddGet() throws Exception {
        this.mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/addOrEditClient"));
    }

    @Test
    public void testAddPost() throws Exception {
        this.mockMvc.perform(post("/add"))
                .andExpect(status().isFound())
                .andExpect(model().attributeExists("client"))
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(get("/delete/{id}", 1))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/"));
    }
}
