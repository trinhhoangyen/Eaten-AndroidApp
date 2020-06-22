package com.example.eaten;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {
    private Button btnPost;
    private EditText edtPostName, edtContent, edtAddress;
    private ImageView imgPicture, imgNewPost;
    //Các biến phục vụ cho Firebase
    private StorageReference mStorageRef;
    private FirebaseStorage storage;
    private Uri imageUri;
    //Đường dẫn ảnh upload
    private String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Hide ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        //bottom navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();
//        MenuItem menuItem0 = menu.getItem(0 );
//        menuItem0.setChecked(true);
//        MenuItem menuItem1 = menu.getItem(1 );
//        menuItem1.setChecked(true);
        MenuItem menuItem2 = menu.getItem(2 );
        menuItem2.setChecked(true);
//        MenuItem menuItem3 = menu.getItem(3);
//        menuItem3.setChecked(true);
//        MenuItem menuItem4 = menu.getItem(4 );
//        menuItem4.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent in1 = new Intent(PostActivity.this, HomeActivity.class);
                        startActivity(in1);
                        Animatoo.animateSlideRight(PostActivity.this);
                        finish();
                        break;
                    case R.id.navigation_videos:
                        Intent in2 = new Intent(PostActivity.this, VideosActivity.class);
                        startActivity(in2);
                        Animatoo.animateSlideRight(PostActivity.this);
                        finish();
                        break;
                    case R.id.navigation_post:
                        break;
                    case R.id.navigation_notifications:
                        Intent in3 = new Intent(PostActivity.this, NotificationsActivity.class);
                        startActivity(in3);
                        Animatoo.animateSlideLeft(PostActivity.this);
                        finish();
                        break;
                    case R.id.navigation_profile:
                        Intent in4 = new Intent(PostActivity.this, AccInfoActivity.class);
                        startActivity(in4);
                        Animatoo.animateSlideLeft(PostActivity.this);
                        finish();
                        break;
                }
                return true;
            }

        });
        //
        btnPost = (Button) findViewById(R.id.btnPost);
        edtPostName = (EditText) findViewById(R.id.edtPostName);
        edtContent = (EditText) findViewById(R.id.edtContent);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        imgNewPost = (ImageView) findViewById(R.id.imgNewPost);
        //Nhận dữ liệu accountId từ HomeActivity
        final int accountID;
        //Intent home = getIntent();

        SharedPreferences sp = getSharedPreferences("Save_ID_Acc", MODE_PRIVATE);
        //Đọc dữ liệu
        accountID = sp.getInt("accID", -1); //X là kiểu dữ liệu

        //Sử dụng Firebase
        mStorageRef = FirebaseStorage.getInstance().getReference();
        storage = FirebaseStorage.getInstance(mStorageRef.toString());

        imgPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtPostName.getText().toString().equals("") && !edtContent.getText().toString().equals("") && !edtAddress.getText().toString().equals("")) {
                    String data = String.format("{\n" +
                            "  \"accountId\": %d,\n" +
                            "  \"postName\": \"%s\",\n" +
                            "  \"content\": \"%s\",\n" +
                            "  \"address\": \"%s\",\n" +
                            "  \"pictureURL\": \"%s\"\n" +
                            "}", accountID, edtPostName.getText().toString(), edtContent.getText().toString(), edtAddress.getText().toString(), imageURL);
                    Submit(data);
                    //Thông báo đăng ký thành công
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("\n" + R.string.text_addstatustrue).setCancelable(false).setNegativeButton(R.string.text_returnhome, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentHome = new Intent(((Dialog)dialog).getContext(), HomeActivity.class);
                            startActivity(intentHome);
                        }
                    }).setPositiveButton(R.string.text_continue, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setTitle(R.string.text_notification);
                    alertDialog.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("\n" + R.string.text_shortinfo).setCancelable(false).setNegativeButton(R.string.text_exit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentHome = new Intent(((Dialog)dialog).getContext(), HomeActivity.class);
                            startActivity(intentHome);
                        }
                    }).setPositiveButton(R.string.text_continue, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setTitle(R.string.text_notification);
                    alertDialog.show();
                }
            }
        });
    }

    private void openImage(){
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == -1){
            imageUri = data.getData();
            uploadImage();
        }
    }

    private void uploadImage() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Đang tải ảnh lên...");
        pd.show();

        if (imageUri != null) {
            final StorageReference fileRef = FirebaseStorage.getInstance().getReference().child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            Log.d("Image URL", url);
                            pd.dismiss();
                            imageURL = url;
                            Picasso.with(PostActivity.this)
                                    .load(imageURL)
                                    //.resize(Home_sub.this.getWindow().getDecorView().getWidth(), 300)
                                    //.centerInside()
                                    .fit()
                                    .centerCrop()
                                    .into(imgNewPost);
                            Toast.makeText(PostActivity.this, R.string.text_loadimgtrue, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } else {
            Toast.makeText(this, R.string.text_loadimgfalse, Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(this.getContentResolver().getType(uri));
    }

    private void Submit(String data) {
        String URL = "https://thym.azurewebsites.net/api/Posts/add-post/";

        final String savedata = data;

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
                Toast.makeText(getApplicationContext(), R.string.text_shortinfo, Toast.LENGTH_LONG).show();
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
}
