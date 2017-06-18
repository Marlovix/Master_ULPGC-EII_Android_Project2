package es.ulpgc.eii.android.project2.listener;

import android.content.DialogInterface;

import es.ulpgc.eii.android.project2.GameActivity;
import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.GameState;
import es.ulpgc.eii.android.project2.modal.Player;
import es.ulpgc.eii.android.project2.modal.Players;
import es.ulpgc.eii.android.project2.ui.GameAlertDialog;
import es.ulpgc.eii.android.project2.ui.GameObject;

public class PlayAgainListener implements DialogInterface.OnClickListener {

    private GameAlertDialog gameDialog;
    private Game game;
    private GameObject[] gameObjects;

    public PlayAgainListener(Game game, GameAlertDialog gameDialog, GameObject[] gameObjects) {
        this.gameDialog = gameDialog;
        this.game = game;
        this.gameObjects = gameObjects;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Player player;
        if (game.getGameState() == GameState.FINISH) {
            player = game.getTurnPlayer();
        } else { // Restart game from exit AlertDialog //
            Players players = game.getPlayers();
            player = players.get(0); // A new game always starts with Player1 //
        }

        game.setStateStart(); // Update game state //
        game.restart(player); // Start a new game with a new player //

        GameActivity gameActivity = (GameActivity) gameDialog.getContext();
        gameActivity.setExitAlertVisible(false);

        // Update views //
        for (GameObject gameObject : gameObjects) gameObject.startGame(game);

        gameDialog.dismiss();
    }
}