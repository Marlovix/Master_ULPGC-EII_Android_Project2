package es.ulpgc.eii.android.project2.ui;

import es.ulpgc.eii.android.project2.modal.Game;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public interface GameObject {
    void startGame(Game game);
    void readyToPlay(Game game);
    void gamePlay(Game game);
    void lostTurnByOne(Game game);
    void finishGame(Game game);
}
