package com.example.fighttracker.Models;

public class Fighter {

    private final String name;
    private final String nickname;
    private final int age;
    private final String country;
    private final String flag;
    private final String weightClass;
    private String rank;
    private final double height;
    private final double weight;
    private final double reach;
    private final String stance;
    private final int wins;
    private final int losses;
    private final int draws;
    private final int ko;
    private final int submissions;
    private final int decisions;
    private final String fightingStyle;
    private final String imagePath;

    public Fighter(String name, String nickname, int age, String country, String flag, String weightClass, String rank, double height, double weight, double reach, String stance, int wins, int losses, int draws, int ko, int submissions, int decisions, String fightingStyle, String imagePath) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.country = country;
        this.flag = flag;
        this.weightClass = weightClass;
        setRank(rank);
        this.height = height;
        this.weight = weight;
        this.reach = reach;
        this.stance = stance;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.ko = ko;
        this.submissions = submissions;
        this.decisions = decisions;
        this.fightingStyle = fightingStyle;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }

    public String getFlag() {
        return flag;
    }

    public String getWeightClass() {
        return weightClass;
    }

    public String getRank() {
        return rank;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getReach() {
        return reach;
    }

    public String getStance() {
        return stance;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public int getKo() {
        return ko;
    }

    public int getSubmissions() {
        return submissions;
    }

    public int getDecisions() {
        return decisions;
    }

    public String getFightingStyle() {
        return fightingStyle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setRank(String rank) {
        if (rank.equals("0")){
            this.rank = "C";
        }else {
            this.rank = rank;
        }
    }
}
