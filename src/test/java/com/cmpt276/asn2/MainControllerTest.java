package com.cmpt276.asn2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class MainControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void test_getIndex() throws Exception {
        mockMvc.perform(get("/ratings"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("ratings"));
    }

    @Test
    void test_validData() throws Exception {
        mockMvc.perform(post("/ratings")
                        .param("staffName", "Admin")
                        .param("email", "admin@example.com")
                        .param("profile.role", "STUFF")
                        .param("clarityScore", "5")
                        .param("nicenessScore", "5")
                        .param("knowledgeabilityScore", "5"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void test_invalidData() throws Exception {
        mockMvc.perform(post("/ratings")
                        .param("staffName", "")
                        .param("email", "teststring")
                        .param("profile.role", "TA")
                        .param("clarityScore", "99")
                        .param("nicenessScore", "5")
                        .param("knowledgeabilityScore", "128"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"));
    }
}
