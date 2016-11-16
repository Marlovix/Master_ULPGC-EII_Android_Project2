package es.ulpgc.eii.android.project2.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import es.ulpgc.eii.android.project2.GameActivity;
import es.ulpgc.eii.android.project2.R;
import es.ulpgc.eii.android.project2.listener.CollectListener;
import es.ulpgc.eii.android.project2.listener.StartTurnListener;
import es.ulpgc.eii.android.project2.listener.ThrowListener;
import es.ulpgc.eii.android.project2.modal.Game;
import es.ulpgc.eii.android.project2.modal.Player;
import es.ulpgc.eii.android.project2.ui.GameInfo;
import es.ulpgc.eii.android.project2.ui.GameObject;

public class PanelFragment extends Fragment implements GameObject{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Game game;
    private GameInfo gameInfo;
    private TextView textViewPlayerTurn;
    private TextView textViewAccumulated;
    private TextView textViewStartTurn;
    private Button buttonThrow;
    private Button buttonCollect;

    private OnPanelFragmentInteraction interaction;

    public PanelFragment() {
        // Required empty public constructor
    }

    // Cycle 3 //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /*int[] fragmentLayouts = new int[2];
        fragmentLayouts[0] = R.layout.fragment_panel;
        fragmentLayouts[1] = R.layout.fragment_panel;

        int numberOfPlayers = getActivity().getIntent().getExtras().
                getInt(ChooseTypeOfGameListener.NUMBER_OF_PLAYERS);*/

        // Inflate the layout for this fragment depending on the number of players //
        View view = inflater.inflate(R.layout.fragment_panel, container, false);


        TextView textViewAccumulated = (TextView) view.findViewById(R.id.textView_accumulated_score);
        TextView textViewPlayerToPlay = (TextView) view.findViewById(R.id.textView_player_turn);
        TextView textViewStartTurn = (TextView) view.findViewById(R.id.textView_start_turn);

        Button buttonCollect = (Button) view.findViewById(R.id.button_collect);
        Button buttonThrow = (Button) view.findViewById(R.id.button_throw);

        gameInfo = new GameInfo(textViewAccumulated, textViewPlayerToPlay, textViewStartTurn);

        /* Listeners */
        textViewStartTurn.setOnClickListener(new StartTurnListener(game, this));

        // Listeners of interaction //
        buttonThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interaction != null)
                    interaction.throwDie();
            }
        });

        buttonCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interaction != null)
                    interaction.collectAccumulated();
            }
        });

        return view;
    }

    public TextView getTextViewPlayerTurn() {
        return textViewPlayerTurn;
    }

    public TextView getTextViewAccumulated() {
        return textViewAccumulated;
    }

    public TextView getTextViewStartTurn() {
        return textViewStartTurn;
    }

    public Button getButtonThrow() {
        return buttonThrow;
    }

    public Button getButtonCollect() {
        return buttonCollect;
    }

    public interface OnPanelFragmentInteraction {
        void throwDie();
        void collectAccumulated();
    }

    @Override
    public void startGame(Game game) {
        setInfo(game);
        textViewStartTurn.setVisibility(View.VISIBLE);
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
    }

    @Override
    public void readyToPlay(Game game) {
        setInfo(game);
        textViewStartTurn.setVisibility(View.INVISIBLE);
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.VISIBLE);
    }

    @Override
    public void gamePlay(Game game) {
        setInfo(game);
        textViewStartTurn.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.VISIBLE);
        buttonCollect.setVisibility(View.VISIBLE);
    }

    @Override
    public void lostTurnByOne(Game game) {
        setInfo(game);
        textViewStartTurn.setVisibility(View.VISIBLE);
        buttonCollect.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.INVISIBLE);
    }

    @Override
    public void finishGame(Game game) {
        setInfo(game);
        textViewStartTurn.setVisibility(View.INVISIBLE);
        buttonThrow.setVisibility(View.VISIBLE);
        buttonCollect.setVisibility(View.VISIBLE);
    }

    private void setInfo(Game game) {
        Player playerToStart = game.getTurnPlayer();
        gameInfo.setPlayerInfo(playerToStart);
    }
}
