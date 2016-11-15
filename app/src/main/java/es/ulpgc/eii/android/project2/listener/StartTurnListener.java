package es.ulpgc.eii.android.project2.listener;

import android.view.View;

import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.ui.GameObject;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class StartTurnListener implements View.OnClickListener {

    private Game game;
    private GameObject[] gameObjects;

    public StartTurnListener(Game game, GameObject... gameObjects) {
        this.game = game;
        this.gameObjects = gameObjects;
    }

    // The text to start the turn disappears and enables the Throw Button //
    @Override
    public void onClick(View v) {
        game.setStateReady();
        for (GameObject gameObject : gameObjects) gameObject.readyToPlay(game);
    }
}