package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean   // 👈 adds a mock UserService bean
    private UserService userService;

    @Test
    void testHello() throws Exception {
        // Mock service response
//        given(userService.getAllUsers()).willReturn("Hello World!");

        mockMvc.perform(get("/api/users/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }
}
