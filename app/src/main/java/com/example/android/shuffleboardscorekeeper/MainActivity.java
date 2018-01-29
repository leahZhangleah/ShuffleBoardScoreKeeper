package com.example.android.shuffleboardscorekeeper;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Presentation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //define four fragments
    private ButtonViewLayout takeDownFragment;
    private ButtonViewLayout escapeFragment;
    private ButtonViewLayout reversalFragment;
    private ButtonViewLayout nearfallFragment;
    private ButtonViewLayout[] fragmentArray = new ButtonViewLayout[4];

    //reset button
    private Button resetButton;

    //set button to be clicked only 3 times maximumly
    private int maxClick = 3;
    private int currentClickNumber = 1;

    // total score for home and guest team
    private int homeTotalScore = 0;
    private int guestTotalScore = 0;

    // score for differnt scoring types
    private int tdHomeScore = 0;
    private int tdGuestScore = 0;

    private int esHomeScore = 0;
    private int esGuestScore = 0;

    private int rvHomeScore = 0;
    private int rvGuestScore = 0;

    private int nfHomeScore = 0;
    private int nfGuestScore = 0;

    //textviews to show total scores
    private TextView homeTsView;
    private TextView guestTsView;

    //score table textviews
    private TextView homeScoreR1;
    private TextView homeScoreR2;
    private TextView homeScoreR3;
    private TextView guestScoreR1;
    private TextView guestScoreR2;
    private TextView guestScoreR3;

    boolean buttonIsClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define two text views of home and guest scores
        homeTsView = (TextView) findViewById(R.id.home_total_score);
        guestTsView = (TextView) findViewById(R.id.guest_total_score);

        //add 4 fragments to main_activity
        takeDownFragment = (ButtonViewLayout) getFragmentManager().findFragmentById(R.id.takedown_fragment);
        escapeFragment = (ButtonViewLayout) getFragmentManager().findFragmentById(R.id.escape_fragment);
        reversalFragment = (ButtonViewLayout) getFragmentManager().findFragmentById(R.id.reversal_fragment);
        nearfallFragment = (ButtonViewLayout) getFragmentManager().findFragmentById(R.id.nearfall_fragment);

        fragmentArray[0] = takeDownFragment;
        fragmentArray[1] = escapeFragment;
        fragmentArray[2] = reversalFragment;
        fragmentArray[3] = nearfallFragment;

                //add 1 reset button
        resetButton = (Button) findViewById(R.id.reset_button);

        //define score table views
        homeScoreR1 = (TextView) findViewById(R.id.home_score_R1);
        guestScoreR1 = (TextView) findViewById(R.id.guest_score_R1);
        homeScoreR2 = (TextView) findViewById(R.id.home_score_R2);
        guestScoreR2 = (TextView) findViewById(R.id.guest_score_R2);
        homeScoreR3 = (TextView) findViewById(R.id.home_score_R3);
        guestScoreR3 = (TextView) findViewById(R.id.guest_score_R3);

        //setListener for each fragment to catch score change
        takeDownFragment.setListener( new ButtonViewLayout.ButtonViewLayoutListener() {
            @Override
            public void getScore(int home, int guest) {
                tdHomeScore = home * 2;
                tdGuestScore = guest * 2;
                updateScoreView();
            }
        });

        escapeFragment.setListener(new ButtonViewLayout.ButtonViewLayoutListener() {
            @Override
            public void getScore(int home, int guest) {
                esHomeScore = home;
                esGuestScore = guest;
                updateScoreView();
            }
        });

        reversalFragment.setListener(new ButtonViewLayout.ButtonViewLayoutListener() {
            @Override
            public void getScore(int home, int guest) {
                rvHomeScore = home * 2;
                rvGuestScore = guest * 2;
                updateScoreView();
            }
        });

        nearfallFragment.setListener(new ButtonViewLayout.ButtonViewLayoutListener() {
            @Override
            public void getScore(int home, int guest) {
                nfHomeScore = home * 2;
                nfGuestScore = guest * 2;
                updateScoreView();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIsClicked = true;
                if (currentClickNumber <= maxClick) {
                    switch (currentClickNumber) {
                        case 1:
                            updateScoreTable(homeScoreR1, guestScoreR1);
                            updateTotalScore();
                            updateFragmentNumber(fragmentArray,buttonIsClicked);
                            buttonIsClicked = false;
                            currentClickNumber += 1;
                            break;
                        case 2:
                            updateScoreTable(homeScoreR2, guestScoreR2);
                            updateTotalScore();
                            updateFragmentNumber(fragmentArray,buttonIsClicked);
                            buttonIsClicked = false;
                            currentClickNumber += 1;
                            break;
                        case 3:
                            updateScoreTable(homeScoreR3, guestScoreR3);
                            updateTotalScore();
                            updateFragmentNumber(fragmentArray,buttonIsClicked);
                            buttonIsClicked = false;
                            currentClickNumber += 1;
                            break;
                    }
                } else {
                    resetButton.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Game over", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //helper methods
    private void updateScoreView(){
        homeTotalScore =tdHomeScore + esHomeScore + rvHomeScore + nfHomeScore;
        guestTotalScore = tdGuestScore + esGuestScore + rvGuestScore + nfGuestScore;
        homeTsView.setText(String.valueOf(homeTotalScore));
        guestTsView.setText(String.valueOf(guestTotalScore));
    }

    private void updateScoreTable(TextView home, TextView guest){
        home.setText(String.valueOf(homeTotalScore));
        guest.setText(String.valueOf(guestTotalScore));
    }

    private void updateTotalScore(){
        homeTotalScore = 0;
        guestTotalScore = 0;
        homeTsView.setText(String.valueOf(homeTotalScore));
        guestTsView.setText(String.valueOf(guestTotalScore));
    }

    //todo
   private void updateFragmentNumber(ButtonViewLayout[] fragmentArray, boolean buttonIsClicked){
        if (buttonIsClicked) {
            for (ButtonViewLayout fragment : fragmentArray) {
                fragment.updateFragmentScore();
            }
        }
    }






}
