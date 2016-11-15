package es.ulpgc.eii.android.project2.listener;

import android.content.DialogInterface;

import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.Player;
import es.ulpgc.eii.android.project2.ui.GameObject;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class PlayAgainListener implements DialogInterface.OnClickListener {

    private Game game;
    private GameObject[] gameObjects;

    public PlayAgainListener(Game game, GameObject[] gameObjects) {
        this.game = game;
        this.gameObjects = gameObjects;
    }

    // The player closes the final dialog, then the game starts a new game with the loser player //
    @Override
    public void onClick(DialogInterface dialog, int which) {
        game.setStateStart();

        // Set new player to start game //
        game.changeTurn();
        Player newPlayer = game.getTurnPlayer();
        game.start(newPlayer);

        for (GameObject gameObject : gameObjects) gameObject.startGame(game);

        dialog.dismiss();
    }
}