package com.example.demo.controller;

import com.example.demo.data.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceTest;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean   // 👈 this creates a mock bean in Spring context
    private UserService userService;

    @Test
    public void testHello() throws Exception {

        // Mock service response
//        given(userService.getAllUsers()).willReturn("Hello World!");

        mockMvc.perform(get("/api/users/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    void testGetUsers() throws Exception {
        List<User> users = Arrays.asList(new User(1L, "John", 50000.0));
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userName").value("John"));
    }

    @Test
    void testGetUserByIdEndpoint() throws Exception {
        User user = new User(1L, "John", 50000.0);
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.userName").value("John"))
                .andExpect(jsonPath("$.salary").value(50000.0));
    }
}
