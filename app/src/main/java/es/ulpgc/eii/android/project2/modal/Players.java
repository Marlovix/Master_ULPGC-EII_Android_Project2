package es.ulpgc.eii.android.project2.modal;

import java.util.ArrayList;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class witch controls the group of players and
// returns the player who has to play //
public class Players extends ArrayList<Player> {

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

}