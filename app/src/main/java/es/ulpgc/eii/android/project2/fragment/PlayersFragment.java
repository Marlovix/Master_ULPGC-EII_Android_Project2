package es.ulpgc.eii.android.project2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.ulpgc.eii.android.project2.R;
import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.Player;
import es.ulpgc.eii.android.project2.ui.BarScore;
import es.ulpgc.eii.android.project2.ui.GameObject;
import es.ulpgc.eii.android.project2.ui.ScoreBoard;

public class PlayersFragment extends Fragment implements GameObject{

    private ScoreBoard scoreBoard;
    private BarScore barScorePlayer1;
    private BarScore barScorePlayer2;

    public PlayersFragment() {
        // Required empty public constructor
    }

    // Cycle 3 //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        TextView textViewPlayer1 = (TextView) view.findViewById(R.id.textView_player1);
        TextView textViewScorePlayer1 = (TextView) view.findViewById(R.id.textView_player1_score);
        ProgressBar progressBarPlayer1 = (ProgressBar) view.findViewById(R.id.progressBar_score_player1);

        TextView textViewPlayer2 = (TextView) view.findViewById(R.id.textView_player2);
        TextView textViewScorePlayer2 = (TextView) view.findViewById(R.id.textView_player2_score);
        ProgressBar progressBarPlayer2 = (ProgressBar) view.findViewById(R.id.progressBar_score_player2);

        BarScore barScorePlayer1 =
                new BarScore(textViewPlayer1, textViewScorePlayer1, progressBarPlayer1);
        BarScore barScorePlayer2 =
                new BarScore(textViewPlayer2, textViewScorePlayer2, progressBarPlayer2);

        scoreBoard = new ScoreBoard(barScorePlayer1, barScorePlayer2);

        return view;
    }

    public BarScore getBarScorePlayer1() {
        return barScorePlayer1;
    }

    public BarScore getBarScorePlayer2() {
        return barScorePlayer2;
    }

    private void renderScoreBoard(Game game) {
        scoreBoard.setNames(game);
        showCurrentScores(game);
        scoreBoard.setMax(game.getMaxScore());
    }

    private void showCurrentScores(Game game) {
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);

        barScorePlayer1.setScore(player1.getScore());
        barScorePlayer2.setScore(player2.getScore());
    }

    @Override
    public void startGame(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void readyToPlay(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void gamePlay(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void lostTurnByOne(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void finishGame(Game game) {
        renderScoreBoard(game);
    }
}
