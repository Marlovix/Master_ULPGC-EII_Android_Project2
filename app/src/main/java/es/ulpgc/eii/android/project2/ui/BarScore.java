package es.ulpgc.eii.android.project2.ui;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class which updates the view of the widgets related with the player score //
public class BarScore {

    private TextView textViewNamePlayer;
    private TextView textViewScore;
    private ProgressBar progressBar;

    public BarScore(TextView textViewNamePlayer, TextView textViewScore, ProgressBar progressBar) {
        this.textViewNamePlayer = textViewNamePlayer;
        this.textViewScore = textViewScore;
        this.progressBar = progressBar;
    }

    public void setNameBarScore(String name) {
        textViewNamePlayer.setText(name);
    }

    public void setScore(int score) {
        progressBar.setProgress(score);
        textViewScore.setText(String.format(Locale.getDefault(), "%d", score));
    }

    void setMax(int max) {
        progressBar.setMax(max);
    }
}