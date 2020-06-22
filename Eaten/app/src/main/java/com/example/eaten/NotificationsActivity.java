package com.example.eaten;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hide ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();
//        MenuItem menuItem0 = menu.getItem(0 );
//        menuItem0.setChecked(true);
//        MenuItem menuItem1 = menu.getItem(1 );
//        menuItem1.setChecked(true);
//        MenuItem menuItem2 = menu.getItem(2 );
//        menuItem2.setChecked(true);
        MenuItem menuItem3 = menu.getItem(3);
        menuItem3.setChecked(true);
//        MenuItem menuItem4 = menu.getItem(4 );
//        menuItem4.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent in1 = new Intent(NotificationsActivity.this, HomeActivity.class);
                        startActivity(in1);
                        Animatoo.animateSlideRight(NotificationsActivity.this);
                        break;
                    case R.id.navigation_videos:
                        Intent in2 = new Intent(NotificationsActivity.this, VideosActivity.class);
                        startActivity(in2);
                        Animatoo.animateSlideRight(NotificationsActivity.this);
                        break;
                    case R.id.navigation_post:
                        Intent in3 = new Intent(NotificationsActivity.this, PostActivity.class);
                        startActivity(in3);
                        Animatoo.animateSlideUp(NotificationsActivity.this);
                        break;
                    case R.id.navigation_notifications:
                        break;
                    case R.id.navigation_profile:
                        Intent in4 = new Intent(NotificationsActivity.this, AccInfoActivity.class);
                        startActivity(in4);
                        Animatoo.animateSlideLeft(NotificationsActivity.this);
                        break;
                }
                return true;
            }

        });
    }
}