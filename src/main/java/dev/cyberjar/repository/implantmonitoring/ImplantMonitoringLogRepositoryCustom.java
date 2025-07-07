package dev.cyberjar.repository.implantmonitoring;

import dev.cyberjar.entity.ImplantMonitoringLog;
import dev.cyberjar.entity.MonitoringStats;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ImplantMonitoringLogRepositoryCustom {

    MonitoringStats aggregateStats(String serialNumber, LocalDateTime from, LocalDateTime to);

    public Map<String, List<ImplantMonitoringLog>> findLogsByAreaAndTimeGrouped(
            Point center, double maxDistanceMeters, LocalDateTime from, LocalDateTime to);

}
