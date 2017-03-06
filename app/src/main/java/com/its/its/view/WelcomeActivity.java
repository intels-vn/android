package com.its.its.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.its.its.R;

public class WelcomeActivity extends AppCompatActivity {

    Button btnPlay_Land, btnJoin_Land;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();

        addEvent();
    }

    private void addEvent() {
        btnPlay_Land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnPlay_Land = (Button) findViewById(R.id.btnPlay_Land);
        btnJoin_Land = (Button) findViewById(R.id.btnJoin_Land);
    }
}
