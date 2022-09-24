package org.example;

import org.example.calculator.ClinetRequestHandler;
import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationSever {
    private final int port;
    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationSever.class);

    public CustomWebApplicationSever(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("{CustomWebApplicationServer} started {} port.", port);

            Socket clientSocket;
            logger.info("{CustomWebApplicationServer} wating for client");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("{CustomWebApplicationServer} client connected!");

                /**
                 * Step 2 - 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
                 */
                new Thread(new ClinetRequestHandler(clientSocket)).start();
            }

        }
    }
}

