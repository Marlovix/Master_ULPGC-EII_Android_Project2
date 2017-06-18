package es.ulpgc.eii.android.project2.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import es.ulpgc.eii.android.project2.GameActivity;
import es.ulpgc.eii.android.project2.R;
import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.ui.GameObject;

public class DieFragment extends Fragment implements GameObject {

    static final int FRAMES = 100;

    private ImageView imageViewDie;
    private Game game;
    private CountDownTimer timer;
    private boolean inAnimation = false;

    public DieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_die, container, false);

        imageViewDie = (ImageView) view.findViewById(R.id.imageView_die);

        return view;
    }

    @Override
    public void finishGame(Game game) {
        int lastThrowing = game.getLastThrowing();
        setImage(lastThrowing);
        imageViewDie.setVisibility(View.INVISIBLE);
    }

    @Override
    public void gamePlay(Game game) {
        int lastThrowing = game.getLastThrowing();
        setImage(lastThrowing);
        imageViewDie.setVisibility(View.VISIBLE);
    }

    @Override
    public void lostTurnByOne(Game game) {
        int lastThrowing = game.getLastThrowing();
        setImage(lastThrowing);
        imageViewDie.setVisibility(View.VISIBLE);
    }

    @Override
    public void readyToPlay(Game game) {
        imageViewDie.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startGame(Game game) {
        imageViewDie.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startTurn(Game game) {
        imageViewDie.setVisibility(View.INVISIBLE);
    }

    @Override
    public void throwingDie(Game game) {
        this.game = game;
        long timeAnimation = game.getTimeAnimation();
        imageViewDie.setVisibility(View.VISIBLE);
        throwingAnimation(timeAnimation);
    }

    @Override
    public void showingScore(Game game) {
        this.game = game;
        long timeAnimation = game.getTimeAnimation();
        int machineThrowingValue = game.getMachineThrowingValue();
        imageViewDie.setVisibility(View.VISIBLE);
        setImage(machineThrowingValue);
        showingScoreAnimation(timeAnimation);
    }

    private void throwingAnimation(long timeAnimation) {
        timer = new CountDownTimer(timeAnimation, FRAMES) {
            @Override
            public void onTick(long millisUntilFinished) {
                inAnimation = true;
                game.setTimeAnimation(millisUntilFinished);
                int dieFace = game.throwDie();
                setImage(dieFace);
            }

            @Override
            public void onFinish() {
                inAnimation = false;
                ((GameActivity) getActivity()).resetTimeAnimation();
                ((GameActivity) getActivity()).checkThrowingValue();

            }
        }.start();
    }

    private void showingScoreAnimation(long timeAnimation) {
        timer = new CountDownTimer(timeAnimation, FRAMES) {
            @Override
            public void onTick(long millisUntilFinished) {
                inAnimation = true;
                game.setTimeAnimation(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                inAnimation = false;
                ((GameActivity) getActivity()).resetTimeAnimation();
                if (game.getMachineThrowing() == 0) {
                    ((GameActivity) getActivity()).collectAccumulated();
                } else {
                    game.setStateThrowing();
                    ((GameActivity) getActivity()).updateState();
                }
            }
        }.start();
    }

    public void cancelAnimation() {
        if (inAnimation) {
            timer.cancel();
        }
    }

    private void setImage(int faceDie) {
        int image;
        switch (faceDie) {
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
            default:
                return;
        }
        imageViewDie.setImageResource(image);
    }

}