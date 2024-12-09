package org.spherical.auserver;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "punctuation")
public class Punctuation {

    @Id
    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "wins", nullable = false)
    private Integer wins;

    @Column(name = "punctuation", nullable = false)
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
}
