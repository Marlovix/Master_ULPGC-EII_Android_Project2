package es.ulpgc.eii.android.project2.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import es.ulpgc.eii.android.project2.R;
import es.ulpgc.eii.android.project2.listener.ExitListener;
import es.ulpgc.eii.android.project2.listener.PlayAgainListener;
import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.Player;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class FinalAlertDialog {

    public static void show(Context context, Game game, GameObject... gameObjects) {
        Player winner = game.getTurnPlayer();

        String title = context.getString(R.string.button_final);
        String message = String.format(context.getString(R.string.label_won), winner.getName());

        String positive = context.getString(R.string.button_play_again);
        String negative = context.getString(R.string.button_exit);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positive, new PlayAgainListener(game, gameObjects));
        builder.setNegativeButton(negative, new ExitListener(context));
        builder.setCancelable(false);

        // Center the message of the Alert Dialog //
        AlertDialog dialog = builder.show();
        TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        dialog.show();
    }

}
