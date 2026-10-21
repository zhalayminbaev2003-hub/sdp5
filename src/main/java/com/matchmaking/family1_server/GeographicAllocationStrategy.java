package com.matchmaking.family1_server;

import com.matchmaking.model.GameServer;
import com.matchmaking.external.ServerMetricsProvider;
import java.util.List;

public class GeographicAllocationStrategy implements ServerAllocationStrategy {
    @Override
    public GameServer allocate(List<GameServer> servers, ServerMetricsProvider metrics, String playerRegion) {
        return servers.stream()
                .filter(s -> s.getRegion().equalsIgnoreCase(playerRegion))
                .findFirst()
                .orElse(servers.get(0));
    }
}