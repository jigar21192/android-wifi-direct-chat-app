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

public class Login extends AppCompatActivity {

    TextView txt_login;
    EditText Email_id,Password;
    Button btn_login;
    private ProgressDialog progress;
    String L_URL="https://unalienable-attorne.000webhostapp.com/login.php";

    String v_email;
    String v_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        txt_login=findViewById(R.id.txt_login);
        Email_id=findViewById(R.id.login_Email_id);
        Password=findViewById(R.id.login_password);
        btn_login=findViewById(R.id.btn_login);

        progress=new ProgressDialog(Login.this);
        progress.setMessage("Loading....");
        progress.setCancelable(false);

        btn_login.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                progress.show();
                v_email=Email_id.getText().toString();
                v_password=Password.getText().toString();

                  if (!v_email .matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
                  {
                            progress.dismiss();
                        Toast.makeText(Login.this, "Enter valid Email_id", Toast.LENGTH_SHORT).show();
                  }
                  else if(TextUtils.isEmpty(v_password))
                  {
                        progress.dismiss();
                        Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                  }
                  else
                      {
                      StringRequest request = new StringRequest(Request.Method.POST, L_URL, new Response.Listener<String>()
                      {
                          @Override
                          public void onResponse(String response)
                          {
                              if (response.trim().equals("success"))
                              {
                                  {
                                      progress.dismiss();
                                      Intent intent = new Intent(Login.this, Main_Page.class);
                                      startActivity(intent);
                                      finish();
                                  }
                              }
                              else
                                  {
                                        progress.dismiss();
                                        Toast.makeText(Login.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                                  }

                          }
                      }, new Response.ErrorListener()
                      {
                          @Override
                          public void onErrorResponse(VolleyError error)
                          {
                              progress.dismiss();
                              Toast.makeText(Login.this, "connection problem", Toast.LENGTH_SHORT).show();

                          }
                      })
                      {
                          protected Map<String, String> getParams() throws AuthFailureError
                          {
                              Map<String, String> param = new HashMap<>();
                              param.put("User_Name", Email_id.getText().toString());
                              param.put("password", Password.getText().toString());
                              return param;
                          }
                      };
                      RequestQueue Queue = Volley.newRequestQueue(Login.this);
                      Queue.add(request);
                  }
                }
            });


            txt_login.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    progress.dismiss();
                    Intent intent=new Intent(Login.this,Registration.class);
                    startActivity(intent);
                }
            });
    }
}
