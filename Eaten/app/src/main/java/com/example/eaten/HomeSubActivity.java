package com.example.eaten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.example.eaten.DTO.HomeSubCmt;
import com.example.eaten.myadapter.HomeSubCmtAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeSubActivity extends AppCompatActivity {
    ImageView img_picture_sub;
    TextView txt_title_sub, txt_loves_sub, txt_content_sub, txt_address_sub, txt_displayName_sub;
    ListView lv_List_cmt;
    List<HomeSubCmt> homeSubCmtList;

    int temp;
    private static final String JSON_URL = "https://eatenapi.azurewebsites.net/api/Posts/get-all-post-info";
    private static final String JSON_URLCMT = "https://eatenapi.azurewebsites.net/api/Comments/get-all-comment-info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sub);
        //

        mapping();

        Intent sub = getIntent();
        temp = (int) sub.getIntExtra("card", -1);
        load();
        loadCmt();
    }

    private void load(){
        //cardList = new ArrayList<>();
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
                            for(int i = 0 ; i < cardArray.length(); i++) {
                                if (temp != -1 && i == temp) {
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
                                    );
                                    //Log.e("abc","e e e e e");
                                    //cardList.add(card);
                                    //final Bitmap myImage = Picasso.with(Home_sub.this).load(card.getPicture()).get();
                                    //int width = myImage.getWidth();
                                    //int height = myImage.getHeight();
                                    Picasso.with(HomeSubActivity.this)
                                            .load(card.getPicture())
                                            //.resize(Home_sub.this.getWindow().getDecorView().getWidth(), 300)
                                            //.centerInside()
                                            .fit()
                                            .centerCrop()
                                            .into(img_picture_sub);
                                    txt_title_sub.setText(card.getPostName());
                                    txt_address_sub.setText(card.getAddress());
                                    txt_displayName_sub.setText(card.getDisplayName());
                                    txt_loves_sub.setText(String.valueOf(card.getReactQuantity()));
                                    txt_content_sub.setText(card.getContent());
                                }
                            }
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

    private void loadCmt(){
        homeSubCmtList = new ArrayList<>();
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URLCMT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray homeSubCmtArray = jsonObject.getJSONArray("data");
                            //Log.e("abc","e e e e e");
                            for(int i = 0 ; i < homeSubCmtArray.length(); i++){
                                JSONObject obj = homeSubCmtArray.getJSONObject(i);
                                if (temp != -1 && obj.getInt("postId") == (temp+1)) {
                                    HomeSubCmt homeSubCmt = new HomeSubCmt(
                                            obj.getInt("postId"),
                                            obj.getInt("accountId"),
                                            obj.getString("avatarURL"),
                                            obj.getString("displayName"),
                                            obj.getString("content")
                                    );
                                    //Log.e("abc","e e e e e");
                                    homeSubCmtList.add(homeSubCmt);
                                }
                            }
                            HomeSubCmtAdapter adapter = new HomeSubCmtAdapter(homeSubCmtList, getApplicationContext());
                            lv_List_cmt.setAdapter(adapter);
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

    private void mapping(){
        img_picture_sub = (ImageView) findViewById(R.id.id_picture_sub);
        txt_title_sub = (TextView) findViewById(R.id.id_title_sub);
        txt_loves_sub = (TextView) findViewById(R.id.id_loves_sub);
        txt_content_sub = (TextView) findViewById(R.id.id_content_sub);
        txt_address_sub = (TextView) findViewById(R.id.id_address_sub);
        txt_displayName_sub = (TextView) findViewById(R.id.id_displayName_sub);
        lv_List_cmt = (ListView) findViewById(R.id.id_List_cmt);
    }
}
