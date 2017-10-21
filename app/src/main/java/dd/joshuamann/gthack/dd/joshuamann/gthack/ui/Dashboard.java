package com.example.android.buttonfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {


    private ArrayList<Fragment> fragments = new ArrayList<>(3);
    private String condition;

    BottomBarFragment location = new BottomBarFragment();
    BottomBarFragment info = new BottomBarFragment();
    YoutubeFragment videos = new YoutubeFragment();


    private void buildFragmentsList() {
        fragments.add(info);
        fragments.add(location);
        videos.setCondition(condition);
        fragments.add(videos);
    }

    private void switchFragment(int pos, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, fragments.get(pos), tag)
                .commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_location:
                    startActivity(new Intent(Dashboard.this, MapsActivity.class));
                    return true;
                case R.id.navigation_info:
                    switchFragment(0, "TAG");
                    return true;
                case R.id.navigation_videos:
                    switchFragment(2, "TAG");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Intent intent = getIntent();
        condition = intent.getStringExtra(MainActivity.EXTRA_DISTRESS);


        buildFragmentsList();
        switchFragment(0, "TAG");



    }

}
