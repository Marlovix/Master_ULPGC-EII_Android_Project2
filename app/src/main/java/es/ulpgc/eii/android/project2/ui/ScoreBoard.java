package es.ulpgc.eii.android.project2.ui;

import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.Players;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class ScoreBoard {

    private BarScore barScorePlayer1;
    private BarScore barScorePlayer2;

    public ScoreBoard(BarScore barScorePlayer1, BarScore barScorePlayer2) {
        this.barScorePlayer1 = barScorePlayer1;
        this.barScorePlayer2 = barScorePlayer2;
    }

    // Set the max score as maximum of progress in the ProgressBar widgets //
    public void setMax(int maxScore) {
        barScorePlayer1.setMax(maxScore);
        barScorePlayer2.setMax(maxScore);
    }

    public void setNames(Game game) {
        Players players = game.getPlayers();
        barScorePlayer1.setNameBarScore(players.get(0).getName());
        barScorePlayer2.setNameBarScore(players.get(1).getName());
    }

}