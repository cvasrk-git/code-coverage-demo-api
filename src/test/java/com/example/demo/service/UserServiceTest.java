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
        User user1 = new User(1L, "John", 50000.0);
        User user2 = new User(2L, "Alice", 60000.0);

        // Mock DAO getUserById
        when(userDao.getUserById(1L)).thenReturn(user1);
        when(userDao.getUserById(2L)).thenReturn(user2);
        when(userDao.getUserById(3L)).thenReturn(null); // non-existing user

        // Act
        User result1 = userService.getUserById(1L);
        User result2 = userService.getUserById(2L);
        User result3 = userService.getUserById(3L);

        // Assert
        assertThat(result1).isNotNull();
        assertThat(result1.getUserName()).isEqualTo("John");

        assertThat(result2).isNotNull();
        assertThat(result2.getUserName()).isEqualTo("Alice");

        assertThat(result3).isNull();
    }

    @Test
    void testUserGettersAndSetters() {
        User user = new User(1L, "John", 50000.0);
        assertThat(user.getUserId()).isEqualTo(1L);
        assertThat(user.getUserName()).isEqualTo("John");
        assertThat(user.getSalary()).isEqualTo(50000.0);
    }

    @Test
    void testNoArgConstructor() {
        UserService service = new UserService();
        assertThat(service).isNotNull();
    }

}
