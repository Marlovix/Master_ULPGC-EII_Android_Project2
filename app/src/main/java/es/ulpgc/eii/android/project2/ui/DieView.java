package es.ulpgc.eii.android.project2.ui;

import android.view.View;
import android.widget.ImageView;

import es.ulpgc.eii.android.project2.R;
import es.ulpgc.eii.android.project2.modal.Game;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class DieView {
    private ImageView imageViewDie;

    public DieView(ImageView imageViewDie) {
        this.imageViewDie = imageViewDie;
    }

    public void setImage(Game game) {
        int lastThrowing = game.getLastThrowing();
        int image = 0;
        switch (lastThrowing) {
            case 1:
                image = R.drawable.face1;
                break;
            case 2:
                image = R.drawable.face2;
                break;
            case 3:
                image = R.drawable.face3;
                break;
            case 4:
                image = R.drawable.face4;
                break;
            case 5:
                image = R.drawable.face5;
                break;
            case 6:
                image = R.drawable.face6;
                break;
        }
        imageViewDie.setImageResource(image);
    }
}
