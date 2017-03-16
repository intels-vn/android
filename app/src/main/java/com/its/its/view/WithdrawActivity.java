package com.its.its.view;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.its.its.R;

public class WithdrawActivity extends AppCompatActivity {

    String[] amoutValues = {"10.000", "20.000", "50.000", "100.000", "200.000", "500.000"};
    Button btnWithdraw;
    Spinner spAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        initView();
        addEvents();
    }

    private void addEvents() {
        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(WithdrawActivity.this);
                dialog.setContentView(R.layout.activity_verify_password);
                dialog.setTitle(getResources().getString(R.string.verify_password));
                Button btnVerify = (Button) dialog.findViewById(R.id.btnVerify);
                btnVerify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(WithdrawActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        EditText txtPassword = (EditText) dialog.findViewById(R.id.txtPassword);
                        Toast.makeText(WithdrawActivity.this, txtPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void initView() {
        spAmount = (Spinner) findViewById(R.id.spAmount);
        btnWithdraw = (Button) findViewById(R.id.btnWithdraw);
    }
}
