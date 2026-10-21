package com.matchmaking.family2_grouping;

public class GroupingStrategySelector {
    public static GroupingStrategy selectStrategyAutomatically(int currentQueueSize) {
        if (currentQueueSize > 1000) {
            return new StrictSkillGroupingStrategy();
        } else if (currentQueueSize < 50) {
            return new RelaxedFastGroupingStrategy();
        } else {
            return new VipPriorityGroupingStrategy();
        }
    }
}