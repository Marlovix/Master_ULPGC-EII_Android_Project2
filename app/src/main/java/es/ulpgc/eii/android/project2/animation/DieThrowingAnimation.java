package es.ulpgc.eii.android.project2.animation;

import android.graphics.drawable.Drawable;

import es.ulpgc.eii.android.project2.GameActivity;
import es.ulpgc.eii.android.project2.tools.RandomNumbers;
import es.ulpgc.eii.android.project2.MainActivity;
import es.ulpgc.eii.android.project2.ui.GameObject;

public class DieThrowingAnimation {

    /*private Drawable[] drawables;
    private GameActivity gameActivity;
    private int timeAnimation;

    public DieThrowingAnimation(GameObject gameObject, Drawable[] drawables,
                                int numberOfFrames, float secondsOfAnimation) {
        super(drawables, numberOfFrames, secondsOfAnimation);
        this.gameActivity = gameActivity;
        this.drawables = drawables;
        this.setOneShot(true);
    }

    // Both buttons are disabled after click the throw button //
    @Override
    public void start(){
        super.start();
        //gameActivity.setAvailabilityButtons(false);
    }

    @Override
    protected void onAnimationFinish() {
        int throwingValue = RandomNumbers.showRandomInteger(1, drawables.length);
        //gameActivity.setDieImage(throwingValue);
        //gameActivity.setAvailabilityButtons(true);
        //gameActivity.throwingValueIsValid(throwingValue);
    }*/
}