package com.example.eaten;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eaten.DTO.Card;
import com.example.eaten.myadapter.CardAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    EditText txtSearch;
    GridView gv;
    List<Card> cardList, listSearch;
    private static final String JSON_URL = "https://thym.azurewebsites.net/api/Posts/get-all-post-info";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hide ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //bottom navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem0 = menu.getItem(0 );
        menuItem0.setChecked(true);
//        MenuItem menuItem1 = menu.getItem(1 );
//        menuItem1.setChecked(true);
//        MenuItem menuItem2 = menu.getItem(2 );
//        menuItem2.setChecked(true);
//        MenuItem menuItem3 = menu.getItem(3);
//        menuItem3.setChecked(true);
//        MenuItem menuItem4 = menu.getItem(4 );
//        menuItem4.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent in1 = new Intent(SearchActivity.this, HomeActivity.class);
                        startActivity(in1);
                        break;
                    case R.id.navigation_videos:
                        Intent in2 = new Intent(SearchActivity.this, VideosActivity.class);
                        startActivity(in2);
                        break;
                    case R.id.navigation_post:
                        Intent in3 = new Intent(SearchActivity.this, PostActivity.class);
                        startActivity(in3);
                        break;
                    case R.id.navigation_notifications:
                        Intent in4 = new Intent(SearchActivity.this, NotificationsActivity.class);
                        startActivity(in4);
                        break;
                    case R.id.navigation_profile:
                        Intent in5 = new Intent(SearchActivity.this, AccInfoActivity.class);
                        startActivity(in5);
                        break;
                }
                return true;
            }

        });

        mapping();
        loadGV();

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //listSearch.clear();
                listSearch = new ArrayList<>();
                for(int i=0 ; i < cardList.size(); i++){
                    if(cardList.get(i).getPostName().contains(txtSearch.getText().toString())){
                        listSearch.add(cardList.get(i));
                    }
                }
                CardAdapter adapter = new CardAdapter(listSearch, getApplicationContext());
                gv.setAdapter(adapter);
            }
        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Card card_sub = (Card) cardList.get(position);
                Intent sub = new Intent(view.getContext(), HomeSubActivity.class);
                //sub.putExtra("accID", temp); //chuyển accountId sang HomeSubActivity
                sub.putExtra("card", listSearch.get(position).getPostId());
                startActivity(sub);
            }
        });
    }

    private void mapping(){
        txtSearch = (EditText) findViewById(R.id.txtSearch);
        gv = (GridView) findViewById(R.id.gridView0);
    }

    private void loadGV(){

        cardList = new ArrayList<>();
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray cardArray = jsonObject.getJSONArray("data");
                            //Log.e("abc","e e e e e");
                            for(int i = 0 ; i < cardArray.length(); i++){
                                JSONObject obj = cardArray.getJSONObject(i);
                                //Log.e("abc","e e e e e");
                                Card card = new Card(
                                        obj.getInt("postId"),
                                        obj.getInt("accountId"),
                                        obj.getString("postName"),
                                        obj.getString("content"),
                                        obj.getString("address"),
                                        obj.getString("displayName"),
                                        obj.getString("pictureURL"),
                                        //null,
                                        obj.getInt("reactQuantity")
////                                        obj.getInt("postId"),
////                                        obj.getInt("accountId"),
////                                        obj.getString("postName"),
////                                        "",
////                                        obj.getString("address"),
////                                        "",
////                                        null,
////                                        obj.getInt("reactQuantity")
                                );
                                //Log.e("abc","e e e e e");
                                cardList.add(card);
                            }
                            Collections.reverse(cardList);
                            //CardAdapter adapter = new CardAdapter(cardList, getApplicationContext());
                            //gv.setAdapter(adapter);
                            Log.e("abc","e e e e e");
                        }catch (JSONException e){
                            e.printStackTrace();
                            //Log.e("abc","e e e e e");
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("abc","e e e e e");
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}