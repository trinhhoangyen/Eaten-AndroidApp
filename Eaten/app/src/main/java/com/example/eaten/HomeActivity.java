package com.example.eaten;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class HomeActivity extends AppCompatActivity {
    GridView gv;
    List<Card> cardList;
    private static final String JSON_URL = "https://eatenapi.azurewebsites.net/api/Posts/get-all-post-info";
    Spinner spinnerRegion;

    GridView grd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Hide ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.getBackground().setAlpha(200);
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
                        break;
                    case R.id.navigation_videos:
                        Intent in2 = new Intent(HomeActivity.this, VideosActivity.class);
                        startActivity(in2);
                        break;
                    case R.id.navigation_post:
                        Intent in3 = new Intent(HomeActivity.this, PostActivity.class);
                        startActivity(in3);
                        break;
                    case R.id.navigation_notifications:
                        Intent in4 = new Intent(HomeActivity.this, NotificationsActivity.class);
                        startActivity(in4);
                        break;
                    case R.id.navigation_profile:
                        Intent in1 = new Intent(HomeActivity.this, AccInfoActivity.class);
                        startActivity(in1);
                        break;
                }
            return true;
            }

        });



         EditText txtSearch = (EditText) this.findViewById(R.id.txtSearch);
         txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this ,SearchActivity.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });

        //Nhận accountID từ MainActivity
        SharedPreferences sp = getSharedPreferences("Save_ID_Acc", MODE_PRIVATE);
        //Đọc dữ liệu
        final int temp;
        temp = sp.getInt("accID", -1); //X là kiểu dữ liệu

        mapping();
        loadGV();

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Card card_sub = (Card) cardList.get(position);
                Intent sub = new Intent(view.getContext(), HomeSubActivity.class);
                //sub.putExtra("accID", temp); //chuyển accountId sang HomeSubActivity
                sub.putExtra("card", cardList.get(position).getPostId());
                startActivity(sub);
            }
        });

        spinnerRegion = (Spinner) findViewById(R.id.spnRegion);
        final ArrayList<String> arrayRegion = new ArrayList<String>();
        arrayRegion.add("TP HỒ CHÍ MINH");
        arrayRegion.add("TP CẦN THƠ");
        arrayRegion.add("TP CAO LÃNH");
        arrayRegion.add("TP VĨNH LONG");
        arrayRegion.add("TP BẢO LỘC");
        arrayRegion.add("TP TÂN AN");
        arrayRegion.add("TP SA ĐÉC");
        arrayRegion.add("TP HỒ CHÍ MINH");
        arrayRegion.add("TP CẦN THƠ");
        arrayRegion.add("TP CAO LÃNH");
        arrayRegion.add("TP VĨNH LONG");
        arrayRegion.add("TP BẢO LỘC");
        arrayRegion.add("TP TÂN AN");
        arrayRegion.add("TP SA ĐÉC");
        arrayRegion.add("TP HỒ CHÍ MINH");
        arrayRegion.add("TP CẦN THƠ");
        arrayRegion.add("TP CAO LÃNH");
        arrayRegion.add("TP VĨNH LONG");
        arrayRegion.add("TP BẢO LỘC");
        arrayRegion.add("TP TÂN AN");
        arrayRegion.add("TP SA ĐÉC");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayRegion);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegion.setAdapter(arrayAdapter);

        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomeActivity.this, arrayRegion.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerRegion.setMinimumHeight(100);

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
////
////
    private void mapping(){
        gv = (GridView) findViewById(R.id.gridView0);
    }


    private class Async extends AsyncTask<String, Void, Integer> {
        Context context;
        GridView gridView;


        public Async(Context context,GridView gridView) {
            // TODO Auto-generated constructor stub

            this.context=context;
            this.gridView=gridView;
        }

        protected Integer doInBackground(String... params) {
            // TODO Auto-generated method stub


            Integer result = 0;
            try {
                // Create Apache HttpClient
                //HttpClient httpclient = new DefaultHttpClient();
                URL url = new URL(JSON_URL);
                URLConnection urlConnection = url.openConnection();
                InputStream in = new BufferedInputStream(
                        urlConnection.getInputStream());

                // int statusCode =
                // httpResponse.getStatusLine().getStatusCode();

                // 200 represents HTTP OK
                if (true) {
                    String response = streamToString(in);
                    parseResult(response);
                    result = 1; // Successful
                } else {
                    result = 0;       // "Failed
                }
            } catch (Exception e) {

            }

            return result;



        }
        String streamToString(InputStream stream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(stream));
            String line;
            String result = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            // Close stream
            if (null != stream) {
                stream.close();
            }
            return result;
        }

        private void parseResult(String result) {
            try {

                Log.d("MainActivity", "JSON Result : " + result);
                JSONArray response = new JSONArray(result);

                for (int i = 0; i < response.length(); i++)

                {

                    JSONObject obj = response.getJSONObject(i);

                    String Doc_name = obj.getString("documentName");
                    Log.d("documentName",Doc_name);

                    String Doc_file = obj.getString("documentFile");
                    String Doc_content = obj.getString("documentContent");

                    String Doc_offer=obj.getString("offer");
                    String Doc_address=obj.getString("address");

                    //Log.d("documentName","JSON Result : " + result);


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


}




