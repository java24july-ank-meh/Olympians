package com.olympians.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*import com.olympians.beans.Person;
import com.olympians.beans.Bookmark;
import com.olympians.beans.Category;
import com.olympians.controllers.BookmarkController;
import com.olympians.controllers.UserController;
import com.olympians.controllers.MamaController;*/


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath: FILL IN .xml", "classpath: FILL IN .xml"})
public class ControllerIntegrationTests {
	
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

   /* @Test void test_name() {
    	this.mockMvc.perform(get("something"))
    	.param("key", "value")
        .andExpect( "something" );
        .andReturn();
        
         
		String content = result.getResponse().getContentAsString();
		assertEquals("something",content);
    }*/

}
