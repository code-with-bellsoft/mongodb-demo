package dev.cyberjar;

import dev.cyberjar.entity.Civilian;
import dev.cyberjar.entity.MonitoringStats;
import dev.cyberjar.repository.civilian.CivilianRepository;
import dev.cyberjar.service.CivilianService;
import dev.cyberjar.service.ImplantMonitoringLogService;
import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;
import java.util.List;

@EnableMongock
@SpringBootApplication
@EnableMongoRepositories
public class MongodbDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(MongodbDemoApp.class, args);
    }


    @Bean
    public CommandLineRunner demo(
            CivilianService civilianService,
            ImplantMonitoringLogService implantMonitoringLogService
    ) {
        return args -> {
            System.out.println("🔧 Verifying data loaded by Mongock...");



            System.out.println("Looking for a civilian:");
            System.out.println(civilianService.getCivilianByNationalId("Ni-96751543-BP").toString());



            List<Civilian> lotMatches = civilianService.getCiviliansByLotNumber(536);
            System.out.println("Civilians with implant lot: " + lotMatches.size());


            MonitoringStats stats = implantMonitoringLogService.aggregateStatsForImplantForPeriod(
                    "447327",
                    LocalDateTime.now().minusDays(7),
                    LocalDateTime.now()
            );

            if (stats != null) {
                System.out.println("Stats for implant 447327:");
                System.out.println("   Power: " + stats.getAvgPowerUsageUw() + " µW");
                System.out.println("   CPU: " + stats.getAvgCpuUsagePct() + " %");
                System.out.println("   Latency: " + stats.getAvgNeuralLatencyMs() + " ms");
            } else {
                System.out.println("No monitoring logs found for 447327");
            }
        };
    }


}
