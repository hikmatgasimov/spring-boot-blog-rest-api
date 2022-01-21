package com.springboot.blog.utils;

public class PasswordEncoderGenerotar {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("passowrd"));
        System.out.println(passwordEncoder.encode("admin"));
    }
}
