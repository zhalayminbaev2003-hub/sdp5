package com.matchmaking.family2_grouping;

import com.matchmaking.model.Player;
import java.util.ArrayList;
import java.util.List;

public class VipPriorityGroupingStrategy implements GroupingStrategy {
    @Override
    public List<Player> groupPlayers(List<Player> queue, int requiredPartySize) {
        List<Player> party = new ArrayList<>();
        
        queue.stream().filter(Player::isVip).limit(requiredPartySize).forEach(party::add);
        queue.stream().filter(p -> !p.isVip()).limit(requiredPartySize - party.size()).forEach(party::add);
        
        return party;
    }
}