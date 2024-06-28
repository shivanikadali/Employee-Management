package com.example.Employee.Management.App;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import com.example.Employee.Management.App.entity.Employee;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;

    @Autowired
    private TestH2Repository h2Repository;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/employee");
    }

    @Test
    public void testAddEmployee() {

        Employee employee = new Employee("thanshi", "wounderland", "thanshi@gmail.com");
        Employee response = restTemplate.postForObject(baseUrl, employee, Employee.class);
        assertEquals("thanshi", response.getFirstName());
        // here we are added only 1 so we are count the records in db
        assertEquals(1, h2Repository.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO employee_details(first_name, last_name, email)VALUES('tony', 'from', 'heaven');\r\n"
            + //
            "", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO employee_details (first_name, last_name,email) VALUES ('bhanu', 'kommu', 'kom.gmail.com')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

    @Sql(statements = "DELETE FROM employee_details WHERE first_name='bhanu'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

    @Sql(statements = "DELETE FROM employee_details WHERE first_name='tony'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetEmployees() {
        List<Employee> employees = restTemplate.getForObject(baseUrl, List.class);
        assertEquals(2, employees.size());
        assertEquals(2, h2Repository.findAll().size());
    }
}