package com.example.android.shuffleboardscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonViewLayout extends AppCompatActivity {
    TextView homeTeamNumber = (TextView) findViewById(R.id.home_team_number);
    TextView guestTeamNumber = (TextView) findViewById(R.id.guest_team_number);
    private int homeNumber = 0;
    private int guestNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_view_layout);

    }
    public void decrementForHome(View view){
        if (homeNumber <= 0) {
            Toast.makeText(this,"Number can't be lower than 0", Toast.LENGTH_SHORT);
        }

        homeNumber--;
        homeTeamNumber.setText(homeNumber);
    }

    public void incrementForHome(View view){
        homeNumber++;
        homeTeamNumber.setText(homeNumber);
    }
}
