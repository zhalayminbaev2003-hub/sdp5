package com.matchmaking.family2_grouping;

import com.matchmaking.model.Player;
import java.util.List;

public class RelaxedFastGroupingStrategy implements GroupingStrategy {
    @Override
    public List<Player> groupPlayers(List<Player> queue, int requiredPartySize) {
        return queue.stream()
                .limit(requiredPartySize)
                .toList();
    }
}