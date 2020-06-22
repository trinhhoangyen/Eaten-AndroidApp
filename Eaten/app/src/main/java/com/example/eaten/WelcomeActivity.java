package com.example.eaten;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eaten.myadapter.PagerAdapter;

public class WelcomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout layoutDot;
    private TextView[] dotstv;
    private int[] layouts;
    private Button btnNext, btnSkip;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hide ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if(!isFirstTime()){
            startMainActivity();
            finish();
        }



        setStatusBarTransparent();
        setContentView(R.layout.activity_welcome);

        viewPager = findViewById(R.id.view_pager);
        layoutDot = findViewById(R.id.dotlayout);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPage = viewPager.getCurrentItem()+1;
                if(currentPage < layouts.length){
                    viewPager.setCurrentItem(currentPage);
                }else {
                    startMainActivity();
                }
            }
        });

        //layouts = new int[]{R.layout.slider_1, R.layout.slider_2};
        layouts = new int[]{R.layout.slider_1, R.layout.slider_2, R.layout.slider_3, R.layout.slider_4};
        pagerAdapter = new PagerAdapter(layouts, getApplicationContext());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == layouts.length-1){
                    btnNext.setText(R.string.text_start);
                    btnSkip.setVisibility(View.GONE);
                }else {
                    btnNext.setText(R.string.text_next);
                    btnSkip.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setDotStatus(0);
    }

    private boolean isFirstTime(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("SlideTime", Context.MODE_PRIVATE);
        return preferences.getBoolean("firstTime", true);
    }
    private void setFirstTime(Boolean stt){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("SlideTime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstTime", stt);
        editor.commit();
    }

    private void setDotStatus(int page){
        layoutDot.removeAllViews();
        dotstv = new TextView[layouts.length];
        for (int i=0; i < dotstv.length; i++){
            dotstv[i] = new TextView(this);
            dotstv[i].setText(Html.fromHtml("&#8226;"));
            dotstv[i].setTextSize(30);
            dotstv[i].setTextColor(Color.parseColor("#a9b4bb"));
            layoutDot.addView(dotstv[i]);
        }
        if (dotstv.length>0){
            dotstv[page].setTextColor(Color.parseColor("#ffffff"));
        }
    }
    private void startMainActivity(){
        setFirstTime(false);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }
    private void setStatusBarTransparent(){
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
