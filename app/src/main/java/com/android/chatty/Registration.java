package com.android.chatty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    TextView txt_reg;
    EditText e_fullname,e_mobileno,e_dob,e_password,e_cpassword,e_username;
    Button registration;
    private ProgressDialog progress;

    String R_URL="https://unalienable-attorne.000webhostapp.com/insertr.php";

    String username;
    String mobileno;
    String dob;
    String email_id;
    String password;
    String c_password;

    Matcher user_name;//only char

    Boolean u_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        e_fullname=(EditText) findViewById(R.id.fullname);
        e_mobileno=(EditText) findViewById(R.id.mobileno);
        e_dob=(EditText) findViewById(R.id.dob);
        e_password=(EditText) findViewById(R.id.password);
        e_cpassword=(EditText) findViewById(R.id.cpassword);
        e_username=(EditText)findViewById(R.id.username);
        registration=(Button) findViewById(R.id.btn_register);
        txt_reg=(TextView) findViewById(R.id.txt_reg);

        progress=new ProgressDialog(Registration.this);
        progress.setMessage("Loading...");
        progress.setCancelable(false);

        registration.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            progress.show();
            //validation
            username=e_fullname.getText().toString();
                mobileno=e_mobileno.getText().toString();
                dob=e_dob.getText().toString();
                email_id=e_username.getText().toString();
                password=e_password.getText().toString();
                c_password=e_cpassword.getText().toString();

                Pattern ch1 = Pattern.compile("^[a-zA-Z]+$");//only char

                user_name=ch1.matcher(e_fullname.getText().toString());
                u_name=user_name.matches();

                    if(TextUtils.isEmpty(username)){
                        progress.dismiss();
                        Toast.makeText(Registration.this, "Enter User_name", Toast.LENGTH_SHORT).show();
                    }
                    else if(u_name == false){
                        progress.dismiss();
                        Toast.makeText(Registration.this, "Only Enter User_name", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(mobileno)){
                        progress.dismiss();
                        Toast.makeText(Registration.this, "Enter Mobile_no", Toast.LENGTH_SHORT).show();
                    }
                    //else if(mobileno.length()!=10){
                      //  Toast.makeText(com.android.chatty.Registration.this, "Valid mobile Number", Toast.LENGTH_SHORT).show();
                    //}
                    else if (TextUtils.isEmpty(dob)){
                        progress.dismiss();
                        Toast.makeText(Registration.this, "Enter Date Of birathe", Toast.LENGTH_SHORT).show();
                    }
                    else if (!email_id.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")){
                        progress.dismiss();
                        Toast.makeText(Registration.this, "Enter valid Email_id", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(password)){
                        progress.dismiss();
                        Toast.makeText(Registration.this, "Enter password", Toast.LENGTH_SHORT).show();
                    }

                    else if (password.length()<5){
                        progress.dismiss();
                        Toast.makeText(Registration.this, "Please Enter Minimum 5 Digits Password", Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(c_password)){
                        progress.dismiss();
                        Toast.makeText(Registration.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    }
                    else if (!password.equals(c_password)){
                        progress.dismiss();
                        Toast.makeText(Registration.this, "Please Confirm Password ", Toast.LENGTH_SHORT).show();
                    }

                    else
                        {
                        StringRequest request = new StringRequest(Request.Method.POST, R_URL, new Response.Listener<String>() {
                            @Override
                                public void onResponse(String response) {
                                progress.dismiss();
                                Toast.makeText(Registration.this, "successfully insert", Toast.LENGTH_SHORT).show();

                                //it successfully registration so go to the connecting page
                                Intent intent=new Intent(Registration.this,Login.class);
                                startActivity(intent);
                            }
                        },
                                new Response.ErrorListener() {
                                @Override
                                    public void onErrorResponse(VolleyError error) {
                                    progress.dismiss();
                                    Toast.makeText(Registration.this, "connection problem", Toast.LENGTH_SHORT).show();

                                }
                        })

                        {
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> param = new HashMap<>();
                                param.put("Name", e_fullname.getText().toString());
                                param.put("Mobile_No", e_mobileno.getText().toString());
                                param.put("DOB", e_dob.getText().toString());
                                param.put("User_Name",e_username.getText().toString());
                                param.put("Password", e_password.getText().toString());
                                param.put("Con_Password", e_cpassword.getText().toString());
                                return param;
                            }
                        };
                            RequestQueue Queue = Volley.newRequestQueue(Registration.this);
                            Queue.add(request);
                    }

                }
            });
        txt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,Login.class);
                startActivity(intent);
            }
        });

    }
}

