package com.example.fighttracker.Models;

public class FightResult {

    private final Fighter winner;
    private final Fighter losser;
    private final String finishType;
    private final int endRound;

    public FightResult(Fighter winner, Fighter losser, String finishType, int endRound) {
        this.winner = winner;
        this.losser = losser;
        this.finishType = finishType;
        this.endRound = endRound;
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
}
