package com.matchmaking.family1_server;

import com.matchmaking.model.GameServer;
import com.matchmaking.external.ServerMetricsProvider;
import java.util.List;

public class LowestPingAllocationStrategy implements ServerAllocationStrategy {
    @Override
    public GameServer allocate(List<GameServer> servers, ServerMetricsProvider metrics, String playerRegion) {
        return servers.stream().min((s1, s2) -> Integer.compare(s1.getPingMs(), s2.getPingMs())).orElse(null);
    }
}