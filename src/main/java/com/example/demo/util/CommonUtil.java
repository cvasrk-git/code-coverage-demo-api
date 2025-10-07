package com.example.demo.util;

public class CommonUtil {
    String name;
    private void chekValues() {
     name = "Siva";
    }

    public void printNames() {
        chekValues();
        System.out.println("Name: " + name);
    }

}
