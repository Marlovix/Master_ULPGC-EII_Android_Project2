package es.ulpgc.eii.android.project2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import es.ulpgc.eii.android.project2.R;
import es.ulpgc.eii.android.project2.listener.ChooseTypeOfGameListener;

public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button onePlayerButton = (Button) view.findViewById(R.id.button_one_player);
        Button twoPlayersButton = (Button) view.findViewById(R.id.button_two_players);
        initButtons(onePlayerButton, 1);
        initButtons(twoPlayersButton, 2);
        return view;
    }

    private void initButtons(Button button, int numberOfPlayers){
        String[] labels = getResources().getStringArray(R.array.number_players);
        String label = labels[numberOfPlayers-1];
        button.setText(label);
        button.setOnClickListener(new ChooseTypeOfGameListener(numberOfPlayers));
    }

}
