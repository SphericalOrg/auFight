package org.auSpherical.auFight.data;


public class Punctuation {

    private String name;
    private Integer wins;
    private Integer punctuation;

    public Punctuation(String name, Integer wins, Integer punctuation) {
        this.name = name;
        this.wins = wins;
        this.punctuation = punctuation;
    }

    public Punctuation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(Integer punctuation) {
        this.punctuation = punctuation;
    }

    public String toString() {
        return "Name: " + name + ", Wins: " + wins + ", Punctuation: " + punctuation;
    }

    public String serialize(){
        return "{\"name\":\"" + name + "\",\"wins\":" + wins + ",\"punctuation\":" + punctuation + "}";
    }
}
