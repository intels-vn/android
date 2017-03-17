package com.its.its.model.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.http.CommonRequest;
import com.its.its.view.MainActivity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Chinh Bui on 3/16/2017.
 */

public class WithdrawTask extends AsyncTask<String, Void, String> {

    Activity activity;
    TextView textView;
    ProgressDialog dialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(activity);
        dialog.setTitle("Process");
        dialog.setMessage("Please wait ...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
        textView.setText(s);
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    public WithdrawTask(Activity activity, TextView textView) {
        this.activity = activity;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        String api = params[0];
        String token = params[1];

        try {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Localization", activity.getResources().getString(R.string.localization));
            headers.put("Authorization", token);

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse("POST", api, headers, null);

            DataReturn data = new Gson().fromJson(inputStreamReader, DataReturn.class);
            switch (data.getStatus()){
                case "200":
                    result = data.getData().toString();
                    break;
                default:
                    break;
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }
}
