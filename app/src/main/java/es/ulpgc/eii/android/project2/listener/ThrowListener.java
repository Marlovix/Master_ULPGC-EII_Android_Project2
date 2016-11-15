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
        // Condition for turn change (Value of die throwing = 1) //
        int throwingValue = game.throwDie();

        if (throwingValue == 1) {
            game.setStateOne();
            game.changeTurn();
        } else {
            // The player who is playing wins the game //
            Player playerPlaying = game.getTurnPlayer();
            int accumulatedScore = playerPlaying.getAccumulatedScore();
            int currentScore = playerPlaying.getScore();
            int newScore = accumulatedScore + currentScore + throwingValue;
            if (newScore >= game.getMaxScore()) { // Player wins //
                game.setStateWinner();
                playerPlaying.setScore(newScore);
                FinalAlertDialog.show(v.getContext(), game, gameObjects);
            } else { // The score of the die throwing is accumulated //
                game.setStateGame();
                playerPlaying.addAccumulatedScore(throwingValue);
            }
        }

        updateViewByState(gameObjects, game.getGameState());
    }

    private void updateViewByState(GameObject[] gameObjects, GameState gameState) {
        switch (gameState) {
            case ONE:
                for (GameObject gameObject : gameObjects) gameObject.lostTurnByOne(game);
                break;
            case WINNER:
                for (GameObject gameObject : gameObjects) gameObject.finishGame(game);
                break;
            case GAME:
                for (GameObject gameObject : gameObjects) gameObject.gamePlay(game);
                break;
        }
    }

}