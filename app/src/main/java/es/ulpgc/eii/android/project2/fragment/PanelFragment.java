package es.ulpgc.eii.android.project2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import es.ulpgc.eii.android.project2.R;
import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.Player;
import es.ulpgc.eii.android.project2.ui.GameObject;

public class PanelFragment extends Fragment implements GameObject {

    private TextView textViewPlayerTurn;
    private TextView textViewAccumulated;
    private TextView textViewStartTurn;
    private Button buttonThrow;
    private Button buttonCollect;

    private OnPanelFragmentInteraction interaction;

    public PanelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interaction = (OnPanelFragmentInteraction) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    // Cycle 3 //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_panel, container, false);

        textViewAccumulated = (TextView) view.findViewById(R.id.textView_accumulated);
        textViewPlayerTurn = (TextView) view.findViewById(R.id.textView_player_turn);
        textViewStartTurn = (TextView) view.findViewById(R.id.textView_start_turn);

        buttonCollect = (Button) view.findViewById(R.id.button_collect);
        buttonThrow = (Button) view.findViewById(R.id.button_throw);

        /* Listeners */
        textViewStartTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interaction != null) interaction.startTurnToPlay();
            }
        });

        // Listeners of interaction //
        buttonThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interaction != null) interaction.throwDie();
            }
        });

        buttonCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interaction != null) interaction.collectAccumulated();
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        interaction = null;
        super.onDetach();
    }

    public interface OnPanelFragmentInteraction {
        void startTurnToPlay();

        void throwDie();

        void collectAccumulated();
    }

    @Override
    public void finishGame(Game game) {
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.INVISIBLE);
        textViewStartTurn.setVisibility(View.VISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
        buttonCollect.setVisibility(View.INVISIBLE);
    }

    @Override
    public void gamePlay(Game game) {
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.VISIBLE);
        textViewStartTurn.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.VISIBLE);
        buttonCollect.setVisibility(View.VISIBLE);
        buttonCollect.setEnabled(true);
        buttonThrow.setEnabled(true);
    }

    @Override
    public void lostTurnByOne(Game game) {
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.VISIBLE);
        textViewStartTurn.setVisibility(View.VISIBLE);
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
    }

    @Override
    public void readyToPlay(Game game) {
        int buttonThrowColor = ContextCompat.getColor(getContext(), R.color.label_button);
        String buttonThrowLabel = getResources().getString(R.string.label_throw);
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.VISIBLE);
        textViewStartTurn.setVisibility(View.INVISIBLE);
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.VISIBLE);
        buttonThrow.setEnabled(true);
        buttonThrow.setText(buttonThrowLabel);
        buttonThrow.setTextColor(buttonThrowColor);
    }

    @Override
    public void startGame(Game game) {
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.VISIBLE);
        textViewStartTurn.setVisibility(View.VISIBLE);
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startTurn(Game game) {
        setInfo(game);
        textViewPlayerTurn.setVisibility(View.INVISIBLE);
        textViewStartTurn.setVisibility(View.VISIBLE);
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
    }

    @Override
    public void throwingDie(Game game) {
        setInfo(game);

        Player player = game.getTurnPlayer();
        String buttonThrowLabel;
        int buttonThrowColor;
        if (player.isHuman()) {
            buttonThrowLabel = getResources().getString(R.string.label_throw);
            buttonThrowColor = ContextCompat.getColor(getContext(), R.color.label_button);
        } else {
            buttonThrowLabel = getResources().getString(R.string.label_throwing);
            buttonThrowColor = ContextCompat.getColor(getContext(), R.color.machine);
        }

        buttonThrow.setText(buttonThrowLabel);
        buttonThrow.setTextColor(buttonThrowColor);
        buttonThrow.setVisibility(View.VISIBLE);
        buttonThrow.setEnabled(false);
        buttonCollect.setEnabled(false);
        buttonCollect.setVisibility(View.VISIBLE);
        buttonCollect.setTextColor(buttonThrowColor);
        textViewStartTurn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showingScore(Game game) {
        setInfo(game);

        int buttonThrowColor;
        String buttonThrowLabel;

        buttonThrowLabel = getResources().getString(R.string.label_throw);
        buttonThrowColor = ContextCompat.getColor(getContext(), R.color.machine);

        buttonCollect.setEnabled(false);
        buttonThrow.setEnabled(false);
        buttonThrow.setText(buttonThrowLabel);
        buttonThrow.setTextColor(buttonThrowColor);
        buttonCollect.setTextColor(buttonThrowColor);
        textViewStartTurn.setVisibility(View.INVISIBLE);
    }

    // Set the player information on the screen //
    private void setInfo(Game game) {
        Context context = textViewPlayerTurn.getContext();
        Player playerToStart = game.getTurnPlayer();
        String newPlayer = playerToStart.getName();
        String textNewTurn = String.format(context.getString(R.string.label_start_turn), newPlayer);
        int accumulatedScore = game.getAccumulatedScore();
        int colorText = playerToStart.getColor();

        updateAccumulatedView(accumulatedScore);
        textViewPlayerTurn.setText(newPlayer);
        textViewStartTurn.setText(textNewTurn);
        textViewStartTurn.setTextColor(colorText);
    }

    private void updateAccumulatedView(int accumulatedScore) {
        Context context = textViewAccumulated.getContext();
        String textAccumulated =
                String.format(context.getString(R.string.label_accumulated), accumulatedScore);
        textViewAccumulated.setText(textAccumulated);
    }
}
