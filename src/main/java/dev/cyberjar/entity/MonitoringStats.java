package dev.cyberjar.entity;

public record MonitoringStats(String implantSerialNumber,
                              double avgPowerUsageUw,
                              double avgCpuUsagePct,
                              double avgNeuralLatencyMs) {

}
