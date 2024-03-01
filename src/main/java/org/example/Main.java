package org.example;

import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Faker faker = new Faker();
        System.out.println(faker.funnyName());
    }
}