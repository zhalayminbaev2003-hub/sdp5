package com.matchmaking.family1_server;

import com.matchmaking.model.GameServer;
import com.matchmaking.external.ServerMetricsProvider;
import java.util.List;

public class ServerAllocationContext {
    private ServerAllocationStrategy strategy;
    private ServerMetricsProvider metricsProvider;

    public ServerAllocationContext(ServerAllocationStrategy strategy, ServerMetricsProvider metricsProvider) {
        this.strategy = strategy;
        this.metricsProvider = metricsProvider;
    }

    public void setStrategy(ServerAllocationStrategy strategy) {
        this.strategy = strategy;
    }

    public GameServer executeAllocation(List<GameServer> servers, String playerRegion) {
        return strategy.allocate(servers, metricsProvider, playerRegion);
    }
}