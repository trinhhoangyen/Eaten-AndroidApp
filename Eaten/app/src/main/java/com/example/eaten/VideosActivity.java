package com.example.eaten;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideosActivity extends AppCompatActivity {
    WebView webView;
    SwipeRefreshLayout swipeRefreshLayout;
    String url = "https://www.youtube.com/channel/UCeB29zGi9E5SdtTsUoEnEAg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hide ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        //Load Web Ytb
        webView = findViewById(R.id.wv);
        swipeRefreshLayout = findViewById(R.id.refreshVideos);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRestart();
                webView.loadUrl(url);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2*1000);
            }
        });

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
                        finish();
                        break;
                    case R.id.navigation_videos:
                        break;
                    case R.id.navigation_post:
                        Intent in2 = new Intent(VideosActivity.this, PostActivity.class);
                        startActivity(in2);
                        Animatoo.animateSlideUp(VideosActivity.this);
                        finish();
                        break;
                    case R.id.navigation_notifications:
                        Intent in3 = new Intent(VideosActivity.this, NotificationsActivity.class);
                        startActivity(in3);
                        Animatoo.animateSlideLeft(VideosActivity.this);
                        finish();
                        break;
                    case R.id.navigation_profile:
                        Intent in4 = new Intent(VideosActivity.this, AccInfoActivity.class);
                        startActivity(in4);
                        Animatoo.animateSlideLeft(VideosActivity.this);
                        finish();
                        break;
                }
                return true;
            }

        });

    }
}