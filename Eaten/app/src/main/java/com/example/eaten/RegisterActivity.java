package com.example.eaten;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.eaten.DTO.Account;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword, edtPasswordAgain;
    private Button btnRegister;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hide ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPasswordAgain = (EditText) findViewById(R.id.edtPasswordAgain);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPassword.getText().toString().equals(edtPasswordAgain.getText().toString()) && !edtEmail.getText().toString().equals("") && !edtPassword.getText().toString().equals("") && !edtPasswordAgain.getText().toString().equals("")) {
                    String data = String.format("{\n" +
                            "  \"accountId\": 0,\n" +
                            "  \"email\": \"%s\",\n" +
                            "  \"password\": \"%s\",\n" +
                            "  \"displayName\": \"string\",\n" +
                            "  \"avatar\": \"string\",\n" +
                            "  \"gender\": \"string\",\n" +
                            "  \"yearOfBirth\": 0\n" +
                            "}", edtEmail.getText().toString(), edtPassword.getText().toString());
                    Submit(data);
                    //Thông báo đăng ký thành công
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("\n" + R.string.text_signintrue).setCancelable(false).setNegativeButton(R.string.text_returnhome, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentMain = new Intent(((Dialog)dialog).getContext(), MainActivity.class);
                            startActivity(intentMain);
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
                    builder.setMessage("\n" + R.string.text_signupfalse).setCancelable(false).setNegativeButton(R.string.text_exit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentMain = new Intent(((Dialog)dialog).getContext(), MainActivity.class);
                            startActivity(intentMain);
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

    private void Submit(String data) {
        final String savedata = data;
        String URL = "https://eatenapi.azurewebsites.net/api/Accounts/create-account/";

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
                Toast.makeText(getApplicationContext(), R.string.text_signupfalse, Toast.LENGTH_LONG).show();
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
