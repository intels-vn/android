package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.entity.Vip;
import com.its.its.model.http.CommonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Chinh Bui on 3/19/2017.
 */

public class GetVipTask extends AsyncTask<String, Void, DataReturn> {
    Activity activity;

    public GetVipTask(Activity activity) {
        this.activity = activity;
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
            headers.put("Accept", "application/json");

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
                    Vip vip = new Gson().fromJson(data, Vip.class);
                    break;
                default:
                    break;
            }
        }
    }
}
