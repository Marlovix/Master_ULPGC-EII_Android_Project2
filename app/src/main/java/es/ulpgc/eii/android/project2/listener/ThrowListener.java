package es.ulpgc.eii.android.project2.listener;

import android.view.View;

import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.GameState;
import es.ulpgc.eii.android.project2.modal.Player;
import es.ulpgc.eii.android.project2.ui.FinalAlertDialog;
import es.ulpgc.eii.android.project2.ui.GameObject;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class ThrowListener implements View.OnClickListener {

    private Game game;
    private GameObject[] gameObjects;

    public ThrowListener(Game game, GameObject[] gameObjects) {
        this.game = game;
        this.gameObjects = gameObjects;
    }

    @Override
    public void onClick(View v) {

    }



}