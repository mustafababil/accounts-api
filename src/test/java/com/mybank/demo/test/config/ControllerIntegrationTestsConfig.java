package com.mybank.demo.test.config;

import com.mybank.demo.service.AccountService;
import com.mybank.demo.service.CustomerService;
import com.mybank.demo.service.TransactionService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@Profile("ControllerIT")
@WebMvcTest
@AutoConfigureMockMvc
@EnableAutoConfiguration()
@ComponentScan(basePackages = { "com.mybank.demo" })
public class ControllerIntegrationTestsConfig {
    @MockBean
    AccountService accountService;

    @MockBean
    TransactionService transactionService;

    @MockBean
    CustomerService customerService;
}