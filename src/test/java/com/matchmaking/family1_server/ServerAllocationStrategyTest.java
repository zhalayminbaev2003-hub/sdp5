package com.matchmaking.family1_server;

import com.matchmaking.model.GameServer;
import com.matchmaking.external.FakeServerMetricsProvider;
import com.matchmaking.bad_code_before.BadServerAllocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ServerAllocationStrategyTest {
    private List<GameServer> servers;
    private FakeServerMetricsProvider fakeMetrics;
    private BadServerAllocator allocator;

    @BeforeEach
    void setUp() {
        servers = List.of(
            new GameServer("srv-1", "EU", 100),
            new GameServer("srv-2", "NA", 150),
            new GameServer("srv-3", "EU", 30)
        );
        fakeMetrics = new FakeServerMetricsProvider();
        allocator = new BadServerAllocator();
    }

    @Test
    void testBadAllocatorLowestPing() {
        GameServer result = allocator.allocateServer(servers, "LOW_PING", fakeMetrics, "EU");
        assertEquals("srv-3", result.getServerId());
    }

    @Test
    void testBadAllocatorLeastLoaded() {
        GameServer result = allocator.allocateServer(servers, "LEAST_LOADED", fakeMetrics, "EU");
        assertEquals("srv-2", result.getServerId());
    }
}