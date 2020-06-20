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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hide ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        //Khai báo BottomNavigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        // Xử lý khi được bấm
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem1 = menu.getItem(1 );
        menuItem1.setChecked(true);

        // Xử lý sự kiện click chọn
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent in1 = new Intent(VideosActivity.this, HomeActivity.class);
                        startActivity(in1);
                        Animatoo.animateSlideRight(VideosActivity.this);
                        break;
                    case R.id.navigation_videos:
                        break;
                    case R.id.navigation_post:
                        Intent in2 = new Intent(VideosActivity.this, PostActivity.class);
                        startActivity(in2);
                        Animatoo.animateSlideUp(VideosActivity.this);
                        break;
                    case R.id.navigation_notifications:
                        Intent in3 = new Intent(VideosActivity.this, NotificationsActivity.class);
                        startActivity(in3);
                        Animatoo.animateSlideLeft(VideosActivity.this);
                        break;
                    case R.id.navigation_profile:
                        Intent in4 = new Intent(VideosActivity.this, AccInfoActivity.class);
                        startActivity(in4);
                        Animatoo.animateSlideLeft(VideosActivity.this);
                        break;
                }
                return true;
            }

        });

        YouTubePlayerView youTubePlayerView0 = findViewById(R.id.youtube_player_view_1);
        getLifecycle().addObserver(youTubePlayerView0);
    }
}