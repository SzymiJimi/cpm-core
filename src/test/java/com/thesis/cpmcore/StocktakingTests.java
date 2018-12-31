package com.thesis.cpmcore;

import com.thesis.cpmcore.configuration.DataBaseConfig;
import com.thesis.cpmcore.configuration.SecurityConfig;
import com.thesis.cpmcore.model.Location;
import com.thesis.cpmcore.model.Stocktaking;
import com.thesis.cpmcore.repository.LocationRepository;
import com.thesis.cpmcore.repository.StocktakingRepository;
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

import static com.thesis.cpmcore.ItemsTest.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SecurityConfig.class, DataBaseConfig.class })
@WebAppConfiguration
@ComponentScan
@Transactional
public class StocktakingTests {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private StocktakingRepository stocktakingRepository;

    @Autowired
    private UserRepository userRepository;

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
        Assert.assertNotNull(wac.getBean("stocktakingController"));
    }

    @Test
    public void repositoryBeanTest() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("stocktakingRepository"));
    }

    @Test
    public void locationRepositoryBeanTest() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("locationRepository"));
    }

    @Test
    public void userRepositoryBeanTest() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("userRepository"));
    }


    @Test
    public void newStockTakingTest() throws Exception {
        mockMvc.perform(post("/stocktaking/new")
                .content(asJsonString(createTestStocktaking()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void createAndFinishStocktaking() throws Exception {
        Stocktaking stocktaking = this.stocktakingRepository.save(createTestStocktaking());
        mockMvc.perform(post("/stocktaking/finish")
                .content(asJsonString(stocktaking))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    private Stocktaking createTestStocktaking(){
        Stocktaking stocktaking = new Stocktaking();
        stocktaking.setType("TEST");
        stocktaking.setMethod("TEST");
        stocktaking.setLocation(this.locationRepository.findById(1).orElse(new Location()));
        stocktaking.setManager(this.userRepository.findByUsername("manager"));
        return stocktaking;
    }



}
