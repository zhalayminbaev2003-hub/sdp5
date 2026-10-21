package com.matchmaking.family1_server;

import com.matchmaking.model.GameServer;
import com.matchmaking.external.ServerMetricsProvider;
import java.util.List;

public interface ServerAllocationStrategy {
    GameServer allocate(List<GameServer> servers, ServerMetricsProvider metrics, String playerRegion);
}