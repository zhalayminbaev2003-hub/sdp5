package com.matchmaking.bad_code_before;

import com.matchmaking.model.GameServer;
import com.matchmaking.external.ServerMetricsProvider;
import java.util.List;

public class BadServerAllocator {
    public GameServer allocateServer(List<GameServer> servers, String strategyType, 
                                     ServerMetricsProvider metrics, String playerRegion) {
        switch (strategyType) {
            case "LOW_PING":
                GameServer bestPing = servers.get(0);
                for (GameServer s : servers) {
                    if (s.getPingMs() < bestPing.getPingMs()) bestPing = s;
                }
                return bestPing;
            case "LEAST_LOADED":
                GameServer leastLoaded = servers.get(0);
                double minLoad = 100.0;
                for (GameServer s : servers) {
                    double load = metrics.getCpuLoadPercentage(s.getServerId());
                    if (load < minLoad) { 
                        minLoad = load; 
                        leastLoaded = s; 
                    }
                }
                return leastLoaded;
            case "GEO":
                for (GameServer s : servers) {
                    if (s.getRegion().equals(playerRegion)) return s;
                }
                return servers.get(0);
            default:
                throw new IllegalArgumentException("Unknown strategy");
        }
    }
}