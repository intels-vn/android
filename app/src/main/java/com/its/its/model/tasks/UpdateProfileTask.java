package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.entity.RegisterAndLogin;
import com.its.its.model.entity.User;
import com.its.its.model.http.CommonRequest;
import com.its.its.view.MainActivity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by BiLac on 3/10/2017.
 */

public class UpdateProfileTask extends AsyncTask<String, Void, DataReturn>{
    Activity activity;

    public UpdateProfileTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected DataReturn doInBackground(String... params) {
        DataReturn result = new DataReturn();

        String api = params[0];
        String token = params[1];

        try {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "*/*");
            headers.put("Localization", activity.getResources().getString(R.string.localization));
            headers.put("Authorization", token);

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse(CommonRequest.PUT, api, headers, null);
            result = new Gson().fromJson(inputStreamReader, DataReturn.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(DataReturn s) {
        super.onPostExecute(s);

        String status = s.getStatus();

        switch (status){
            case "200":
                Toast.makeText(activity, "Updated profile successfully", Toast.LENGTH_SHORT).show();
                Log.d("Data: ", s.getMessage());
                break;
            case "400":
                break;
            default:
                break;
        }
    }
}
