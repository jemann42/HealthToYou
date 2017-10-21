package com.example.android.buttonfinal;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final ArrayList<Button> firstBlade = new ArrayList<>();
    boolean forOthers = false;
    public static final String EXTRA_DISTRESS ="";
    ;
    boolean toggleSeen = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //Add Buttons to List
        firstBlade.add((Button) findViewById(R.id.Heart));
        firstBlade.add((Button) findViewById(R.id.Allergies));
        firstBlade.add((Button) findViewById(R.id.Fainting));
        firstBlade.add((Button) findViewById(R.id.Poisoning));
        firstBlade.add((Button) findViewById(R.id.Trauma));
        firstBlade.add((Button) findViewById(R.id.Cuts));


        //Intialize Visibility
        hideOptions();


        final Button SOSButton = (Button) findViewById(R.id.SOSButton);
        SOSButton.setVisibility(View.VISIBLE);
        SOSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!toggleSeen) {
                    showOptions();
                } else {
                    hideOptions();
                }
                toggleSeen = !toggleSeen;

            }
        });

        SOSButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //Send SOS data to the database, move to next
                moveOn("SOS");
                return true;
            }
        });




        final Button OthersButton = (Button) findViewById(R.id.Others);
        OthersButton.setVisibility(View.VISIBLE);
        OthersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forOthers = true;
            }


        });








    }

    public void moveOn(String distress) {

        Intent intent = new Intent(this, Dashboard.class);
        intent.putExtra(EXTRA_DISTRESS, distress);
        startActivity(intent);

    }


    private void showOptions() {
        for (int i = 0; i < firstBlade.size(); i++) {
            firstBlade.get(i).setVisibility(View.VISIBLE);
        }
    }

    private void hideOptions() {
        for (int i = 0; i < firstBlade.size(); i++) {
            firstBlade.get(i).setVisibility(View.INVISIBLE);
        }
    }

    public void clicked(View v) {
        String name = ((Button) v).getText().toString();
        moveOn(name);
        //change to next activity and send data


    }
}