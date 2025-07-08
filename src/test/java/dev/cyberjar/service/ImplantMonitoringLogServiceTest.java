package dev.cyberjar.service;

import dev.cyberjar.MongodbDemoApp;
import dev.cyberjar.entity.ImplantMonitoringLog;
import dev.cyberjar.entity.MonitoringStats;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@SpringBootTest(classes = MongodbDemoApp.class)
@TestPropertySource(locations = "classpath:test.properties")
class ImplantMonitoringLogServiceTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

    @Autowired
    private ImplantMonitoringLogService monitoringLogService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void populateWithData() {
        mongoTemplate.createCollection("implant_logs");

        String implantSerialNum = "123456qw";
        String civilianNationalId = "rtfg5674-98";

        double powerUsage = 1.5;
        double cpuUsage = 1.0;
        double neuralLatency = 0.5;

        List<ImplantMonitoringLog> logs = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            ImplantMonitoringLog implantMonitoringLog = new ImplantMonitoringLog(null,
                    implantSerialNum, civilianNationalId,
                    LocalDateTime.now().minusHours(i),
                    powerUsage + i,
                    cpuUsage + i,
                    neuralLatency + i,
                    new Point(4.899, 52.372)); //Coordinates for Amsterdam

            logs.add(implantMonitoringLog);
        }

        mongoTemplate.insert(logs, ImplantMonitoringLog.class);

    }

    @AfterEach
    void cleanUp() {
        mongoTemplate.dropCollection("implant_logs");

    }

    @Test
    void shouldGatherStatsForImplantLogs() {

        MonitoringStats stats = monitoringLogService.aggregateStatsForImplantForPeriod(
                "123456qw",
                LocalDateTime.now().minusDays(7),
                LocalDateTime.now()
        );

        double expectedAvgPowerUsage = 16.0;
        assertEquals(expectedAvgPowerUsage, stats.avgPowerUsageUw());

    }

}