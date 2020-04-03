package com.example.testtask;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author lobachev.nikolay
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TomcatConnectionPoolTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void givenTomcatConnectionPoolInstance_whenCheckedPoolClassName_thenCorrect() {
        assertEquals(dataSource.getClass().getName(), "org.apache.tomcat.jdbc.pool.DataSource");
    }
}
