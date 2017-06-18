package es.ulpgc.eii.android.project2.ui;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class BarScore {

    private ProgressBar progressBar;
    private TextView textViewNamePlayer;
    private TextView textViewScore;

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

    public void setMax(int max) {
        progressBar.setMax(max);
    }
}