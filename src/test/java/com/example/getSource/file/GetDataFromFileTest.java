package com.example.getSource.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetDataFromFileTest {

    GetDataFromFile getDataFromFile = new GetDataFromFile("\\target\\classes\\animal.txt");

    @Test
    void get() {
        List<String> actual = getDataFromFile.get();
        System.out.println(actual);
        List<String> expected = new ArrayList<>();
        expected.add("Dog,Brown,true");
        expected.add("Cat,Green,true");
        expected.add("Bird,Blue,false");

//        Assertions.assertArrayEquals( expected, actual);


    }
}