package com.example.demo.util;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonUtilTest {


    @Test
    void testChekValuesSetsName() {
        CommonUtil util = new CommonUtil();

        ReflectionTestUtils.invokeMethod(util, "chekValues");

        String name = (String) ReflectionTestUtils.getField(util, "name");
        assertEquals("Siva", name);
    }


    @Test
    void testPrintNames() {
        CommonUtil util = new CommonUtil();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        ReflectionTestUtils.invokeMethod(util, "printNames");

        System.setOut(originalOut);
        String output = outContent.toString();
        assertTrue(output.contains("Name: Siva"));
    }
}
