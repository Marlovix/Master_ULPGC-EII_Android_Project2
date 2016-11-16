package es.ulpgc.eii.android.project2.animation;

import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;

import es.ulpgc.eii.android.project2.tools.RandomNumbers;
import es.ulpgc.eii.android.project2.MainActivity;

public class MachinePlayingAnimation extends ChangeImageAnimation{

    private MainActivity gameActivity;
    private Drawable[] drawables;
    private int numberOfThrowing;

    public MachinePlayingAnimation(MainActivity gameActivity,
                                   Drawable[] drawables, int numberOfThrowing,
                                   int numberOfFrames, float secondsOfAnimation) {
        super(drawables, numberOfFrames, secondsOfAnimation);

        this.gameActivity = gameActivity;
        this.numberOfThrowing = numberOfThrowing;
        this.drawables = drawables;
        this.setOneShot(true);
    }

    @Override
    protected void onAnimationFinish() {
        numberOfThrowing--;

        int throwingValue = RandomNumbers.showRandomInteger(1, drawables.length);
        gameActivity.setDieImage(throwingValue);

        if (gameActivity.throwingValueIsValid(throwingValue)){
            new CountDownTimer(5000, 1500) {
                @Override
                public void onTick(long millisUntilFinished) {
                    gameActivity.addDot();
                }

                @Override
                public void onFinish() {
                    if (numberOfThrowing == 0){
                        gameActivity.setAvailabilityButtons(true);
                        gameActivity.resetThrowButton();
                        gameActivity.collectAccumulated();
                    }else{
                        gameActivity.playMachine(numberOfThrowing);
                    }
                }
            }.start();
        }else{
            gameActivity.setAvailabilityButtons(true);
            gameActivity.resetThrowButton();
        }
    }
}