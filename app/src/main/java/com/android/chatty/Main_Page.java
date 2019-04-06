package com.android.chatty;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.chatty.B_Chat.B_Activity;
import com.android.chatty.util.ActivityUtilities;

public class Main_Page extends AppCompatActivity {
    Context context;

    Button blue,wifi,setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__page);



        blue=findViewById(R.id.bluetooth);
        wifi=findViewById(R.id.wifi);

        wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main_Page.this, MainActivity.class);
                startActivity(intent);
                ((Activity) getApplicationContext()).finish();
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main_Page.this,B_Activity.class);
                startActivity(intent);
            }
        });

        /*setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main_Page.this,feedback.class);
                startActivity(intent);
            }
        });*/


    }

}
