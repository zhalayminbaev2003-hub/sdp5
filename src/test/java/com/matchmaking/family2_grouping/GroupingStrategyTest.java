package com.matchmaking.family2_grouping;

import com.matchmaking.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class GroupingStrategyTest {
    private List<Player> queue;

    @BeforeEach
    void setUp() {
        queue = List.of(
            new Player("p1", 1450, false),
            new Player("p2", 2000, false),
            new Player("p3", 1500, true),
            new Player("p4", 1550, false)
        );
    }

    @Test
    void testStrictSkillGroupingStrategy() {
        GroupingStrategy strategy = new StrictSkillGroupingStrategy();
        List<Player> result = strategy.groupPlayers(queue, 2);
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(p -> p.getMmr() > 1400 && p.getMmr() < 1600));
    }

    @Test
    void testVipPriorityGroupingStrategy() {
        GroupingStrategy strategy = new VipPriorityGroupingStrategy();
        List<Player> result = strategy.groupPlayers(queue, 2);
        assertEquals("p3", result.get(0).getId()); 
    }

    @Test
    void testSelectorRules() {
        assertTrue(GroupingStrategySelector.selectStrategyAutomatically(1500) instanceof StrictSkillGroupingStrategy);
        assertTrue(GroupingStrategySelector.selectStrategyAutomatically(10) instanceof RelaxedFastGroupingStrategy);
        assertTrue(GroupingStrategySelector.selectStrategyAutomatically(200) instanceof VipPriorityGroupingStrategy);
    }

    static Stream<GroupingStrategy> provideGroupingStrategies() {
        return Stream.of(new StrictSkillGroupingStrategy(), new RelaxedFastGroupingStrategy(), new VipPriorityGroupingStrategy());
    }

    @ParameterizedTest
    @MethodSource("provideGroupingStrategies")
    void testAllStrategiesRespectMaxPartySize(GroupingStrategy strategy) {
        List<Player> result = strategy.groupPlayers(queue, 2);
        assertTrue(result.size() <= 2);
    }
}