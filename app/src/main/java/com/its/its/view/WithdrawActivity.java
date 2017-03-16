package com.its.its.view;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.its.its.R;
import com.its.its.model.tasks.WithdrawTask;
import com.its.its.presenter.security.EncryptDecrypt;

public class WithdrawActivity extends AppCompatActivity {

    String[] amoutValues = {"10.000", "20.000", "50.000", "100.000", "200.000", "500.000"};
    Button btnWithdraw;
    Spinner spAmount;
    String amount;
    EditText edtPhoneReceiveExchangeWithdraw;

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
                        EditText edtPasswordVerifyWithdraw = (EditText) dialog.findViewById(R.id.edtPasswordVerifyWithdraw);

                        new WithdrawTask(WithdrawActivity.this).execute(
                                "http://192.168.100.14:8080/Demo/withdraw?id=" + getIntent().getStringExtra("ID") +
                                        "&amount=" + amount +
                                        "&phoneRX=" + EncryptDecrypt.encrypt(
                                                edtPhoneReceiveExchangeWithdraw.getText().toString(),
                                                getResources().getString(R.string.khoa)) +
                                        "&password=" + EncryptDecrypt.encrypt(
                                                edtPasswordVerifyWithdraw.getText().toString(),
                                                getResources().getString(R.string.khoa)),
                                getIntent().getStringExtra("TOKEN")
                        );

//                        Toast.makeText(WithdrawActivity.this, txtPassword.getText().toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        spAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (amoutValues[position]){
                    case "10.000":
                        amount = "10000";
                        break;
                    case "20.000":
                        amount = "20000";
                        break;
                    case "50.000":
                        amount = "50000";
                        break;
                    case "100.000":
                        amount = "100000";
                        break;
                    case "200.000":
                        amount = "200000";
                        break;
                    case "500.000":
                        amount = "500000";
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        spAmount = (Spinner) findViewById(R.id.spAmount);
        edtPhoneReceiveExchangeWithdraw = (EditText) findViewById(R.id.edtPhoneReceiveExchangeWithdraw);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                WithdrawActivity.this, android.R.layout.simple_spinner_dropdown_item, amoutValues);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spAmount.setAdapter(adapter);

        btnWithdraw = (Button) findViewById(R.id.btnWithdraw);
    }
}
