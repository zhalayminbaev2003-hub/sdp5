package com.matchmaking.model;

public class GameServer {
    private String serverId;
    private String region;
    private int pingMs;

    public GameServer(String serverId, String region, int pingMs) {
        this.serverId = serverId;
        this.region = region;
        this.pingMs = pingMs;
    }

    public String getServerId() { return serverId; }
    public String getRegion() { return region; }
    public int getPingMs() { return pingMs; }

    @Override
    public String toString() { return serverId + "(" + region + ")"; }
}