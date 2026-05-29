package com.example.fighttracker.Models;

public class FightResult {

    private final Fighter winner;
    private final Fighter losser;
    private final String finishType;
    private final int endRound;
    private final String time;

    public FightResult(Fighter winner, Fighter losser, String finishType, int endRound, String time) {
        this.winner = winner;
        this.losser = losser;
        this.finishType = finishType;
        this.endRound = endRound;
        this.time = time;
    }

    public Fighter getWinner() {
        return winner;
    }

    public Fighter getLosser() {
        return losser;
    }

    public String getFinishType() {
        return finishType;
    }

    public int getEndRound() {
        return endRound;
    }

    public String getTime() {
        return time;
    }
}
