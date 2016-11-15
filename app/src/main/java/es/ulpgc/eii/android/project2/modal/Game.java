package es.ulpgc.eii.android.project2.modal;

import java.util.Collections;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class Game {

    private GameState gameState;
    private Players players;
    private Die die;
    private int maxScore;
    private int lastThrowing;

    public Game(Player... players) {
        gameState = GameState.START;

        // 100 points are necessary to win the game //
        maxScore = 20;
        lastThrowing = 0;

        die = new Die();

        // Players with their bar scores //
        this.players = new Players();
        Collections.addAll(this.players, players);
    }

    public int getLastThrowing() {
        return lastThrowing;
    }

    // The game starts with the player who is set as parameter //
    public void start(Player firstPlayer) {
        this.players.setFirstPlayer(firstPlayer);
        this.players.resetScores();
    }

    public Player getTurnPlayer() {
        return this.players.getPlayer();
    }

    // When a turn is going to be started, the player is changed,
    // the game gameState is updated and the buttons are hidden //
    public void changeTurn() {
        players.changePlayer();
    }

    public int throwDie() {
        lastThrowing = die.getValue();
        return lastThrowing;
    }

    public Players getPlayers() {
        return players;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setStateStart() {
        gameState = GameState.START;
    }

    public void setStateReady() {
        gameState = GameState.READY;
    }

    public void setStateGame() {
        gameState = GameState.GAME;
    }

    public void setStateOne() {
        gameState = GameState.ONE;
    }

    public void setStateWinner() {
        gameState = GameState.WINNER;
    }

}