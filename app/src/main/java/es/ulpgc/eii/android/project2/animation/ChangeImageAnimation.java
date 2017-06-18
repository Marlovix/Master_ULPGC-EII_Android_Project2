package es.ulpgc.eii.android.project2.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import es.ulpgc.eii.android.project2.tools.RandomNumbers;
import es.ulpgc.eii.android.project2.ui.GameObject;

public abstract class ChangeImageAnimation  {
/*
    static final int FRAMES = 100;

    private int animationDuration;
    private GameObject gameObject;

    public ChangeImageAnimation(GameObject gameObject, float secondsOfAnimation){
        new CountDownTimer(5000, 1500) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("MILLIS", String.valueOf(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                /*if (numberOfThrowing == 0) {
                    gameActivity.setAvailabilityButtons(true);
                    gameActivity.resetThrowButton();
                    gameActivity.collectAccumulated();
                } else {
                    //gameActivity.playMachine(numberOfThrowing);
                }
            }
        }.start();











        animationDuration = (int) (secondsOfAnimation * 1000); // Milliseconds
        int frameDuration = animationDuration / FRAMES;

        /* Add each frame to the animation */
       /* randomNumbers = RandomNumbers.getRandomNumbers(FRAMES, 0, 5); // Random number between 0 & 5
        for (int randomIndex : randomNumbers)
            this.addFrame(drawables[randomNumbers[randomIndex]], frameDuration);
    }

    @Override
    public void start() {
        super.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            public void run() {
                onAnimationFinish();
            }
        }, animationDuration);
    }

    // Called when the animation finishes //
    protected abstract void onAnimationFinish();
*/
}
