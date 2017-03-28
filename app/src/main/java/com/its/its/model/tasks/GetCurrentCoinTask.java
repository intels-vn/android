package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.Coin;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.http.CommonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Chinh Bui on 3/19/2017.
 */

public class GetCurrentCoinTask extends AsyncTask<String, Void, DataReturn> {
    Activity activity;
    TextView txtMoney;

    public GetCurrentCoinTask(Activity activity, TextView txtMoney) {
        this.activity = activity;
        this.txtMoney = txtMoney;
    }

    @Override
    protected DataReturn doInBackground(String... params) {
        DataReturn result = new DataReturn();

        String api = params[0];
        String token = params[1];

        try{
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Localization", activity.getResources().getString(R.string.localization));
            headers.put("Authorization", token);
            headers.put("Accept", "application/json");

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse(CommonRequest.GET, api, headers, null);

            if(inputStreamReader != null){
                result = new Gson().fromJson(inputStreamReader, DataReturn.class);
            }
        } catch (IOException e){
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
                    Coin currentCoin = new Gson().fromJson(data, Coin.class);
                    txtMoney.setText(currentCoin.getCurrent_coin() + "");
                    break;
                default:
                    break;
            }
        }
    }
}
