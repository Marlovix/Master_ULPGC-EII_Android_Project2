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

public class GameAlertDialog {
    private Context context;
    private AlertDialog dialog;
    private Game game;
    private GameObject[] gameObjects;

    public GameAlertDialog(Context context, Game game, GameObject[] gameObjects) {
        this.context = context;
        this.game = game;
        this.gameObjects = gameObjects;
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    // Depending on param it shown the FINISH AlertDialog or the EXIT AlertDialog //
    public void show(String typeAlert) {
        if (dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            Player winner = game.getTurnPlayer();

            String title = context.getString(R.string.button_final);
            String message = "";
            String positive = "";
            switch (typeAlert) {
                case "EXIT":
                    message = context.getString(R.string.label_exit_question);
                    positive = context.getString(R.string.start_new_game);
                    break;
                case "FINISH":
                    game.changeTurn();
                    message = String.format(context.getString(R.string.label_won), winner.getName());
                    positive = context.getString(R.string.button_play_again);
                    break;
            }
            String negative = context.getString(R.string.button_exit);

            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton(positive, new PlayAgainListener(game, this, gameObjects));
            builder.setNegativeButton(negative, new ExitListener(context));
            builder.setCancelable(false); // Avoid close the alert with back button of the device //

            dialog = builder.show();

            if (typeAlert.equals("FINISH")) {
                TextView textViewMessage = (TextView) dialog.findViewById(android.R.id.message);
                textViewMessage.setGravity(Gravity.CENTER);
            }
        }
    }

    public Context getContext() {
        return context;
    }
}
