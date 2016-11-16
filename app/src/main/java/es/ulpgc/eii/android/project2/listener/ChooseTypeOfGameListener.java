package es.ulpgc.eii.android.project2.listener;

import android.content.Intent;
import android.view.View;

import es.ulpgc.eii.android.project2.MainActivity;

public class ChooseTypeOfGameListener implements View.OnClickListener{

    public static final String NUMBER_OF_PLAYERS = "ChooseTypeOfGameListener.NUMBER_OF_PLAYERS";

    private int numberOfPlayers;

    public ChooseTypeOfGameListener(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        intent.putExtra(NUMBER_OF_PLAYERS, numberOfPlayers);
        v.getContext().startActivity(intent);
    }
}