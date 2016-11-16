package es.ulpgc.eii.android.project2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;

import es.ulpgc.eii.android.project2.animation.ChangeImageAnimation;
import es.ulpgc.eii.android.project2.animation.DieThrowingAnimation;
import es.ulpgc.eii.android.project2.fragment.DieFragment;
import es.ulpgc.eii.android.project2.fragment.PanelFragment;
import es.ulpgc.eii.android.project2.fragment.PlayersFragment;
import es.ulpgc.eii.android.project2.fragment.RetainedFragment;
import es.ulpgc.eii.android.project2.listener.ChooseTypeOfGameListener;
import es.ulpgc.eii.android.project2.listener.CollectListener;
import es.ulpgc.eii.android.project2.listener.StartTurnListener;
import es.ulpgc.eii.android.project2.listener.ThrowListener;
import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.GameState;
import es.ulpgc.eii.android.project2.modal.Player;
import es.ulpgc.eii.android.project2.ui.FinalAlertDialog;
import es.ulpgc.eii.android.project2.ui.GameObject;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class GameActivity extends FragmentActivity implements PanelFragment.OnPanelFragmentInteraction{

    private static final String TAG_DATA_FRAGMENT = GameActivity.class.getSimpleName();
    private static final String SAVE_GAME_STATE = Game.class.getSimpleName();
    private Game game;
    private GameObject[] gameObjects;

    private PlayersFragment playersFragment;
    private DieFragment dieFragment;
    private PanelFragment panelFragment;

    public Game getGame(){
        return game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle b = getIntent().getExtras();
        int numberOfPlayers = b.getInt(ChooseTypeOfGameListener.NUMBER_OF_PLAYERS);
        boolean secondPlayerIsHuman = (numberOfPlayers == 2);

        FragmentManager manager = getSupportFragmentManager();
        playersFragment = (PlayersFragment) manager.findFragmentById(R.id.fragment_players);
        dieFragment = (DieFragment) manager.findFragmentById(R.id.fragment_die);
        panelFragment = (PanelFragment) manager.findFragmentById(R.id.fragment_panel);
        savedInstanceState.putParcelable(SAVE_GAME_STATE, game);
        panelFragment.setArguments(savedInstanceState);
        gameObjects = new GameObject[]{playersFragment,dieFragment,panelFragment};

        // Create the fragment and data the first time //
        RetainedFragment dataFragment = (RetainedFragment) manager.findFragmentByTag(TAG_DATA_FRAGMENT);
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

    public void startAnimation(ChangeImageAnimation animation){
        dieFragment.getImageViewDie().setVisibility(View.VISIBLE);
        dieFragment.setBackground(animation);

        animation.start();
    }

    /** * * * * * * * * * * * * * * * * * * * * * * *
     *   PanelFragment interaction implementation  *
     * * * * * * * * * * * * * * * * * * * * * * **/

    // It starts the animation of throwing and check the result when the animation finishes //
    @Override
    public void throwDie() {
        // Condition for turn change (Value of die throwing = 1) //
        int throwingValue = game.throwDie();

        if (throwingValue == 1) {
            game.setStateOne();
            game.changeTurn();
        } else {
            // The player who is playing wins the game //
            Player playerPlaying = game.getTurnPlayer();
            int accumulatedScore = playerPlaying.getAccumulatedScore();
            int currentScore = playerPlaying.getScore();
            int newScore = accumulatedScore + currentScore + throwingValue;
            if (newScore >= game.getMaxScore()) { // Player wins //
                game.setStateWinner();
                playerPlaying.setScore(newScore);
                FinalAlertDialog.show(this, game, gameObjects);
            } else { // The score of the die throwing is accumulated //
                game.setStateGame();
                playerPlaying.addAccumulatedScore(throwingValue);
            }
        }

        // Initialization of animation with 25 frames per second for 5 seconds //
        Drawable[] faces = dieFragment.getFacesDie();
        ChangeImageAnimation animation =
                new DieThrowingAnimation(this, faces, 25, 5);

        startAnimation(animation);

        updateViewByState(gameObjects, game.getGameState());
    }

    @Override
    public void collectAccumulated() {
        game.setStateStart();

        Player playerPlaying = game.getTurnPlayer();
        int accumulatedScore = playerPlaying.getAccumulatedScore();
        int currentScore = playerPlaying.getScore();
        int newScore = accumulatedScore + currentScore;

        playerPlaying.setScore(newScore);
        playerPlaying.resetAccumulatedScore();

        game.changeTurn();

        for (GameObject gameObject : gameObjects) gameObject.startGame(game);

    }

    private void updateViewByState(GameObject[] gameObjects, GameState gameState) {
        switch (gameState) {
            case ONE:
                for (GameObject gameObject : gameObjects) gameObject.lostTurnByOne(game);
                break;
            case WINNER:
                for (GameObject gameObject : gameObjects) gameObject.finishGame(game);
                break;
            case GAME:
                for (GameObject gameObject : gameObjects) gameObject.gamePlay(game);
                break;
        }
    }

}