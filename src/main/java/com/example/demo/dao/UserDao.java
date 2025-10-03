package com.example.demo.dao;

import com.example.demo.data.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserDao {
    private final List<User> users = Arrays.asList(
            new User(1L, "Alice", 50000.0),
            new User(2L, "Bob", 60000.0),
            new User(3L, "Charlie", 70000.0)
    );

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
