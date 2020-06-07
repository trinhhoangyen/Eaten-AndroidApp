package com.example.eaten.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.eaten.DTO.Card;
import com.example.eaten.R;
import com.example.eaten.SearchActivity;

import java.util.List;

//



public class HomeFragment extends Fragment {

    GridView gv;
    List<Card> cardList;
    private static final String JSON_URL = "https://eatenapi.azurewebsites.net/api/Posts/get-all-post-info";
    private ImageView btnNewPost;
    private Spinner spinner;

    private static final String[] paths = {"item 1", "item 2", "item 3"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Intent in = new Intent(getActivity(), SearchActivity.class);
        in.putExtra("some", "some data");
        startActivity(in);
        View view = inflater.inflate(R.layout.activity_home, container, false);
//        EditText txtSearch = (EditText) view.findViewById(R.id.txtSearch);
//        txtSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("aaa", "onClick: thanh cong");
//                Intent in = new Intent(getActivity(), SearchActivity.class);
//                in.putExtra("some", "some data");
//                startActivity(in);
//            }
//        });


//        //Nhận accountID từ MainActivity
////
////        Intent sub = getIntent();
////        temp = (int) sub.getIntExtra("accID", -1);
//        //Nhận accountID từ MainActivity
//        SharedPreferences sp = getSharedPreferences("Save_ID_Acc", MODE_PRIVATE);
//        //Đọc dữ liệu
//        final int temp;
//        temp = sp.getInt("accID", -1); //X là kiểu dữ liệu
//
//        mapping();
//
//        loadGV();
//
//        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Card card_sub = (Card) cardList.get(position);
//                Intent sub = new Intent(view.getContext(), HomeSubActivity.class);
//                //sub.putExtra("accID", temp); //chuyển accountId sang HomeSubActivity
//                sub.putExtra("card", cardList.get(position).getPostId());
//                startActivity(sub);
//            }
//        });
        return view;
    }



//    @Override
////    public void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        //Nhận accountID từ MainActivity
//////
//////        Intent sub = getIntent();
//////        temp = (int) sub.getIntExtra("accID", -1);
////        //Nhận accountID từ MainActivity
////        SharedPreferences sp = getSharedPreferences("Save_ID_Acc", MODE_PRIVATE);
////        //Đọc dữ liệu
////        final int temp;
////        temp = sp.getInt("accID", -1); //X là kiểu dữ liệu
////
////        mapping();
////
////        loadGV();
////
////        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Card card_sub = (Card) cardList.get(position);
////                Intent sub = new Intent(view.getContext(), HomeSubActivity.class);
////                //sub.putExtra("accID", temp); //chuyển accountId sang HomeSubActivity
////                sub.putExtra("card", cardList.get(position).getPostId());
////                startActivity(sub);
////            }
////        });
////
//////        btnNewPost.setOnClickListener(new View.OnClickListener() {
//////                    @Override
//////                    public void onClick(View v) {
//////                        Intent intentNewPost = new Intent(HomeFragment.this, PostActivity.class);
//////                        //intentNewPost.putExtra("accID", temp); //chuyển accountId sang HomeSubActivity
//////                        startActivity(intentNewPost);
//////            }
//////        });
////    }


//    private void loadGV(){
//        cardList = new ArrayList<>();
//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.VISIBLE);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressBar.setVisibility(View.INVISIBLE);
//
//                        try{
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray cardArray = jsonObject.getJSONArray("data");
//                            //Log.e("abc","e e e e e");
//                            for(int i = 0 ; i < cardArray.length(); i++){
//                                JSONObject obj = cardArray.getJSONObject(i);
//                                //Log.e("abc","e e e e e");
//                                Card card = new Card(
//                                        obj.getInt("postId"),
//                                        obj.getInt("accountId"),
//                                        obj.getString("postName"),
//                                        obj.getString("content"),
//                                        obj.getString("address"),
//                                        obj.getString("displayName"),
//                                        obj.getString("pictureURL"),
//                                        //null,
//                                        obj.getInt("reactQuantity")
////                                        obj.getInt("postId"),
////                                        obj.getInt("accountId"),
////                                        obj.getString("postName"),
////                                        "",
////                                        obj.getString("address"),
////                                        "",
////                                        null,
////                                        obj.getInt("reactQuantity")
//                                );
//                                //Log.e("abc","e e e e e");
//                                cardList.add(card);
//                            }
//                            Collections.reverse(cardList);
//                            CardAdapter adapter = new CardAdapter(cardList, getApplicationContext());
//                            gv.setAdapter(adapter);
//                            Log.e("abc","e e e e e");
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                            //Log.e("abc","e e e e e");
//                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //Log.e("abc","e e e e e");
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.INVISIBLE);
//                    }
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
//
////    private void loadGV(){
////        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
////        progressBar.setVisibility(View.VISIBLE);
////        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
////                new Response.Listener<String>() {
////                    @Override
////                    public void onResponse(String response) {
////                        progressBar.setVisibility(View.INVISIBLE);
////
////                        try{
////                            JSONArray cardArray =new JSONArray(response);
////                            for(int i = 0 ; i < cardArray.length(); i++){
////                                JSONObject obj = cardArray.getJSONObject(i);
////                                Card card = new Card(
////                                obj.getInt("postId"),
////                                obj.getInt("accountId"),
////                                obj.getString("postName"),
////                                obj.getString("content"),
////                                obj.getString("address"),
////                                obj.getString("picture"),
////                                obj.getInt("displayName"),
////                                obj.getInt("reactQuantity")
////                                );
////                                cardList.add(card);
////                            }
////                            adapter = new CardAdapter(this, R.layout.dong_imgfood, cardList);
////                            gv.setAdapter(adapter);
////                        }catch (JSONException e){
////                            e.printStackTrace();
////                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
////                        }
////                    }
////                },
////                new Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError error) {
////                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
////                        progressBar.setVisibility(View.INVISIBLE);
////                    }
////                });
////        RequestQueue requestQueue = Volley.newRequestQueue(this);
////        requestQueue.add(stringRequest);
////    }
//
//    private void mapping(){
//
//        gv = (GridView) gv.findViewById(R.id.gridView0);
//    }

}