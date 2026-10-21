package com.matchmaking.external;

public interface ServerMetricsProvider {
    double getCpuLoadPercentage(String serverId);
}