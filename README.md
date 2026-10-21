
## Overview
This project implements the Strategy Design Pattern for a Video Game Matchmaking system. It includes two independent strategy families:
1. **Server Allocation Strategy:** Interchangeable algorithms for selecting the optimal game server (Lowest Ping, Least Loaded, Geographic).
2. **Matchmaking Grouping Strategy:** Interchangeable algorithms for organizing players into balanced parties (Strict Skill/MMR, Relaxed Fast, VIP Priority).

## SRP Table (Single Responsibility Principle)

## Why Strategy Pattern and not State or Template Method?
The Strategy pattern was chosen because algorithms need to be completely swapped out at runtime based on external conditions (such as picking a server based on ping vs. server CPU load). 

This is not the **State Pattern** because the context does not manage internal state transitions or lifecycle states on its own. 

This is not the **Template Method Pattern** because Template Method relies on inheritance to override steps of a fixed skeleton, whereas our implementation relies on object composition to delegate operations entirely to interchangeable strategy instances.
