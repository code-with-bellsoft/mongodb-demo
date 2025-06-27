package dev.cyberjar.entity;

public class MonitoringStats {

    private String implantSerialNumber;
    private double avgPowerUsageUw;
    private double avgCpuUsagePct;
    private double avgNeuralLatencyMs;

    public String getImplantSerialNumber() {
        return implantSerialNumber;
    }

    public void setImplantSerialNumber(String implantSerialNumber) {
        this.implantSerialNumber = implantSerialNumber;
    }

    public double getAvgPowerUsageUw() {
        return avgPowerUsageUw;
    }

    public void setAvgPowerUsageUw(double avgPowerUsageUw) {
        this.avgPowerUsageUw = avgPowerUsageUw;
    }

    public double getAvgCpuUsagePct() {
        return avgCpuUsagePct;
    }

    public void setAvgCpuUsagePct(double avgCpuUsagePct) {
        this.avgCpuUsagePct = avgCpuUsagePct;
    }

    public double getAvgNeuralLatencyMs() {
        return avgNeuralLatencyMs;
    }

    public void setAvgNeuralLatencyMs(double avgNeuralLatencyMs) {
        this.avgNeuralLatencyMs = avgNeuralLatencyMs;
    }
}
