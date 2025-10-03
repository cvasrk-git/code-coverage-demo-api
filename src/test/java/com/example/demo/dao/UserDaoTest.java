package com.example.demo.dao;

import com.example.demo.dao.UserDao;
import com.example.demo.data.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {


    private UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDao();
    }

    @Test
    void testGetAllUsers() {
        List<User> users = userDao.getAllUsers();

        assertThat(users).hasSize(3);
        assertThat(users).extracting(User::getUserName)
                .containsExactly("Alice", "Bob", "Charlie");
    }

    @Test
    void testGetUserById_existingUser() {
        User user = userDao.getUserById(2L);

        assertThat(user).isNotNull();
        assertThat(user.getUserName()).isEqualTo("Bob");
    }

    @Test
    void testGetUserById_nonExistingUser() {
        User user = userDao.getUserById(99L);

        assertThat(user).isNull();
    }
}
