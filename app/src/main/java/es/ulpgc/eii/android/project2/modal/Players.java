package es.ulpgc.eii.android.project2.modal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class witch controls the group of players and
// returns the player who has to play //
public class Players extends ArrayList<Player> implements Parcelable {

    private Player playerToPlay;

    Players() {
    }

    void setFirstPlayer(Player player) {
        playerToPlay = player;
    }

    void changePlayer() {
        playerToPlay.resetAccumulatedScore();
        int numPlayer = this.indexOf(playerToPlay) + 1;
        playerToPlay = this.get(numPlayer % this.size());
    }

    public Player getPlayer() {
        return playerToPlay;
    }

    void resetScores() {
        for (Player player : this) {
            player.resetAccumulatedScore();
            player.setScore(0);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.playerToPlay, flags);
    }

    protected Players(Parcel in) {
        this.playerToPlay = in.readParcelable(Player.class.getClassLoader());
    }

    public static final Parcelable.Creator<Players> CREATOR = new Parcelable.Creator<Players>() {
        @Override
        public Players createFromParcel(Parcel source) {
            return new Players(source);
        }

        @Override
        public Players[] newArray(int size) {
            return new Players[size];
        }
    };
}