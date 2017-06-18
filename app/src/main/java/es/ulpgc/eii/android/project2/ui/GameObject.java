package es.ulpgc.eii.android.project2.ui;

import es.ulpgc.eii.android.project2.modal.Game;

public interface GameObject {

    void finishGame(Game game);

    void gamePlay(Game game);

    void lostTurnByOne(Game game);

    void readyToPlay(Game game);

    void startGame(Game game);

    void startTurn(Game game);

    void throwingDie(Game game);

    void showingScore(Game game);
}
