    package com.matchmaking.external;

public class FakeServerMetricsProvider implements ServerMetricsProvider {
    @Override
    public double getCpuLoadPercentage(String serverId) {
        if (serverId.equals("srv-1")) return 90.0;
        if (serverId.equals("srv-2")) return 10.0;
        return 50.0;
    }
}