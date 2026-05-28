package com.example.fighttracker.Models;

import java.util.Random;

public class FightSimulation {

    public void runSimulator(Fighter fighterA, Fighter fighterB) {
        int maxRound = getMaxRound(fighterA, fighterB);
        Fighter winner = getWinner(fighterA, fighterB);
        Fighter losser = null;
        String finishType;
        int endRound;

        if (winner == null) {
            finishType = "Draw";
            endRound = maxRound;
        } else {
            losser = (winner == fighterA) ? fighterB : fighterA;
            finishType = getTypeOfFinish(winner);
        }

        if (finishType.equals("Decision") || finishType.equals("Draw")) {
            endRound = maxRound;
        } else {
            endRound = new Random().nextInt(1, maxRound + 1);
        }
    }

    public String getTypeOfFinish(Fighter winner) {
        double koRatio = (double) winner.getKo() / winner.getWins();
        double submissionRatio = (double) winner.getSubmissions() / winner.getWins();

        double randomPick = new Random().nextDouble();

        if (winner.getWins() == 0) return "Unanimous decision";

        if (randomPick < koRatio) {
            return "Knockout";
        } else if (randomPick < (koRatio + submissionRatio)) {
            return "Submission";
        } else {
            int decisionRandom = new Random().nextInt(0, 3);
            switch (decisionRandom) {
                case 1:
                    return "Split decision";
                case 2:
                    return "Majority decision";
                default:
                    return "Unanimous decision";
            }
        }
    }

    public int getMaxRound (Fighter fighterA, Fighter fighterB){
        if (fighterA.getRank().equals("C") || fighterB.getRank().equals("C")){
            return 5;
        } else {
            return 3;
        }
    }

    public Fighter getWinner(Fighter fighterA, Fighter fighterB){
        int totalFightsA = (fighterA.getWins() + fighterA.getLosses() + fighterA.getDraws());
        int totalFightsB = (fighterB.getWins() + fighterB.getLosses() + fighterB.getDraws());

        double winRatioA;
        double winRatioB;

        if (totalFightsA == 0){
            winRatioA = 0.5;
        } else {
            winRatioA = (double) (fighterA.getWins()) / totalFightsA;
        }

        if (totalFightsB == 0){
            winRatioB = 0.5;
        } else {
            winRatioB = (double) (fighterB.getWins()) / totalFightsB;
        }

        double totalWinRatio = winRatioA+winRatioB;
        double drawMargin = totalWinRatio*0.025;

        Random random = new Random();

        double winRandom = random.nextDouble(0,(winRatioA+winRatioB));


        if (winRandom > (winRatioA + drawMargin)){
            return fighterB;
        } else if (winRandom < (winRatioA - drawMargin)) {
            return fighterA;
        } else  {
           return null;
        }
    }

}
