package com.example.android.shuffleboardscorekeeper;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonViewLayout extends Fragment {
    private Button homeDecrement;
    private Button homeIncrement;
    private Button guestDecrement;
    private Button guestIncrement;
    private TextView homeTextView;
    private TextView guestTextView;
    private int homeNumber = 0;
    private int guestNumber = 0;

   ButtonViewLayoutListener activityManager;

    public interface ButtonViewLayoutListener{
        public void getScore(int home, int guest);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*try {
            activityManager = (ButtonViewLayoutListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement ButtonViewLayoutListener");
        }*/
    }

    public void setListener(ButtonViewLayoutListener listener) {
        activityManager = listener;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View buttonView = inflater.inflate(R.layout.activity_button_view_layout,container,false);

        homeDecrement = (Button) buttonView.findViewById(R.id.home_decrement);
        homeIncrement = (Button) buttonView.findViewById(R.id.home_increment);
        guestDecrement = (Button)buttonView.findViewById(R.id.guest_decrement);
        guestIncrement = (Button) buttonView.findViewById(R.id.guest_increment);
        homeTextView = (TextView)buttonView.findViewById(R.id.home_team_number);
        guestTextView = (TextView) buttonView.findViewById(R.id.guest_team_number);

        //check if main acitivity reset button is clicked
       /* buttonIsClciked = getArguments().getBoolean("reset_button_clicked");
        resetScore(buttonIsClciked);*/


        homeDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeNumber -= 1;
                if (homeNumber < 0){
                    homeNumber = 0;
                }
                homeTextView.setText(String.valueOf(homeNumber));
                activityManager.getScore(homeNumber,guestNumber);

            }
        });

        homeIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeNumber += 1;
                homeTextView.setText(String.valueOf(homeNumber));
                activityManager.getScore(homeNumber,guestNumber);
            }
        });

        guestDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guestNumber -= 1;
                if (guestNumber < 0){
                    guestNumber = 0;
                }
                guestTextView.setText(String.valueOf(guestNumber));
                activityManager.getScore(homeNumber,guestNumber);
            }
        });

        guestIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guestNumber += 1;
                guestTextView.setText(String.valueOf(guestNumber));
                activityManager.getScore(homeNumber,guestNumber);
            }
        });



        return buttonView;
    }

    public void updateFragmentScore(){
        homeNumber = 0;
        guestNumber = 0;
        homeTextView.setText(String.valueOf(homeNumber));
        guestTextView.setText(String.valueOf(guestNumber));
    }



}
