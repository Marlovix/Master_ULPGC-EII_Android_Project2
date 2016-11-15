package es.ulpgc.eii.android.project2;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import es.ulpgc.eii.android.project2.modal.Game;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class RetainedFragment extends Fragment {

    // data object we want to retain
    private Game game;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}