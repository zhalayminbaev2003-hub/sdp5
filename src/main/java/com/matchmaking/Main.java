package com.matchmaking;

import com.matchmaking.external.ServerMetricsProvider;
import com.matchmaking.family1_server.*;
import com.matchmaking.family2_grouping.*;
import com.matchmaking.model.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== VIDEO GAME MATCHMAKING: STRATEGY DEMO ===");

        List<GameServer> servers = List.of(
            new GameServer("srv-1", "EU", 45),
            new GameServer("srv-2", "NA", 150),
            new GameServer("srv-3", "EU", 25)
        );
        ServerMetricsProvider liveMetrics = serverId -> Math.random() * 100;

        System.out.println("\n--- 1. Manual Strategy Switching (Servers) ---");
        ServerAllocationContext serverContext = new ServerAllocationContext(new LowestPingAllocationStrategy(), liveMetrics);
        System.out.println("Lowest Ping Strategy: " + serverContext.executeAllocation(servers, "EU"));

        serverContext.setStrategy(new GeographicAllocationStrategy());
        System.out.println("Geographic Strategy: " + serverContext.executeAllocation(servers, "NA"));

        System.out.println("\n--- 2. Automatic Selection (Servers) ---");
        ServerAllocationStrategy autoStrategy = ServerAllocationSelector.selectStrategyAutomatically(true, false);
        serverContext.setStrategy(autoStrategy);
        System.out.println("Auto Strategy (Peak Hours) picked: " + autoStrategy.getClass().getSimpleName());
        System.out.println("Result: " + serverContext.executeAllocation(servers, "EU"));

        System.out.println("\n--- 3. Matchmaking Grouping Families ---");
        List<Player> queue = List.of(
            new Player("p1", 1500, false), new Player("p2", 2800, false),
            new Player("p3", 1550, true), new Player("p4", 1100, false)
        );

        MatchmakingContext groupContext = new MatchmakingContext(new VipPriorityGroupingStrategy());
        System.out.println("VIP Priority: " + groupContext.executeGrouping(queue, 2));

        groupContext.setStrategy(new StrictSkillGroupingStrategy());
        System.out.println("Strict MMR: " + groupContext.executeGrouping(queue, 2));
    }
}