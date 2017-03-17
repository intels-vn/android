package com.its.its.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.its.its.R;

public class WelcomeActivity extends AppCompatActivity {

    Button btnPlay_Welcome, btnJoin_Welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
        addEvent();
    }

    //Fetch the ID from the welcome layout.
    private void initView() {
        btnPlay_Welcome = (Button) findViewById(R.id.btnPlay_Welcome);
        btnJoin_Welcome = (Button) findViewById(R.id.btnJoin_Welcome);
    }

    //Add events to the buttons.
    private void addEvent() {
        btnPlay_Welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnJoin_Welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
