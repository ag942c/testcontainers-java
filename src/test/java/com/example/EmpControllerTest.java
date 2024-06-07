package com.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Date;
import java.util.List;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmpControllerTest {
    private MockMvc mvc;

 /*   @Container
    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");*/

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest").withDatabaseName("testcontainer").withUsername("root").withPassword("root");

    /*  spring.datasource.driver-class-name: com.mysql.jdbc.Driver
      spring.datasource.url: jdbc:mysql://localhost:3306/testcontainer
      spring.datasource.username: root
      spring.datasource.password: root*/
    @DynamicPropertySource
    static void configDymanic(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name", mySQLContainer::getDriverClassName);
        dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);

    }

 /*   static PostgreSQLContainer PostgreSQLContainer111 = new PostgreSQLContainer<>("postgres:alpine3.20");*/


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    static public void beforeAlltests() {
        System.out.println("mySQLContainer.getJdbcUrl() " + mySQLContainer.getJdbcUrl());
        System.out.println("mySQLContainer.getContainerId() " + mySQLContainer.getContainerId());
        System.out.println("mySQLContainer.getPassword() " + mySQLContainer.getPassword());
        System.out.println("mySQLContainer.getUsername() " + mySQLContainer.getUsername());
        System.out.println("mySQLContainer.getDatabaseName() " + mySQLContainer.getDatabaseName());
        System.out.println("mySQLContainer.getDriverClassName() " + mySQLContainer.getDriverClassName());
        System.out.println("mySQLContainer.getContainerName() " + mySQLContainer.getContainerName());
        System.out.println("mySQLContainer.getDockerImageName() " + mySQLContainer.getDockerImageName());

        System.out.println("mySQLContainer.getTestQueryString() " + mySQLContainer.getTestQueryString());
        System.out.println("mySQLContainer.getHost() " + mySQLContainer.getHost());
        System.out.println("mySQLContainer.getWorkingDirectory() " + mySQLContainer.getWorkingDirectory());


    }

    @Test
    void checkNoOfRecords() throws Exception {

        System.out.println("Number of records " + mySQLContainer.getWorkingDirectory());
        List<Emp> emps = this.restTemplate.getForObject("http://localhost:" + port + "/emps",
                List.class);

        Assertions.assertThat("==================" + emps.size());
    }

    @Test
    void save1Record() throws Exception {
        List<Emp> emps = getEmps();
        System.out.println("Before Save " + emps == null ? 0 : emps.size());

        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/emps", Emp.builder().name("TEXT_NAME" + new Date()).build(),
                Emp.class));
        getEmps();

        System.out.println("After Save " + emps == null ? 0 : emps.size());
    }


    private List<Emp> getEmps() throws Exception {

        // System.out.println("Number of records starts ");
        List<Emp> emps = this.restTemplate.getForObject("http://localhost:" + port + "/emps",
                List.class);
        // System.out.println("Number of records ends  =======> "+emps == null ?0 : emps.size());
        return emps;
    }


}
