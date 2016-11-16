package es.ulpgc.eii.android.project2.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import es.ulpgc.eii.android.project2.R;
import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.ui.DieView;
import es.ulpgc.eii.android.project2.ui.GameObject;

public class DieFragment extends Fragment implements GameObject{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

            /*WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
            display.getSize(size);
        imageViewDie.getLayoutParams().width = size.x*3 / 5;
        imageViewDie.getLayoutParams().height = size.y/3 ;*/

    private ImageView imageViewDie;
    private DieView dieView;
    private Drawable[] facesDie;

    public DieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_die, container, false);

        imageViewDie = (ImageView)view.findViewById(R.id.imageView_die);

        dieView = new DieView(imageViewDie);

        // Getting an array with the images of the die //
        TypedArray facesIDs = getResources().obtainTypedArray(R.array.die_faces);
        facesDie = new Drawable[facesIDs.length()];
        for (int i=0; i<facesIDs.length(); i++)
            facesDie[i] = ContextCompat.getDrawable(getContext(), facesIDs.getResourceId(i, -1));
        facesIDs.recycle();

        return view;
    }

    public void setBackground(Drawable drawable){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
            imageViewDie.setBackgroundDrawable(drawable);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            imageViewDie.setBackground(drawable);
    }

    public void setBackground(AnimationDrawable animation){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN)
            imageViewDie.setBackgroundDrawable(animation);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            imageViewDie.setBackground(animation);
    }

    public ImageView getImageViewDie() {
        return imageViewDie;
    }

    public Drawable[] getFacesDie() {
        return facesDie;
    }

    @Override
    public void startGame(Game game) {
        imageViewDie.setVisibility(View.INVISIBLE);
    }

    @Override
    public void readyToPlay(Game game) {
        imageViewDie.setVisibility(View.INVISIBLE);
    }

    @Override
    public void gamePlay(Game game) {
        dieView.setImage(game);
        imageViewDie.setVisibility(View.VISIBLE);
    }

    @Override
    public void lostTurnByOne(Game game) {
        dieView.setImage(game);
        imageViewDie.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishGame(Game game) {
        dieView.setImage(game);
        imageViewDie.setVisibility(View.VISIBLE);
    }

}
