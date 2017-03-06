package com.its.its.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.its.its.R;

public class WelcomeActivity extends AppCompatActivity {

    Button btnPlay_Welcome_Land, btnJoin_Welcome_Land;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();

        addEvent();
    }

    private void addEvent() {
        btnPlay_Welcome_Land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnJoin_Welcome_Land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnPlay_Welcome_Land = (Button) findViewById(R.id.btnPlay_Welcome_Land);
        btnJoin_Welcome_Land = (Button) findViewById(R.id.btnJoin_Welcome_Land);
    }
}
