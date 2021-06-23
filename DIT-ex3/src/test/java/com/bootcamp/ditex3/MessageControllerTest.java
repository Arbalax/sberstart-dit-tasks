package com.bootcamp.ditex3;

import com.bootcamp.ditex3.controller.MessageController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql("/schema.sql")
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MessageController messageController;

    @Test
    public void testControllerIsPresent() {
        Assertions.assertThat(messageController).isNotNull();
    }

    @Test
    public void testOfferMessage() throws Exception {
        this.mockMvc.perform(post("/messages/offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":123,\"msg\":\"First Test Message\"}"))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/messages/offer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":123,\"msg\":\"First Test Message\"}"))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void testPollMessage() throws Exception {

        this.mockMvc.perform(get("/messages/poll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"queueId\":1,\"id\":777,\"msg\":\"777 Message\",\"isInQueue\":false,\"isModified\":false}"));

        this.mockMvc.perform(get("/messages/poll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"queueId\":2,\"id\":888,\"msg\":\"888 Message\",\"isInQueue\":false,\"isModified\":false}"));
    }

    @Test
    public void testPeekMessage() throws Exception {

        this.mockMvc.perform(get("/messages/peek"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"queueId\":1,\"id\":777,\"msg\":\"777 Message\",\"isInQueue\":true,\"isModified\":false}"));

        this.mockMvc.perform(get("/messages/peek"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"queueId\":1,\"id\":777,\"msg\":\"777 Message\",\"isInQueue\":true,\"isModified\":false}"));
    }

    @Test
    public void testPeekMaxMessage() throws Exception {

        this.mockMvc.perform(get("/messages/peekMax"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"queueId\":2,\"id\":888,\"msg\":\"888 Message\",\"isInQueue\":true,\"isModified\":false}"));
    }

    @Test
    public void testPeekAllMessage() throws Exception {

        this.mockMvc.perform(get("/messages/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"queueId\":1,\"id\":777,\"msg\":\"777 Message\",\"isInQueue\":true,\"isModified\":false}," +
                        "{\"queueId\":2,\"id\":888,\"msg\":\"888 Message\",\"isInQueue\":true,\"isModified\":false}]"));
    }
}
