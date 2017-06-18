package es.ulpgc.eii.android.project2.modal;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

    private int color;
    private String name;
    private int totalScore;

    public boolean isHuman() {
        return isHuman;
    }

    private boolean isHuman;

    public Player(String name, int color, boolean isHuman) {
        this.name = name;
        this.color = color;
        this.isHuman = isHuman;
        totalScore = 0;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        dest.writeInt(this.color);
        dest.writeString(this.name);
        dest.writeInt(this.totalScore);
        dest.writeByte(this.isHuman ? (byte) 1 : (byte) 0);
    }

    private Player(Parcel in) {
        this.color = in.readInt();
        this.name = in.readString();
        this.totalScore = in.readInt();
        this.isHuman = in.readByte() != 0;
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
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