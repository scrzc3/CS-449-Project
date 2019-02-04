package com.example.umpirebuddy;
/*Created by Sam Rovenstine Spring 2019*/

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private int mOutsCount, mBallsCount, mStrikesCount;
    private static String Out_Index = "out_index";
    private static String Ball_Index = "ball_index";
    private static String Strike_Index = "strike_index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button mBallsButton;
        Button mStrikesButton;

        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            mStrikesCount = savedInstanceState.getInt(Strike_Index);
            mBallsCount = savedInstanceState.getInt(Ball_Index);
            mOutsCount = savedInstanceState.getInt(Out_Index);

            TextView Strikeid = findViewById(R.id.strikesCount);
            TextView Ballid = findViewById(R.id.ballsCount);
            TextView Outid = findViewById(R.id.outsCount);

            Strikeid.setText(String.valueOf(mStrikesCount));
            Ballid.setText(String.valueOf(mBallsCount));
            Outid.setText(String.valueOf(mOutsCount));

        }
        mBallsButton = findViewById(R.id.ballButton);
        mBallsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BallCount(0);
                if (mBallsCount >= 4) {
                    WalkDialog().show();
                    StrikeCount(1);
                    BallCount(1);
                }
            }
        });
        mStrikesButton = findViewById(R.id.strike_button);
        mStrikesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrikeCount(0);
                if (mStrikesCount >= 3) {
                    OutDialog().show();
                    StrikeCount(1);
                    BallCount(1);
                    OutCount(0);
                }
            }
        });

    }




    /*Dialog boxes that appear after each Strike/Ball limit is reached before resetting to new hitter*/
    public AlertDialog OutDialog() {
        AlertDialog.Builder OutDialog = new AlertDialog.Builder(MainActivity.this);
        OutDialog.setMessage("Out");
        OutDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicks OK
            }
        });
        return OutDialog.create();
    }

    public AlertDialog WalkDialog() {
        AlertDialog.Builder WalkDialog = new AlertDialog.Builder(MainActivity.this);
        WalkDialog.setMessage("Walk");
        WalkDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicks OK
            }
        });
        return WalkDialog.create();
    }




    /*Equations/Parameters for when a player is out and/or gets a walk*/
    public void StrikeCount(int val) {
        if (val == 0)
        {
            mStrikesCount++;
        }
        else{
            mStrikesCount = 0;
        }
        TextView Ballid = findViewById(R.id.strikesCount);
        Ballid.setText(String.valueOf(mStrikesCount));
    }

    public void OutCount(int val) {
        if (val == 0) {
            mOutsCount++;
        }

        if (mOutsCount == 3){
            mOutsCount = 0;
        }
        TextView OutsId = findViewById(R.id.outsCount);
        OutsId.setText(String.valueOf(mOutsCount));
    }

    public void BallCount(int val) {
        if (val == 0)
        {
            mBallsCount++;
        }
        else{
            mBallsCount = 0;
        }
        TextView Strikeid = findViewById(R.id.ballsCount);
        Strikeid.setText(String.valueOf(mBallsCount));
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(Ball_Index, mBallsCount);
        savedInstanceState.putInt(Strike_Index, mStrikesCount);
        savedInstanceState.putInt(Out_Index, mOutsCount);
    }
}
