package com.matchmaking.family2_grouping;

import com.matchmaking.model.Player;
import java.util.List;

public interface GroupingStrategy {
    List<Player> groupPlayers(List<Player> queue, int requiredPartySize);
}