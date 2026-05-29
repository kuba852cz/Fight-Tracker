package com.example.fighttracker.Models;

/**
 * Represents a mixed martial arts (MMA) fighter.
 * Holds personal information, physical attributes, and fight statistics.
 */

public class Fighter {

    private final String name;
    private final String nickname;
    private final int age;
    private final String country;
    private final String flag;
    private final String weightClass;
    private final int rank;
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

    public String getRecord (){
        return wins+"/"+losses+"/"+draws;
    }

    public Fighter(String name, String nickname, int age, String country, String flag, String weightClass, int rank, double height, double weight, double reach, String stance, int wins, int losses, int draws, int ko, int submissions, int decisions, String fightingStyle, String imagePath) {
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

    /**
     * Gets the fighter's current division rank.
     * Returns "C" (Champion) if the rank is 0, otherwise returns the numeric rank.
     * @return String representation of the rank.
     */

    public String getRank() {
        if (this.rank == 0){
            return "C";
        }else{
            return String.valueOf(this.rank);
        }
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

    @Override
    public String toString() {
        return "Fighter{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", flag='" + flag + '\'' +
                ", weightClass='" + weightClass + '\'' +
                ", rank='" + rank + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", reach=" + reach +
                ", stance='" + stance + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                ", draws=" + draws +
                ", ko=" + ko +
                ", submissions=" + submissions +
                ", decisions=" + decisions +
                ", fightingStyle='" + fightingStyle + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
