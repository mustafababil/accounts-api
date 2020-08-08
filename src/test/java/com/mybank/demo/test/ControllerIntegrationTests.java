package com.mybank.demo.test;

import com.mybank.demo.test.config.ControllerIntegrationTestsConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@ActiveProfiles("ControllerIT")
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {ControllerIntegrationTestsConfig.class})
public abstract class ControllerIntegrationTests {

}