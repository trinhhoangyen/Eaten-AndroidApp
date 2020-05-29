package com.example.eaten;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eaten.DTO.Account;
import com.example.eaten.DTO.Card;
import com.example.eaten.myadapter.CardAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AccInfoActivity extends AppCompatActivity {
    ListView lv;
    List<Card> cardList;
    ImageView acc_info;
    TextView displayname_info, email_info, gender_info;
    int temp;

    private static final String JSON_URL = "https://eatenapi.azurewebsites.net/api/Posts/get-all-post-info";
    private static final String JSON_URLACC = "https://eatenapi.azurewebsites.net/api/Accounts/get-all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_info);

        SharedPreferences sp = getSharedPreferences("Save_ID_Acc", MODE_PRIVATE);
        //Đọc dữ liệu
        temp = sp.getInt("accID", -1); //X là kiểu dữ liệu

        mapping();
        loadacc();
        loadlv();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Card card_sub = (Card) cardList.get(position);
                Intent sub = new Intent(view.getContext(), HomeSubActivity.class);
                //sub.putExtra("accID", temp); //chuyển accountId sang HomeSubActivity
                sub.putExtra("card", cardList.get(position).getPostId());
                startActivity(sub);
            }
        });
    }
    private void loadacc(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URLACC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray cardArray = jsonObject.getJSONArray("data");
                            //Log.e("abc","e e e e e");
                            for(int i = 0 ; i < cardArray.length(); i++) {
                                JSONObject obj = cardArray.getJSONObject(i);
                                if (temp != -1 && i+1 == temp) {
                                    Account account = new Account(
                                            obj.getInt("accountId"),
                                            obj.getInt("yearOfBirth"),
                                            obj.getString("email"),
                                            obj.getString("password"),
                                            obj.getString("displayName"),
                                            obj.getString("avatarURL"),
                                            obj.getString("gender")
                                    );
                                    Picasso.with(AccInfoActivity.this)
                                            .load(account.getAvatar())
                                            //.resize(Home_sub.this.getWindow().getDecorView().getWidth(), 300)
                                            //.centerInside()
                                            .fit()
                                            .centerCrop()
                                            .into(acc_info);
                                    displayname_info.setText(account.getDisplayName());
                                    email_info.setText(account.getEmail());
                                    gender_info.setText(account.getGender());
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
                        //progressBar.setVisibility(View.INVISIBLE);
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void loadlv(){
        cardList = new ArrayList<>();
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar_info);
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
                                if (temp != -1 && obj.getInt("accountId") == temp) {
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
                                    cardList.add(card);
                                }
                            }
                            CardAdapter adapter = new CardAdapter(cardList, getApplicationContext());
                            lv.setAdapter(adapter);
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
        acc_info = (ImageView) findViewById(R.id.id_acc_info);
        displayname_info = (TextView) findViewById(R.id.id_displayname_info);
        email_info = (TextView) findViewById(R.id.id_email_info);
        gender_info = (TextView) findViewById(R.id.id_gender_info);
        lv = (ListView) findViewById(R.id.id_list_status_info);
    }
}
