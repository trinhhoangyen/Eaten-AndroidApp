package com.example.eaten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eaten.DTO.Card;
import com.example.eaten.myadapter.CardAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    GridView gv;
    List<Card> cardList;
    private static final String JSON_URL = "https://eatenapi.azurewebsites.net/api/Posts/get-all-post-info";
    private Button btnNewPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //nhận accountID từ MainActivity
        final int temp;
        Intent sub = getIntent();
        temp = (int) sub.getIntExtra("accID", -1);


        mapping();

        loadGV();

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Card card_sub = (Card) cardList.get(position);
                Intent sub = new Intent(view.getContext(), HomeSubActivity.class);
                sub.putExtra("accID", temp); //chuyển accountId sang HomeSubActivity
                sub.putExtra("card", position);
                startActivity(sub);
            }
        });

//        btnNewPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentNewPost = new Intent(HomeActivity.this, PostActivity.class);
//                startActivity(intentNewPost);
//            }
//        });
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
//                                        obj.getInt("postId"),
//                                        obj.getInt("accountId"),
//                                        obj.getString("postName"),
//                                        "",
//                                        obj.getString("address"),
//                                        "",
//                                        null,
//                                        obj.getInt("reactQuantity")
                                );
                                //Log.e("abc","e e e e e");
                                cardList.add(card);
                            }
                            CardAdapter adapter = new CardAdapter(cardList, getApplicationContext());
                            gv.setAdapter(adapter);
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

//    private void loadGV(){
//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.VISIBLE);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressBar.setVisibility(View.INVISIBLE);
//
//                        try{
//                            JSONArray cardArray =new JSONArray(response);
//                            for(int i = 0 ; i < cardArray.length(); i++){
//                                JSONObject obj = cardArray.getJSONObject(i);
//                                Card card = new Card(
//                                obj.getInt("postId"),
//                                obj.getInt("accountId"),
//                                obj.getString("postName"),
//                                obj.getString("content"),
//                                obj.getString("address"),
//                                obj.getString("picture"),
//                                obj.getInt("displayName"),
//                                obj.getInt("reactQuantity")
//                                );
//                                cardList.add(card);
//                            }
//                            adapter = new CardAdapter(this, R.layout.dong_imgfood, cardList);
//                            gv.setAdapter(adapter);
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.INVISIBLE);
//                    }
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }

    private void mapping(){

        gv = (GridView) findViewById(R.id.gridView0);
    }
}
