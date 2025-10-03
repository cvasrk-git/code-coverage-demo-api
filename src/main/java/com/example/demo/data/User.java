package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   // getters, setters, toString, equals, hashCode
@NoArgsConstructor      // no-arg constructor
@AllArgsConstructor     // all-arg constructor
public class User {

    private Long   userId;
    private String userName;
    private Double salary;

}
