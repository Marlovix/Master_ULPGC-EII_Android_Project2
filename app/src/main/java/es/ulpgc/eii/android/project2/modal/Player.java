package es.ulpgc.eii.android.project2.modal;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class with all information which is necessary for a player //
public class Player {

    private String name;
    private int totalScore;
    private int accumulatedScore;
    private int color;

    public Player(String name, int color) {
        this.name = name;
        this.color = color;
        totalScore = 0;
        accumulatedScore = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccumulatedScore() {
        return accumulatedScore;
    }

    public void resetAccumulatedScore() {
        this.accumulatedScore = 0;
    }

    public void addAccumulatedScore(int accumulatedScore) {
        this.accumulatedScore += accumulatedScore;
    }

    public int getColor() {
        return color;
    }

    public int getScore() {
        return totalScore;
    }

    public void setScore(int totalScore) {
        this.totalScore = totalScore;
    }
}