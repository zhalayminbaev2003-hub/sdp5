package com.matchmaking.family1_server;

import com.matchmaking.model.GameServer;
import com.matchmaking.external.FakeServerMetricsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class ServerAllocationStrategyTest {
    private List<GameServer> servers;
    private FakeServerMetricsProvider fakeMetrics;

    @BeforeEach
    void setUp() {
        servers = List.of(
            new GameServer("srv-1", "EU", 100),
            new GameServer("srv-2", "NA", 150),
            new GameServer("srv-3", "EU", 30)
        );
        fakeMetrics = new FakeServerMetricsProvider();
    }

    @Test
    void testLowestPingStrategy() {
        ServerAllocationStrategy strategy = new LowestPingAllocationStrategy();
        GameServer result = strategy.allocate(servers, fakeMetrics, "EU");
        assertEquals("srv-3", result.getServerId());
    }

    @Test
    void testLeastLoadedStrategy() {
        ServerAllocationStrategy strategy = new LeastLoadedAllocationStrategy();
        GameServer result = strategy.allocate(servers, fakeMetrics, "EU");
        assertEquals("srv-2", result.getServerId());
    }

    @Test
    void testSelectorRules() {
        assertTrue(ServerAllocationSelector.selectStrategyAutomatically(true, false) instanceof LeastLoadedAllocationStrategy);
        assertTrue(ServerAllocationSelector.selectStrategyAutomatically(false, true) instanceof LowestPingAllocationStrategy);
    }

    static Stream<ServerAllocationStrategy> provideStrategies() {
        return Stream.of(new LowestPingAllocationStrategy(), new LeastLoadedAllocationStrategy(), new GeographicAllocationStrategy());
    }

    @ParameterizedTest
    @MethodSource("provideStrategies")
    void testAllStrategiesReturnNonNullServer(ServerAllocationStrategy strategy) {
        GameServer result = strategy.allocate(servers, fakeMetrics, "EU");
        assertNotNull(result, "Strategy should always return a valid server");
    }
}