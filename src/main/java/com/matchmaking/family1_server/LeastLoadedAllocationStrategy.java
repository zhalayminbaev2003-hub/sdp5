package com.matchmaking.family1_server;

import com.matchmaking.model.GameServer;
import com.matchmaking.external.ServerMetricsProvider;
import java.util.List;

public class LeastLoadedAllocationStrategy implements ServerAllocationStrategy {
    @Override
    public GameServer allocate(List<GameServer> servers, ServerMetricsProvider metrics, String playerRegion) {
        GameServer best = servers.get(0);
        double minLoad = metrics.getCpuLoadPercentage(best.getServerId());
        
        for (GameServer s : servers) {
            double currentLoad = metrics.getCpuLoadPercentage(s.getServerId());
            if (currentLoad < minLoad) {
                minLoad = currentLoad;
                best = s;
            }
        }
        return best;
    }
}