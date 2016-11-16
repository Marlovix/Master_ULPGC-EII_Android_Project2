package es.ulpgc.eii.android.project2.modal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class with all information which is necessary for a player //
public class Player implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.totalScore);
        dest.writeInt(this.accumulatedScore);
        dest.writeInt(this.color);
    }

    protected Player(Parcel in) {
        this.name = in.readString();
        this.totalScore = in.readInt();
        this.accumulatedScore = in.readInt();
        this.color = in.readInt();
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}