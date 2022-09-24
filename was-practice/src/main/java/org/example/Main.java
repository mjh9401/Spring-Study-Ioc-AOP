package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new CustomWebApplicationSever(7070).start();
    }
}