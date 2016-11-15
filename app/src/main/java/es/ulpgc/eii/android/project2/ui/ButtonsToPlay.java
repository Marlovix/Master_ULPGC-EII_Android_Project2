package es.ulpgc.eii.android.project2.ui;

import android.view.View;
import android.widget.Button;

import es.ulpgc.eii.android.project2.modal.Game;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class which contains the buttons that are necessaries to play //
public class ButtonsToPlay extends GameObject {

    private Button buttonThrow;
    private Button buttonCollect;

    public ButtonsToPlay(Button buttonThrow, Button buttonCollect) {
        this.buttonThrow = buttonThrow;
        this.buttonCollect = buttonCollect;
    }

    @Override
    public void startGame(Game game) {
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
    }

    @Override
    public void readyToPlay(Game game) {
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.VISIBLE);
    }

    @Override
    public void gamePlay(Game game) {
        buttonThrow.setVisibility(View.VISIBLE);
        buttonCollect.setVisibility(View.VISIBLE);
    }

    @Override
    public void lostTurnByOne(Game game) {
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
    }

    @Override
    public void finishGame(Game game) {
        buttonThrow.setVisibility(View.VISIBLE);
        buttonCollect.setVisibility(View.VISIBLE);
    }
}
