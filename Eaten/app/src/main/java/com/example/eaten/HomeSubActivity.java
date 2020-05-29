package com.example.eaten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeSubActivity extends AppCompatActivity {
    ImageView img_picture_sub, img_acc_nocmt;
    TextView txt_title_sub, txt_loves_sub, txt_content_sub, txt_address_sub, txt_displayName_sub, txt_name_acc_nocmt;
    EditText content_nocmt;
    ListView lv_List_cmt;
    List<HomeSubCmt> homeSubCmtList;

    int temp, accID;
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
        //Nhận accountID từ MainActivity
        SharedPreferences sp = getSharedPreferences("Save_ID_Acc", MODE_PRIVATE);
        //Đọc dữ liệu
        accID = sp.getInt("accID", -1); //X là kiểu dữ liệu

        load();
        loadacc();
        loadCmt();
    }

    private void load(){
        //cardList = new ArrayList<>();
        //final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.INVISIBLE);

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
                        //progressBar.setVisibility(View.INVISIBLE);
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void loadCmt(){
        homeSubCmtList = new ArrayList<>();
        //final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URLCMT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.INVISIBLE);

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
                            Collections.reverse(homeSubCmtList);
                            HomeSubCmtAdapter adapter = new HomeSubCmtAdapter(homeSubCmtList, getApplicationContext());
                            lv_List_cmt.setAdapter(adapter);
                            //sét độ dài của listview theo độ số lượng cmt
                            ListAdapter listAdapter = lv_List_cmt.getAdapter();
                            if(listAdapter != null){
                                int totalheight = 0 ;
                                for (int i = 0 ; i < listAdapter.getCount(); i++){
                                    View listItems = listAdapter.getView(i, null, lv_List_cmt);
                                    listItems.measure(0,0);
                                    totalheight += listItems.getMeasuredHeight();
                                }
                                ViewGroup.LayoutParams params = lv_List_cmt.getLayoutParams();
                                params.height = totalheight + (lv_List_cmt.getDividerHeight() * (listAdapter.getCount()-1)) + 5;
                                lv_List_cmt.setLayoutParams(params);
                                lv_List_cmt.requestLayout();
                            }
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
                        //progressBar.setVisibility(View.INVISIBLE);
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //Load avatar và tên của account đang đăng nhập
    private void loadacc(){
        final String JSON_ACC = "https://eatenapi.azurewebsites.net/api/Accounts/get-all";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_ACC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray accArray = jsonObject.getJSONArray("data");
                            //Log.e("abc","e e e e e");
                            for(int i = 0 ; i < accArray.length(); i++) {
                                if (accID != -1 && (i+1) == accID) {
                                    JSONObject obj = accArray.getJSONObject((i));
                                    Picasso.with(HomeSubActivity.this)
                                            .load(obj.getString("avatarURL"))
                                            //.resize(Home_sub.this.getWindow().getDecorView().getWidth(), 300)
                                            //.centerInside()
                                            .fit()
                                            .centerCrop()
                                            .into(img_acc_nocmt);
                                    txt_name_acc_nocmt.setText(obj.getString("displayName"));
                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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

        img_acc_nocmt = (ImageView) findViewById(R.id.id_img_acc_nocmt);
        txt_name_acc_nocmt = (TextView) findViewById(R.id.id_name_acc_nocmt);
        content_nocmt = (EditText) findViewById(R.id.id_content_nocmt);
        content_nocmt.setOnEditorActionListener(editorListener);
    }
    private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId){
                case EditorInfo.IME_ACTION_SEND:
                    if (!content_nocmt.getText().toString().equals("")) {
                        String data = String.format("{\n" +
                                "  \"commentId\": 1,\n" +
                                "  \"postId\": %d,\n" +
                                "  \"accountId\": %d,\n" +
                                "  \"content\": \"%s\",\n" +
                                "  \"react\": 0,\n" +
                                "  \"rate\": 0\n" +
                                "}", temp+1, accID, content_nocmt.getText().toString());
                        Submit(data);
                    }
                    else {
                        Toast.makeText(HomeSubActivity.this, "Không nhập bình luận!", Toast.LENGTH_SHORT).show();
                    }
                    closeKeyboard();
                    loadCmt();
                    content_nocmt.setText("");
                    break;
                default:
                    break;
            }
            return false;
        }
    };
    private void Submit(String data) {
        final String savedata = data;
        String URL = "https://eatenapi.azurewebsites.net/api/Comments/create-comment";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Không thể bình luận!", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return savedata == null ? null : savedata.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    //Log.v("Unsupported Encoding while trying to get the bytes", data);
                    return null;
                }
            }
        };
        requestQueue.add(stringRequest);
    }
    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
