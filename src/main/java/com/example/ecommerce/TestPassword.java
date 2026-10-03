package com.example.ecommerce;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPassword {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        String hash = encoder.encode("password123");

        System.out.println("BCrypt: " + hash);

        System.out.println(
                "Valid: " +
                encoder.matches("password123", hash)
        );
    }
}