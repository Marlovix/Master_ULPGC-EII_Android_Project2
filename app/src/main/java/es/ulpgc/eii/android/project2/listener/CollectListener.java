package es.ulpgc.eii.android.project2.listener;

import android.view.View;

import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.Player;
import es.ulpgc.eii.android.project2.ui.GameObject;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class CollectListener implements View.OnClickListener {

    private Game game;
    private GameObject[] gameObjects;

    public CollectListener(Game game, GameObject[] gameObjects) {
        this.game = game;
        this.gameObjects = gameObjects;
    }

    // When the player collects the accumulated score the game changes the player to play //
    @Override
    public void onClick(View v) {
        game.setStateStart();

        Player playerPlaying = game.getTurnPlayer();
        int accumulatedScore = playerPlaying.getAccumulatedScore();
        int currentScore = playerPlaying.getScore();
        int newScore = accumulatedScore + currentScore;

        playerPlaying.setScore(newScore);
        playerPlaying.resetAccumulatedScore();

        game.changeTurn();

        for (GameObject gameObject : gameObjects) gameObject.startGame(game);
    }
}