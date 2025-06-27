package dev.cyberjar.repository.implantmonitoring;

import dev.cyberjar.entity.MonitoringStats;

import java.time.LocalDateTime;

public interface ImplantMonitoringLogRepositoryCustom {

    MonitoringStats aggregateStats(String serialNumber, LocalDateTime from, LocalDateTime to);

}
