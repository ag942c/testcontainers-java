package com.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmpControllerTest {
    private MockMvc mvc;

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");
  /*  spring.datasource.driver-class-name: com.mysql.jdbc.Driver
    spring.datasource.url: jdbc:mysql://localhost:3306/testcontainer
    spring.datasource.username: root
    spring.datasource.password: root*/
    @DynamicPropertySource
    static void configDymanic(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username",mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password",mySQLContainer::getPassword);

    }

    static PostgreSQLContainer PostgreSQLContainer111 = new PostgreSQLContainer<>("postgres:alpine3.20");


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void checkNoOfRecords() throws Exception {
        List<Emp> emps = this.restTemplate.getForObject("http://localhost:" + port + "/emps",
                List.class);

        Assertions.assertThat("==================" + emps.size());
    }

    @Test
    void save1Record() throws Exception {

        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/emps", Emp.builder().name("TEXT_NAME"+new Date()).build(),
                Emp.class));
    }




}
