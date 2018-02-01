package com.example.android.shuffleboardscorekeeper;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ceciliaHumlelu on 2018-01-30.
 */

public class MyDialogFragment extends DialogFragment {
    //View alertDialogFrame;
    View titleBackground;
    TextView winningTeam;
    Button yesButton;
    Button noButton;
    String mMessage;
    ImageView satisfiedSmile;

    DialogClickListener communicator;

    static MyDialogFragment newInstance(String messageFromActivity){
        MyDialogFragment f = new MyDialogFragment();
        Bundle args = new Bundle();
        args.putString("message",messageFromActivity);
        f.setArguments(args);
        return f;
    }

    interface DialogClickListener{
        public void yesButtonClicked();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessage = getArguments().getString("message");
        try{
            communicator = (DialogClickListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()+"must implement DialogClcikListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //define views in congratulations alert dialog
        View dialog = inflater.inflate(R.layout.congratulations_alert_dialog,container,false);
        titleBackground = (RelativeLayout) dialog.findViewById(R.id.title_background);
        satisfiedSmile = (ImageView) dialog.findViewById(R.id.satisfied_smile);
        winningTeam = (TextView) dialog.findViewById(R.id.winning_team);
        yesButton = (Button) dialog.findViewById(R.id.yes_button);
        noButton = (Button) dialog.findViewById(R.id.no_button);

        //setup view after receiving message from activity
        winningTeam.setText(mMessage);
        if (mMessage.equals("Home Team")) {
            int home_team_color = getResources().getColor(android.R.color.holo_red_light);
            if (Build.VERSION.SDK_INT >= 16) {
                dialog.setBackground(getResources().getDrawable(R.drawable.button_bg_transparent));
            }
            titleBackground.setBackgroundColor(home_team_color);
            yesButton.setTextColor(home_team_color);
            noButton.setTextColor(home_team_color);
        } else if (mMessage.equals("Guest Team")){
            int guest_team_color = getResources().getColor(android.R.color.holo_blue_light);
            if (Build.VERSION.SDK_INT >= 16) {
                dialog.setBackground(getResources().getDrawable(R.drawable.button_opponent_bg_transparent));
            }
            titleBackground.setBackgroundColor(guest_team_color);
            yesButton.setTextColor(guest_team_color);
            noButton.setTextColor(guest_team_color);

        }else {
            satisfiedSmile.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        }
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                communicator.yesButtonClicked();
                dismiss();

            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

  /*  public void updateDialog(String team){

    }*/




}
