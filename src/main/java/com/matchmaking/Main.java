package com.matchmaking;

import com.matchmaking.bad_code_before.BadServerAllocator;
import com.matchmaking.external.ServerMetricsProvider;
import com.matchmaking.family2_grouping.*;
import com.matchmaking.model.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== VIDEO GAME MATCHMAKING: BEFORE REFACTORING ===");

        List<GameServer> servers = List.of(
            new GameServer("srv-1", "EU", 45),
            new GameServer("srv-2", "NA", 150),
            new GameServer("srv-3", "EU", 25)
        );
        ServerMetricsProvider liveMetrics = serverId -> Math.random() * 100;

        System.out.println("\n--- 1. Bad Server Allocator (Switch-Case) ---");
        BadServerAllocator badAllocator = new BadServerAllocator();
        System.out.println("Lowest Ping: " + badAllocator.allocateServer(servers, "LOW_PING", liveMetrics, "EU"));
        System.out.println("Geographic: " + badAllocator.allocateServer(servers, "GEO", liveMetrics, "NA"));

        System.out.println("\n--- 2. Matchmaking Grouping Families ---");
        List<Player> queue = List.of(
            new Player("p1", 1500, false), new Player("p2", 2800, false),
            new Player("p3", 1550, true), new Player("p4", 1100, false)
        );

        MatchmakingContext groupContext = new MatchmakingContext(new VipPriorityGroupingStrategy());
        System.out.println("VIP Priority: " + groupContext.executeGrouping(queue, 2));
    }
}