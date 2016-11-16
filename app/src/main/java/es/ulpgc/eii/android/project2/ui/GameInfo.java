package es.ulpgc.eii.android.project2.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import es.ulpgc.eii.android.project2.R;
import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.Player;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class which contains the text reports about the state of the game, as the accumulated score,
// the name of the player who is playing and the clickable text to start the turn with a color //
public class GameInfo {

    private TextView textViewPlayerToPlay;
    private TextView textViewStartTurn;
    private TextView textViewAccumulated;

    public GameInfo(TextView textViewAccumulated,
                    TextView textViewPlayerToPlay, TextView textViewStartTurn) {
        this.textViewAccumulated = textViewAccumulated;
        this.textViewPlayerToPlay = textViewPlayerToPlay;
        this.textViewStartTurn = textViewStartTurn;
    }

    public void setPlayerInfo(Player player) {
        Context context = textViewPlayerToPlay.getContext();
        int accumulatedScore = player.getAccumulatedScore();
        int colorText = player.getColor();
        String newPlayer = player.getName();
        String textNewTurn = String.format(context.getString(R.string.label_start_turn), newPlayer);
        updateAccumulatedView(accumulatedScore);
        textViewPlayerToPlay.setText(newPlayer);
        textViewStartTurn.setText(textNewTurn);
        textViewStartTurn.setTextColor(colorText);
    }

    // The only element which needs a update is the accumulated score //
    private void updateAccumulatedView(int accumulatedScore) {
        Context context = textViewAccumulated.getContext();
        String textAccumulated =
                String.format(context.getString(R.string.label_accumulated), accumulatedScore);
        textViewAccumulated.setText(textAccumulated);
    }

}