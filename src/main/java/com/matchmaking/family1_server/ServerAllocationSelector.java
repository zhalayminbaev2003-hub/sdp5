package com.matchmaking.family1_server;

public class ServerAllocationSelector {
    public static ServerAllocationStrategy selectStrategyAutomatically(boolean isPeakHours, boolean playerHasHighPingIssue) {
        if (playerHasHighPingIssue) {
            return new LowestPingAllocationStrategy();
        } else if (isPeakHours) {
            return new LeastLoadedAllocationStrategy();
        } else {
            return new GeographicAllocationStrategy();
        }
    }
}