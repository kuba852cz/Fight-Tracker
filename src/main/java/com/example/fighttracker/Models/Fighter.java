package com.example.fighttracker.Models;

public class Fighter {

    private String name;
    private String nickname;
    private int age;
    private String country;
    private String flag;
    private String weightClass;
    private int rank;
    private int height;
    private int weight;
    private int reach;
    private String stance;
    private int wins;
    private int losses;
    private int draws;
    private int ko;
    private int submissions;
    private int decisions;
    private String fightingStyle;
    private String imagePath;

    public Fighter(String name, String nickname, int age, String country, String flag, String weightClass, int rank, int height, int weight, int reach, String stance, int wins, int losses, int draws, int ko, int submissions, int decisions, String fightingStyle, String imagePath) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.country = country;
        this.flag = flag;
        this.weightClass = weightClass;
        this.rank = rank;
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

    public int getRank() {
        return rank;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getReach() {
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
}
