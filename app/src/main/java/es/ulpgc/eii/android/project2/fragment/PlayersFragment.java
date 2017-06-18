package es.ulpgc.eii.android.project2.fragment;

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
import es.ulpgc.eii.android.project2.modal.Players;
import es.ulpgc.eii.android.project2.ui.BarScore;
import es.ulpgc.eii.android.project2.ui.GameObject;

public class PlayersFragment extends Fragment implements GameObject {

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
        ProgressBar progressBarPlayer1 = (ProgressBar) view.findViewById(R.id.progress_score_player1);

        TextView textViewPlayer2 = (TextView) view.findViewById(R.id.textView_player2);
        TextView textViewScorePlayer2 = (TextView) view.findViewById(R.id.textView_player2_score);
        ProgressBar progressBarPlayer2 = (ProgressBar) view.findViewById(R.id.progress_score_player2);

        barScorePlayer1 = new BarScore(textViewPlayer1, textViewScorePlayer1, progressBarPlayer1);
        barScorePlayer2 = new BarScore(textViewPlayer2, textViewScorePlayer2, progressBarPlayer2);

        return view;
    }

    private void renderScoreBoard(Game game) {
        setNames(game);
        showCurrentScores(game);
        setMax(game);
    }

    private void setNames(Game game) {
        Players players = game.getPlayers();
        barScorePlayer1.setNameBarScore(players.get(0).getName());
        barScorePlayer2.setNameBarScore(players.get(1).getName());
    }

    private void showCurrentScores(Game game) {
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);

        barScorePlayer1.setScore(player1.getScore());
        barScorePlayer2.setScore(player2.getScore());
    }

    private void setMax(Game game) {
        int max = game.getScoreToWin();
        barScorePlayer1.setMax(max);
        barScorePlayer2.setMax(max);
    }

    @Override
    public void finishGame(Game game) {
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
    public void readyToPlay(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void startGame(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void startTurn(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void throwingDie(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void showingScore(Game game) {
        renderScoreBoard(game);
    }
}
