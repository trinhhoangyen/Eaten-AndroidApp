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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword, edtPasswordAgain, edtDisplayName, edtYearOfBirth;
    private Button btnRegister;
    private Spinner spnGender;
    Context context;

    private boolean isSuccess = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hide ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Mapping
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPasswordAgain = (EditText) findViewById(R.id.edtPasswordAgain);
        edtDisplayName = (EditText) findViewById(R.id.edtDisplayName);
        edtYearOfBirth = (EditText) findViewById(R.id.edtYearOfBirth);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        spnGender = (Spinner) findViewById(R.id.spnGender);
        //Set dữ liệu cho Spinner
        String[] itemsGender = new String[]{"Giới tính: Nam", "Giới tính: Nữ", "Khác..."};
        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsGender);
        spnGender.setAdapter(adapterGender);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkIsValid()) {
                    String data = String.format("{\n" +
                            "  \"accountId\": 0,\n" +
                            "  \"email\": \"%s\",\n" +
                            "  \"password\": \"%s\",\n" +
                            "  \"displayName\": \"%s\",\n" +
                            "  \"avatar\": \"string\",\n" +
                            "  \"gender\": \"%s\",\n" +
                            "  \"yearOfBirth\": %d\n" +
                            "}", edtEmail.getText().toString(), edtPassword.getText().toString(), edtDisplayName.getText().toString(), getGender(spnGender.getSelectedItemPosition()), Integer.parseInt(edtYearOfBirth.getText().toString()));
                    Submit(data);
                    //Thông báo đăng ký thành công
                    if(isSuccess){
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("\n" + R.string.text_signuptrue).setCancelable(false).setNegativeButton(R.string.text_returnhome, new DialogInterface.OnClickListener() {
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
            }
        });
    }

    private void Submit(String data) {
        final String savedata = data;
        String URL = "https://thym.azurewebsites.net/api/Accounts/create-account/";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                isSuccess = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                isSuccess = false;
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

    private String getGender(int index){
        switch (index){
            case 0:
                return "Nam";
            case 1:
                return "Nữ";
            case 2:
                return "Khác";
        }
        return "";
    }

    private boolean checkIsValid(){
        boolean isValid = false;
        if(edtEmail.getText().toString().equals(""))
            edtEmail.setError("Email không được để trống!");
        else if(!edtEmail.getText().toString().contains("@") || !edtEmail.getText().toString().contains("."))
            edtEmail.setError("Email không hợp lệ!");
        else if(edtDisplayName.getText().toString().equals(""))
            edtDisplayName.setError("Tên hiển thị không được để trống!");
        else if(edtPassword.getText().toString().equals(""))
            edtPassword.setError("Mật khẩu không được để trống!");
        else if(edtPassword.getText().toString().length() < 8)
            edtPassword.setError("Mật khẩu phải dài hơn 8 kí tự!");
        else if(edtPasswordAgain.getText().toString().equals(""))
            edtPasswordAgain.setError("Vui lòng nhập lại mật khẩu!");
        else if(!edtPassword.getText().toString().equals(edtPasswordAgain.getText().toString()))
            edtPasswordAgain.setError("Mật khẩu nhập lại không chính xác!");
        else if(edtYearOfBirth.getText().toString().equals(""))
            edtYearOfBirth.setError("Năm sinh không được để trống!");
        else if(edtYearOfBirth.getText().toString().length() > 4)
            edtYearOfBirth.setError("Năm sinh không hợp lệ!");
        else
            isValid = true;
        return  isValid;
    }
}
