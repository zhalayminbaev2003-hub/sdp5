package com.matchmaking.family2_grouping;

import com.matchmaking.model.Player;
import java.util.List;

public class StrictSkillGroupingStrategy implements GroupingStrategy {
    @Override
    public List<Player> groupPlayers(List<Player> queue, int requiredPartySize) {
        return queue.stream()
                .filter(p -> p.getMmr() > 1400 && p.getMmr() < 1600)
                .limit(requiredPartySize)
                .toList();
    }
}