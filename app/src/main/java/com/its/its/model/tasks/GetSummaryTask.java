package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.entity.Summary;
import com.its.its.model.http.CommonRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Chinh Bui on 3/19/2017.
 */

public class GetSummaryTask extends AsyncTask<String, Void, DataReturn> {
    Activity activity;
    TextView txtCoinAmount, txtTotalLose, txtTotalWin;

    public GetSummaryTask(Activity activity, TextView txtCoinAmount, TextView txtTotalLose, TextView txtTotalWin) {
        this.activity = activity;
        this.txtCoinAmount = txtCoinAmount;
        this.txtTotalLose = txtTotalLose;
        this.txtTotalWin = txtTotalWin;
    }

    @Override
    protected DataReturn doInBackground(String... params) {
        DataReturn result = new DataReturn();
        String api = params[0];
        String token = params[1];

        try {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Accept", "application/json");
            headers.put("Authorization", token);
            headers.put("Localization", activity.getResources().getString(R.string.localization));

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse(CommonRequest.GET, api, headers, null);

            if(inputStreamReader != null){
                result = new Gson().fromJson(inputStreamReader, DataReturn.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(DataReturn s) {
        super.onPostExecute(s);
        if(s != null){
            switch (s.getStatus()){
                case "200":
                    String data = s.getData().toString();
                    Summary summary = new Gson().fromJson(data, Summary.class);
                    txtCoinAmount.setText(summary.getCoinAmount() + "");;
                    txtTotalLose.setText(summary.getTotalLose() + "");
                    txtTotalWin.setText(summary.getTotalWin() + "");
                    break;
                default:
                    break;
            }
        }
    }
}
