package es.ulpgc.eii.android.project2;

import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;

import es.ulpgc.eii.android.project2.fragment.DieFragment;
import es.ulpgc.eii.android.project2.fragment.PanelFragment;
import es.ulpgc.eii.android.project2.fragment.PlayersFragment;
import es.ulpgc.eii.android.project2.listener.ChooseTypeOfGameListener;
import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.GameState;
import es.ulpgc.eii.android.project2.modal.Player;
import es.ulpgc.eii.android.project2.tools.RandomNumbers;
import es.ulpgc.eii.android.project2.ui.GameAlertDialog;
import es.ulpgc.eii.android.project2.ui.GameObject;

public class GameActivity extends FragmentActivity implements PanelFragment.OnPanelFragmentInteraction {

    // Constants //
    static final String GAME_TAG = Game.class.getSimpleName();
    static final String EXIT_ALERT_VISIBILITY = GameAlertDialog.class.getSimpleName();
    static final int SCORE_TO_WIN = 20;
    static final int TIME_ANIMATION = 5000;

    // Attributes //
    private Game game;
    private GameObject[] gameObjects;
    private GameAlertDialog gameAlertDialog;
    private boolean exitAlertVisible;

    public Game getGame() {
        return game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the option value from the MainActivity (number of players) //
        Bundle b = getIntent().getExtras();
        int numberOfPlayers = b.getInt(ChooseTypeOfGameListener.NUMBER_OF_PLAYERS);
        boolean secondPlayerIsHuman = (numberOfPlayers == 2);

        setContentView(R.layout.activity_game);

        FragmentManager manager = getSupportFragmentManager();
        PlayersFragment playersFragment = (PlayersFragment) manager.findFragmentById(R.id.fragment_players);
        DieFragment dieFragment = (DieFragment) manager.findFragmentById(R.id.fragment_die);
        PanelFragment panelFragment = (PanelFragment) manager.findFragmentById(R.id.fragment_panel);

        // These objects with views are GameObjects, so they are stored in a global array //
        gameObjects = new GameObject[]{playersFragment, dieFragment, panelFragment};

        if (savedInstanceState == null) {
            // Game variables //
            int colorPlayer1 = ContextCompat.getColor(this, R.color.player1);
            String namePlayer1 = String.format(getResources().getString(R.string.player), 1);
            String namePlayer2;
            int colorPlayer2;
            if (secondPlayerIsHuman) {
                namePlayer2 = String.format(getResources().getString(R.string.player), 2);
                colorPlayer2 = ContextCompat.getColor(this, R.color.player2);
            } else {
                namePlayer2 = getResources().getString(R.string.machine);
                colorPlayer2 = ContextCompat.getColor(this, R.color.machine);
            }
            Player player1 = new Player(namePlayer1, colorPlayer1, true);
            Player player2 = new Player(namePlayer2, colorPlayer2, secondPlayerIsHuman);

            // The game is created with two players when the application is launched //
            game = new Game(player1, player2);
            game.setTimeAnimation(TIME_ANIMATION);

            // It is necessary set player who is going to start the game and the score to win //
            game.start(player1, SCORE_TO_WIN);

            // The AlertDialog to show the winner message or exit message -> onBackPressed() //
            gameAlertDialog = new GameAlertDialog(this, game, gameObjects);

            updateState();
        }
    }

    // The final call of system before the activity is destroyed, so the AlertDialog is closed //
    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameAlertDialog.dismiss();
    }

    // The system calls this before Activity is destroyed and saves the game state //
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(GAME_TAG, game);
        outState.putBoolean(EXIT_ALERT_VISIBILITY, exitAlertVisible); // is EXIT AlertDialog shown?

        // Cancel animation //
        DieFragment dieFragment = (DieFragment) gameObjects[1];
        dieFragment.cancelAnimation();

        GameState gameState = game.getGameState();
        if (gameState == GameState.FINISH) game.changeTurn();

        super.onSaveInstanceState(outState);
    }

    // The game state is restored and set views according that state //
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        game = savedInstanceState.getParcelable(GAME_TAG);
        exitAlertVisible = savedInstanceState.getBoolean(EXIT_ALERT_VISIBILITY);
        // The AlertDialog to show the winner message or exit message -> onBackPressed() //
        gameAlertDialog = new GameAlertDialog(this, game, gameObjects);
        updateState();
        if (exitAlertVisible) gameAlertDialog.show("EXIT");
    }

    // Ask if exit or start a new game on AlertDialog //
    @Override
    public void onBackPressed() {
        gameAlertDialog.show("EXIT");
        exitAlertVisible = true;
    }

    // Depending on the game state is executed a method //
    public void updateState() {
        switch (game.getGameState()) {
            case GAME:
                gamePlay();
                break;
            case ONE:
                lostTurnByOne();
                break;
            case READY:
                readyToPlay();
                break;
            case START:
                startGame();
                break;
            case TURN:
                startTurn();
                break;
            case FINISH:
                finishGame();
                break;
            case THROWING:
                throwingDie();
                break;
            case SCORE:
                showingScore();
                break;

        }
    }

    // These methods only loop the gameObjects array and execute the methods of the interface //
    private void finishGame() {
        gameAlertDialog.show("FINISH");
        for (GameObject gameObject : gameObjects) gameObject.finishGame(game);
    }

    private void gamePlay() {
        for (GameObject gameObject : gameObjects) gameObject.gamePlay(game);
    }

    private void lostTurnByOne() {
        for (GameObject gameObject : gameObjects) gameObject.lostTurnByOne(game);
    }

    private void readyToPlay() {
        for (GameObject gameObject : gameObjects) gameObject.readyToPlay(game);
    }

    private void startGame() {
        for (GameObject gameObject : gameObjects) gameObject.startGame(game);
        //exitAlertVisible = false;
    }

    private void startTurn() {
        for (GameObject gameObject : gameObjects) gameObject.startTurn(game);
    }

    private void throwingDie() {
        for (GameObject gameObject : gameObjects) gameObject.throwingDie(game);
    }

    private void showingScore() {
        for (GameObject gameObject : gameObjects) gameObject.showingScore(game);
    }

    public void checkThrowingValue() {
        int throwingValue = game.throwDie(); // Random number between 1 and 6 //

        // Condition for turn change (Value of die throwing = 1) //
        if (throwingValue == 1) {
            game.setStateOne(); // Update game state //
            game.changeTurn();
        } else {
            Player playerPlaying = game.getTurnPlayer();
            int currentAccumulatedScore = game.getAccumulatedScore();
            int currentScore = playerPlaying.getScore();
            int newAccumulatedScore = currentAccumulatedScore + throwingValue;
            int newScore = currentAccumulatedScore + currentScore + throwingValue;

            if (newScore >= game.getScoreToWin()) { // Player wins //
                game.setStateWinner(); // Update game state //
                playerPlaying.setScore(SCORE_TO_WIN);
            } else { // The score of the die throwing is accumulated //
                game.setStateGame(); // Update game state //
            }

            game.setAccumulatedScore(newAccumulatedScore); // Accumulate score //

            if (game.getGameState() == GameState.GAME && !playerPlaying.isHuman()) {
                if (game.getMachineThrowing() != 0) {
                    int machineThrowing = game.getMachineThrowing();
                    machineThrowing--;
                    game.setMachineThrowing(machineThrowing);
                    game.setMachineThrowingValue(throwingValue);
                    game.setStateScore();
                }
            }
        }

        updateState();
    }

    public void resetTimeAnimation() {
        game.setTimeAnimation(TIME_ANIMATION);
    }

    public void setExitAlertVisible(boolean exitAlertVisible) {
        this.exitAlertVisible = exitAlertVisible;
    }

    /**
     * * * * * * * * * * * * * * * * * * * * * * *
     * PanelFragment interaction implementation  *
     * * * * * * * * * * * * * * * * * * * * * *
     **/

    @Override
    public void startTurnToPlay() {
        game.setAccumulatedScore(0); // Restart the accumulated score //

        Player player = game.getTurnPlayer();
        if (player.isHuman()) {
            game.setStateReady(); // Update game state //
        } else {
            game.setStateThrowing();
        }

        updateState();
    }

    // It starts the animation of throwing and check the result when the animation finishes //
    @Override
    public void throwDie() {
        game.setStateThrowing(); // Update game state //
        updateState();
    }

    @Override
    public void collectAccumulated() {
        Player playerPlaying = game.getTurnPlayer();
        int accumulatedScore = game.getAccumulatedScore();
        int currentScore = playerPlaying.getScore();
        int newScore = accumulatedScore + currentScore;
        playerPlaying.setScore(newScore);

        game.changeTurn();
        Player nextPlayer = game.getTurnPlayer();
        if (!nextPlayer.isHuman()) {
            int numberOfMachineThrowing = RandomNumbers.showRandomInteger(1, 10);
            game.setMachineThrowing(numberOfMachineThrowing);
        }

        game.setStateTurn(); // Update game state //
        updateState();
    }

}