package es.ulpgc.eii.android.project2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import es.ulpgc.eii.android.project2.listener.CollectListener;
import es.ulpgc.eii.android.project2.listener.StartTurnListener;
import es.ulpgc.eii.android.project2.listener.ThrowListener;
import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.Player;
import es.ulpgc.eii.android.project2.ui.BarScore;
import es.ulpgc.eii.android.project2.ui.ButtonsToPlay;
import es.ulpgc.eii.android.project2.ui.DieView;
import es.ulpgc.eii.android.project2.ui.FinalAlertDialog;
import es.ulpgc.eii.android.project2.ui.GameInfo;
import es.ulpgc.eii.android.project2.ui.GameObject;
import es.ulpgc.eii.android.project2.ui.ScoreBoard;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class MainActivity extends FragmentActivity {

    private static final String TAG_DATA_FRAGMENT = MainActivity.class.getSimpleName();
    private Game game;
    private GameObject[] gameObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the retained fragment on activity restart //
        FragmentManager manager = getSupportFragmentManager();
        RetainedFragment dataFragment = (RetainedFragment) manager.findFragmentByTag(TAG_DATA_FRAGMENT);

        // Create the fragment and data the first time //
        if (dataFragment == null) {
            // Add the fragment //
            dataFragment = new RetainedFragment();
            manager.beginTransaction().add(dataFragment, TAG_DATA_FRAGMENT).commit();

            /* Data objects initialization */
            String namePlayer1 = String.format(getResources().getString(R.string.player), 1);
            String namePlayer2 = String.format(getResources().getString(R.string.player), 2);

            int colorPlayer1 = Color.parseColor("#0000FF"); // Color Blue
            int colorPlayer2 = Color.parseColor("#FF0000"); // Color Red

            Player player1 = new Player(namePlayer1, colorPlayer1);
            Player player2 = new Player(namePlayer2, colorPlayer2);

            // The game is created with two players when the application is launched //
            game = new Game(player1, player2);
            game.start(player1);
            dataFragment.setGame(game);
        } else {
            game = dataFragment.getGame();
        }

        initViews();

        switch (game.getGameState()) {
            case START:
                startGame();
                break;
            case READY:
                readyToPlay();
                break;
            case GAME:
                gamePlay();
                break;
            case ONE:
                lostTurnByOne();
                break;
            case WINNER:
                finishGame();
                FinalAlertDialog.show(this, game, gameObjects);
                break;
        }
    }

    private void initViews(){
        /* Views initialization */
        TextView textViewPlayer1 = (TextView) findViewById(R.id.textView_player1);
        TextView textViewScorePlayer1 = (TextView) findViewById(R.id.textView_player1_score);
        ProgressBar progressBarPlayer1 = (ProgressBar) findViewById(R.id.progressBar_score_player1);

        TextView textViewPlayer2 = (TextView) findViewById(R.id.textView_player2);
        TextView textViewScorePlayer2 = (TextView) findViewById(R.id.textView_player2_score);
        ProgressBar progressBarPlayer2 = (ProgressBar) findViewById(R.id.progressBar_score_player2);

        ImageView imageViewDie = (ImageView) findViewById(R.id.imageView_die);

        TextView textViewAccumulated = (TextView) findViewById(R.id.textView_accumulated_score);
        TextView textViewPlayerToPlay = (TextView) findViewById(R.id.textView_player_turn);
        TextView textViewStartTurn = (TextView) findViewById(R.id.textView_start_turn);

        Button buttonCollect = (Button) findViewById(R.id.button_collect);
        Button buttonThrow = (Button) findViewById(R.id.button_throw);

        BarScore barScorePlayer1 =
                new BarScore(textViewPlayer1, textViewScorePlayer1, progressBarPlayer1);
        BarScore barScorePlayer2 =
                new BarScore(textViewPlayer2, textViewScorePlayer2, progressBarPlayer2);

        ScoreBoard scoreBoard = new ScoreBoard(barScorePlayer1, barScorePlayer2);
        DieView dieView = new DieView(imageViewDie);
        GameInfo gameInfo = new GameInfo(textViewAccumulated, textViewPlayerToPlay, textViewStartTurn);
        ButtonsToPlay buttons = new ButtonsToPlay(buttonThrow, buttonCollect);

        gameObjects = new GameObject[]{scoreBoard, dieView, gameInfo, buttons};

        /* Listeners */
        buttonThrow.setOnClickListener(new ThrowListener(game, gameObjects));
        buttonCollect.setOnClickListener(new CollectListener(game, gameObjects));
        textViewStartTurn.setOnClickListener(new StartTurnListener(game, gameObjects));
    }

    private void startGame() {
        for (GameObject gameObject : gameObjects) gameObject.startGame(game);
    }

    private void readyToPlay() {
        for (GameObject gameObject : gameObjects) gameObject.readyToPlay(game);
    }

    private void gamePlay() {
        for (GameObject gameObject : gameObjects) gameObject.gamePlay(game);
    }

    private void lostTurnByOne() {
        for (GameObject gameObject : gameObjects) gameObject.lostTurnByOne(game);
    }

    private void finishGame() {
        for (GameObject gameObject : gameObjects) gameObject.finishGame(game);
    }

}