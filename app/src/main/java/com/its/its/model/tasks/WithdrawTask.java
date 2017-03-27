package com.its.its.model.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.Coin;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.http.CommonRequest;
import com.its.its.view.MainActivity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Chinh Bui on 3/16/2017.
 */

public class WithdrawTask extends AsyncTask<String, Void, DataReturn> {

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

    public WithdrawTask(Activity activity, TextView textView) {
        this.activity = activity;
        this.textView = textView;
    }

    @Override
    protected DataReturn doInBackground(String... params) {
        DataReturn result = new DataReturn();

        String api = params[0];
        String token = params[1];

        try {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Localization", activity.getResources().getString(R.string.localization));
            headers.put("Authorization", token);

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse(CommonRequest.POST, api, headers, null);
            if(inputStreamReader != null){
                result = new Gson().fromJson(inputStreamReader, DataReturn.class);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(DataReturn s) {
        super.onPostExecute(s);

        dialog.dismiss();
        String data = s.getData().toString();
        Coin current_coin = new Gson().fromJson(data, Coin.class);

        String money = current_coin.toString();
        if(s != null) {
            textView.setText(money);
            Log.d("Money ", money);
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
        }
    }
}
