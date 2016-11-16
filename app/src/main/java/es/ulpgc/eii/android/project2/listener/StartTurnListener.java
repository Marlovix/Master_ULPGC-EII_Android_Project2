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
    private GameObject gameObject;

    public StartTurnListener(Game game, GameObject gameObject) {
        this.game = game;
        this.gameObject = gameObject;
    }

    // The text to start the turn disappears and enables the Throw Button //
    @Override
    public void onClick(View v) {
        game.setStateReady();
        gameObject.readyToPlay(game);
    }
}