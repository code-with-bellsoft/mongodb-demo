package dev.cyberjar.service;

import dev.cyberjar.MongodbDemoApp;
import dev.cyberjar.entity.Civilian;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(classes = MongodbDemoApp.class)
class CivilianServiceTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

    @Autowired
    private CivilianService civilianService;

    @BeforeAll
    static void startContainerBeforeAll() {
        mongoDBContainer.start();
    }

    @AfterAll
    static void stopContainerAfterAll() {
        mongoDBContainer.stop();
    }

    @Test
    void shouldFindCivilianByNationalId() {
        Civilian civilian = civilianService.getCivilianByNationalId("NP-59909166-Wg");
        String name = "Paula Lin";
        assertEquals(name, civilian.getLegalName());
    }

    @Test
    void shouldFindCiviliansByLotNumber() {
        List<Civilian> civilians = civilianService.getCiviliansByLotNumber(536);
        int expected = 2;
        assertEquals(expected, civilians.size());
    }



}