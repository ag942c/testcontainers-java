package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class DemoControllerTest111   {


 static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");
    static PostgreSQLContainer PostgreSQLContainer111 = new PostgreSQLContainer<>("postgres:alpine3.20");

    @Test
    void simpleTest() {
        String fooResource = "/foo";

        System.out.println(PostgreSQLContainer111);
    }


}
