package dev.cyberjar.repository.implantmonitoring;

import dev.cyberjar.entity.ImplantMonitoringLog;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface ImplantMonitoringLogRepository extends MongoRepository<ImplantMonitoringLog, String>, ImplantMonitoringLogRepositoryCustom {

    List<ImplantMonitoringLog> findByImplantSerialNumber(String implantSerialNumber);

    List<ImplantMonitoringLog> findByImplantSerialNumberAndTimestampAfter(String implantSerialNumber,
                                                                          LocalDateTime timestamp);

    List<ImplantMonitoringLog> findByImplantSerialNumberAndTimestampBetween(String implantSerialNumber,
                                                                            LocalDateTime timestampFrom,
                                                                            LocalDateTime timestampTo);

    /* A basic method to return a List of all ImplantMonitoringLogs
       within a given time and distance

    List<ImplantMonitoringLog> findByLocationNearAndTimestampBetween(
            Point point, Distance distance, LocalDateTime from, LocalDateTime to);

     */
}
