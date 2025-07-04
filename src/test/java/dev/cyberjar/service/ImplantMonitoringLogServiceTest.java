package dev.cyberjar.service;

import dev.cyberjar.MongodbDemoApp;
import dev.cyberjar.entity.MonitoringStats;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
@SpringBootTest(classes = MongodbDemoApp.class)
class ImplantMonitoringLogServiceTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

    @Autowired
    private ImplantMonitoringLogService monitoringLogService;

    @BeforeAll
    static void startContainerBeforeAll() {
        mongoDBContainer.start();
    }

    @AfterAll
    static void stopContainerAfterAll() {
        mongoDBContainer.stop();
    }

    @Test
    void shouldGatherStatsForImplantLogs() {

        MonitoringStats stats = monitoringLogService.aggregateStatsForImplantForPeriod(
                "447327",
                LocalDateTime.now().minusDays(7),
                LocalDateTime.now()
        );


//        double expectedAvgPowerUsage = 51.02;
//        assertEquals(expectedAvgPowerUsage, stats.getAvgPowerUsageUw());

    }

}