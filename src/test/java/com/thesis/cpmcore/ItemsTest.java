package com.thesis.cpmcore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.cpmcore.configuration.DataBaseConfig;
import com.thesis.cpmcore.configuration.SecurityConfig;
import com.thesis.cpmcore.model.Item;
import com.thesis.cpmcore.repository.ItemRepository;
import com.thesis.cpmcore.repository.LocationRepository;
import com.thesis.cpmcore.repository.UserRepository;
import org.junit.*;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SecurityConfig.class, DataBaseConfig.class })
@WebAppConfiguration
@ComponentScan
@Transactional
public class ItemsTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ItemRepository itemRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void appContextTest() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("itemController"));
    }

    @Test
    public void addItemToDb() throws Exception{
        mockMvc.perform(post("/item/new")
                .content(asJsonString(createItem()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    public void getItemTest()
            throws Exception {
        this.mockMvc.perform(get("/item/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.idItem").exists());
    }

    @Test
    public void disableItems() {
        createAndAddItem();
        List<Item> items = getItemsToDisable();
        items.forEach(item -> {
            try {
                this.mockMvc.perform(get("/item/disable/"+ item.getIdItem())).andExpect(status().isOk());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testNullItem() throws Exception {
        mockMvc.perform(post("/item/new")
                .content(asJsonString(null))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isBadRequest());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Item createItem(){
        Item testItem = new Item();
        testItem.setBrand("TEST");
        testItem.setModel("TEST");
        testItem.setCreationUser(this.userRepository.findByUsername("manager"));
        testItem.setLocation(this.locationRepository.findByUnitHeadIdUser(4));
        testItem.setActive(1);
        return testItem;
    }

    private void createAndAddItem(){
        this.itemRepository.save(createItem());
    }

    private List<Item> getItemsToDisable(){
        List<Item> items = this.itemRepository.findAllByBrand("TEST");
        items.forEach(item -> {item.setActive(0);});
        return items;
    }


}
