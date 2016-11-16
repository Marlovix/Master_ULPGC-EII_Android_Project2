package es.ulpgc.eii.android.project2.animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import es.ulpgc.eii.android.project2.tools.RandomNumbers;

public abstract class ChangeImageAnimation extends AnimationDrawable {

    private int animationDuration;
    private Drawable[] drawables;
    protected int[] randomNumbers;

    public ChangeImageAnimation(Drawable[] drawables, int numberOfFrames, float secondsOfAnimation){
        this.drawables = drawables;

        animationDuration = (int) (secondsOfAnimation * 1000); // Milliseconds
        int frameDuration = animationDuration / numberOfFrames;

        /* Add each frame to the animation */
        randomNumbers = RandomNumbers.getRandomNumbers(numberOfFrames, 0, drawables.length-1);
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

}
