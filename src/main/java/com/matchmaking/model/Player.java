package com.matchmaking.model;

public class Player {
    private String id;
    private int mmr;
    private boolean isVip;

    public Player(String id, int mmr, boolean isVip) {
        this.id = id;
        this.mmr = mmr;
        this.isVip = isVip;
    }

    public String getId() { return id; }
    public int getMmr() { return mmr; }
    public boolean isVip() { return isVip; }

    @Override
    public String toString() { return id + "[MMR:" + mmr + ", VIP:" + isVip + "]"; }
}   