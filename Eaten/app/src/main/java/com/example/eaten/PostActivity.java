package com.example.eaten;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        //
        btnPost = (Button) findViewById(R.id.btnPost);
        edtPostName = (EditText) findViewById(R.id.edtPostName);
        edtContent = (EditText) findViewById(R.id.edtContent);
        edtAddress = (EditText) findViewById(R.id.edtAddress);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        imgNewPost = (ImageView) findViewById(R.id.imgNewPost);
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
                            "  \"accountId\": 1,\n" +
                            "  \"postName\": \"%s\",\n" +
                            "  \"content\": \"%s\",\n" +
                            "  \"address\": \"%s\",\n" +
                            "  \"pictureURL\": \"%s\"\n" +
                            "}", edtPostName.getText().toString(), edtContent.getText().toString(), edtAddress.getText().toString(), imageURL);
                    Submit(data);
                    //Thông báo đăng ký thành công
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("\nBài viết của bạn đã tải lên thành công!").setCancelable(false).setNegativeButton("Trở về trang chủ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentMain = new Intent(((Dialog)dialog).getContext(), MainActivity.class);
                            startActivity(intentMain);
                        }
                    }).setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setTitle("Thông báo");
                    alertDialog.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("\nĐăng bài thất bại, bạn vui lòng nhâp đủ thông tin!").setCancelable(false).setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentHome = new Intent(((Dialog)dialog).getContext(), HomeActivity.class);
                            startActivity(intentHome);
                        }
                    }).setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setTitle("Thông báo");
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
                            Toast.makeText(PostActivity.this, "Tải ảnh lên thành công!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } else {
            Toast.makeText(this, "Thêm ảnh thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(this.getContentResolver().getType(uri));
    }

    private void Submit(String data) {
        String URL = "https://eatenapi.azurewebsites.net/api/Posts/add-post/";

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
                Toast.makeText(getApplicationContext(), "Đăng bài thất bại!", Toast.LENGTH_LONG).show();
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
