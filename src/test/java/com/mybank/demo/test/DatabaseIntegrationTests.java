package com.mybank.demo.test;

import com.mybank.demo.DemoApplication;
import com.mybank.demo.test.config.DatabaseIntegrationTestsConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ActiveProfiles("DatabaseIT")
@SpringBootTest(classes = { DemoApplication.class, DatabaseIntegrationTestsConfig.class })
@Transactional
@Rollback
@EnableJpaRepositories(basePackages = "com.mybank.demo.dal.inmemory.repository")
public abstract class DatabaseIntegrationTests {
}
