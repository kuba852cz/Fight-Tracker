package com.example.fighttracker.Models;

import java.util.Random;

/**
 * Handles the mathematical logic for simulating a fight between two fighters.
 * Calculates the winner, the method of victory, and the duration of the fight.
 */

public class FightSimulation {

    /**
     * Executes the fight simulation to determine the outcome.
     * Calculates the maximum rounds, the winner, the finish type, and the exact round the fight ends.
     * @param fighterA The first fighter in the simulation.
     * @param fighterB The second fighter in the simulation.
     * @return A FightResult object containing the winner, loser, finish type, and the round the fight ended.
     */

    public FightResult runSimulator(Fighter fighterA, Fighter fighterB) {
        int maxRound = getMaxRound(fighterA, fighterB);
        Fighter winner = getWinner(fighterA, fighterB);
        Fighter losser = null;
        String finishType;
        String formattedTime;
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

        if (finishType.toLowerCase().contains("decision") || finishType.equals("Draw")) {
            endRound = maxRound;
            formattedTime = "5:00";
        } else {
            endRound = new Random().nextInt(1, maxRound + 1);
            int minutes = new Random().nextInt(0, 5);
            int seconds = new Random().nextInt(0, 60);
            formattedTime = String.format("%d:%02d", minutes, seconds);
        }

        return new FightResult(winner, losser, finishType, endRound, formattedTime);
    }

    /**
     * Determines the finishing method (Knockout, Submission, or Decision)
     * based on the winner's historical win ratios and probabilities.
     * @param winner The fighter who won the simulated match.
     * @return A string representing the exact type of finish.
     */

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

    /**
     * Determines the maximum number of rounds for the fight.
     * Championship fights (where at least one fighter is rank "C") are 5 rounds, regular fights are 3 rounds.
     * @param fighterA The first fighter.
     * @param fighterB The second fighter.
     * @return The maximum number of rounds (3 or 5).
     */

    public int getMaxRound (Fighter fighterA, Fighter fighterB){
        if (fighterA.getRank().equals("C") || fighterB.getRank().equals("C")){
            return 5;
        } else {
            return 3;
        }
    }

    /**
     * Calculates the winner of the fight based on a weighted random probability
     * using the historical win ratios of both fighters.
     * Includes a small statistical margin for a draw.
     * @param fighterA The first fighter.
     * @param fighterB The second fighter.
     * @return The winning Fighter object, or null in the case of a draw.
     */

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
