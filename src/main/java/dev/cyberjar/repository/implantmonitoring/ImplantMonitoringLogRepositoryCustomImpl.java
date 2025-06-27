package dev.cyberjar.repository.implantmonitoring;

import dev.cyberjar.entity.MonitoringStats;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class ImplantMonitoringLogRepositoryCustomImpl implements ImplantMonitoringLogRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public ImplantMonitoringLogRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public MonitoringStats aggregateStats(String serialNumber, LocalDateTime from, LocalDateTime to) {
        MatchOperation match = Aggregation.match(Criteria.where("implantSerialNumber").is(serialNumber)
                .and("timestamp").gte(from).lte(to));

        GroupOperation group = Aggregation.group("implantSerialNumber")
                .avg("powerUsageUw").as("avgPowerUsageUw")
                .avg("cpuUsagePct").as("avgCpuUsagePct")
                .avg("neuralLatencyMs").as("avgNeuralLatencyMs");

        ProjectionOperation project = Aggregation.project()
                .and("_id").as("implantSerialNumber")
                .andInclude("avgPowerUsageUw", "avgCpuUsagePct", "avgNeuralLatencyMs");

        Aggregation aggregation = Aggregation.newAggregation(match, group, project);

        AggregationResults<MonitoringStats> results = mongoTemplate.aggregate(
                aggregation, "implant_logs", MonitoringStats.class);

        return results.getUniqueMappedResult();
    }
}
