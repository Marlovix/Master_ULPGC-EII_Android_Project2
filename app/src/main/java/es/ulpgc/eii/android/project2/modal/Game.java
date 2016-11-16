package es.ulpgc.eii.android.project2.modal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collections;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class Game implements Parcelable {


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.gameState == null ? -1 : this.gameState.ordinal());
        dest.writeParcelable(this.players, flags);
        dest.writeParcelable(this.die, flags);
        dest.writeInt(this.maxScore);
        dest.writeInt(this.lastThrowing);
    }

    protected Game(Parcel in) {
        int tmpGameState = in.readInt();
        this.gameState = tmpGameState == -1 ? null : GameState.values()[tmpGameState];
        this.players = in.readParcelable(Players.class.getClassLoader());
        this.die = in.readParcelable(Die.class.getClassLoader());
        this.maxScore = in.readInt();
        this.lastThrowing = in.readInt();
    }

    public static final Parcelable.Creator<Game> CREATOR = new Parcelable.Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}