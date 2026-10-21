package com.matchmaking.family2_grouping;

import com.matchmaking.model.Player;
import java.util.List;

public class MatchmakingContext {
    private GroupingStrategy strategy;

    public MatchmakingContext(GroupingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(GroupingStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Player> executeGrouping(List<Player> queue, int requiredPartySize) {
        return strategy.groupPlayers(queue, requiredPartySize);
    }
}