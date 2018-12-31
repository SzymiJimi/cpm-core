package com.thesis.cpmcore;

import com.thesis.cpmcore.configuration.DataBaseConfig;
import com.thesis.cpmcore.configuration.SecurityConfig;
import com.thesis.cpmcore.model.Action;
import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.repository.ActionRepository;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import java.sql.Timestamp;
import java.util.Calendar;

import static com.thesis.cpmcore.ItemsTest.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SecurityConfig.class, DataBaseConfig.class })
@WebAppConfiguration
@ComponentScan
@Transactional
public class ReservationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActionRepository actionRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void controllerBeanTest() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("actionController"));
    }

    @Test
    public void repositoryBeanTest() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("actionRepository"));
    }

    @Test
    public void userRepositoryBeanTest() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("userRepository"));
    }

    @Test
    public void itemRepositoryBeanTest() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("itemRepository"));
    }

    @Test
    public void addItemToDb() throws Exception{
        mockMvc.perform(post("/reservation/new")
                .content(asJsonString(createReservation()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    public void testWrongDates() throws Exception{
        mockMvc.perform(post("/reservation/new")
                .content(asJsonString(createIncorrectReservation()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddReservationAndGetThroughWeb() throws Exception {
        Action testReservation = this.actionRepository.save(createReservation());
        mockMvc.perform(get("/action/get/"+testReservation.getIdReservation()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.idReservation").exists());
    }

    @Test
    public void addReservationAndCloseThroughWeb() throws Exception {
        Action testReservation = this.actionRepository.save(createReservation());
        mockMvc.perform(post("/action/finish")
                .content(asJsonString(testReservation))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Action createReservation(){
        Action testReservation = new Action();
        testReservation.setFrom(new Timestamp(Calendar.getInstance().getTimeInMillis()+3600000));
        testReservation.setTo(new Timestamp(Calendar.getInstance().getTimeInMillis()+(36*3600000)));
        testReservation.setReason("TEST");
        testReservation.setType(Action.RESERVATION);
        testReservation.setReserverUser(this.userRepository.findByUsername("manager"));
        testReservation.setItemId(this.itemRepository.findById(1).orElse(new Item()));
        return testReservation;
    }

    private Action createIncorrectReservation(){
        Action testReservation = new Action();
        testReservation.setFrom(new Timestamp(Calendar.getInstance().getTimeInMillis()+3600000));
        testReservation.setTo(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        testReservation.setReason("TEST");
        testReservation.setType(Action.RESERVATION);
        testReservation.setReserverUser(this.userRepository.findByUsername("manager"));
        testReservation.setItemId(this.itemRepository.findById(1).orElse(new Item()));
        return testReservation;
    }

}
