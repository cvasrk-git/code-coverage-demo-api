package com.example.demo.data;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    void testUserLombokMethods() {
        // no-arg constructor + setters
        User user1 = new User();
        user1.setUserId(1L);
        user1.setUserName("John");
        user1.setSalary(50000.0);

        // all-arg constructor
        User user2 = new User(1L, "John", 50000.0);

        // getters
        assertThat(user1.getUserId()).isEqualTo(1L);
        assertThat(user1.getUserName()).isEqualTo("John");
        assertThat(user1.getSalary()).isEqualTo(50000.0);

        // toString
        assertThat(user1.toString()).contains("John");

        // equals & hashCode
        assertThat(user1).isEqualTo(user2);
        assertThat(user1.hashCode()).isEqualTo(user2.hashCode());

        // canEqual
        assertThat(user1.canEqual(user2)).isTrue();

        // change value and check inequality
        user2.setUserName("Alice");
        assertThat(user1).isNotEqualTo(user2);
        assertThat(user1.hashCode()).isNotEqualTo(user2.hashCode());
    }

    @Test
    void testEqualsEdgeCases() {
        User user1 = new User(1L, "John", 50000.0);

        // equals with null
        assertThat(user1).isNotEqualTo(null);

        // equals with different class
        assertThat(user1).isNotEqualTo("some string");

        // equals with same instance
        assertThat(user1).isEqualTo(user1);

        // hashCode consistency
        int initialHash = user1.hashCode();
        assertThat(user1.hashCode()).isEqualTo(initialHash);
    }

    @Test
    void testEqualsAndHashCodeEdgeCases() {
        User user1 = new User(1L, "John", 50000.0);
        User user2 = new User(1L, "John", 50000.0);
        User user3 = new User(2L, "Alice", 60000.0);

        // equals: same instance
        assertThat(user1).isEqualTo(user1);

        // equals: different object, same values
        assertThat(user1).isEqualTo(user2);
        assertThat(user1.hashCode()).isEqualTo(user2.hashCode());

        // equals: different values
        assertThat(user1).isNotEqualTo(user3);

        // equals: null
        assertThat(user1).isNotEqualTo(null);

        // equals: different class
        assertThat(user1).isNotEqualTo("string");

        // canEqual branch: override to false scenario
        User userWithDifferentClass = new User(1L, "John", 50000.0) {
            @Override
            public boolean canEqual(Object other) {
                return false;
            }
        };
        assertThat(user1).isNotEqualTo(userWithDifferentClass);

        // hashCode null fields
        User userWithNulls = new User(null, null, null);
        assertThat(userWithNulls.hashCode()).isNotZero(); // forces the null branches
    }


}
