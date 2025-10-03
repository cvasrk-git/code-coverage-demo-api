package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.data.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User(1L, "John", 50000.0);
        User user2 = new User(2L, "Alice", 60000.0);
        when(userDao.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<User> users = userService.getAllUsers();

        // Assert
        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::getUserName)
                .containsExactly("John", "Alice");
    }

    @Test
    void testGetUserById() {
        // Arrange
        User user = new User(1L, "John", 50000.0);
        when(userDao.getUserById(1L)).thenReturn(user);

        // Act
        User result = userService.getUserById(1L);

        // Assert in test class
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.getUserName()).isEqualTo("John");
    }

}
