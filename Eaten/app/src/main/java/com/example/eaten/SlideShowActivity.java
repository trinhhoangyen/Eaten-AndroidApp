package com.example.eaten;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SlideShowActivity extends AppCompatActivity {
    private static int TIME = 2000;
    Animation top, bottom, center, logo;
    ImageView topimg, bottomimg, centerimg, logoimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hide ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slide_show);

        //Animation
        top = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        center = AnimationUtils.loadAnimation(this, R.anim.center_animation);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        //logo = AnimationUtils.loadAnimation(this,R.anim.logo_animation);

        //mapping
        topimg = findViewById(R.id.imageView_top);
        centerimg = findViewById(R.id.imageView_center);
        bottomimg = findViewById(R.id.imageView_bottom);
        //logoimg = findViewById(R.id.imageView_logo);

        topimg.setAnimation(top);
        centerimg.setAnimation(center);
        bottomimg.setAnimation(bottom);
        //logoimg.setAnimation(logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentHome = new Intent(SlideShowActivity.this, HomeActivity.class);
                //startActivity(intentHome);
                //finish();
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View, String>(topimg, "img_logo");
                pairs[1] = new Pair<View, String>(centerimg, "img_logo");
                pairs[2] = new Pair<View, String>(bottomimg, "img_logo");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SlideShowActivity.this,pairs);
                    startActivity(intentHome, options.toBundle());
                    finish();
                }
            }
        }, TIME);
    }
}
