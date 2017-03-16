package com.its.its.model.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.entity.RegisterAndLogin;
import com.its.its.model.http.CommonRequest;
import com.its.its.view.MainActivity;
import com.its.its.view.RegisterActivity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by BiLac on 3/10/2017.
 */

public class LoginTask extends AsyncTask<String, Void, String>{

    private Activity activity;

    public LoginTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";

        String url = params[0];
        String username = params[1];
        String password = params[2];

        String api = url + "?username=" + username + "&password=" + password;

        try {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Localization", activity.getResources().getString(R.string.localization));

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse(CommonRequest.POST, api, headers, null);

            DataReturn dataReturn = new Gson().fromJson(inputStreamReader, DataReturn.class);
            String status = dataReturn.getStatus();

            switch (status){
                case "200":
                    String data = dataReturn.getData().toString();
                    RegisterAndLogin registerAndLogin = new Gson().fromJson(data, RegisterAndLogin.class);
                    Intent intent = new Intent(activity, MainActivity.class);

                    intent.putExtra("ID", registerAndLogin.getId());
                    intent.putExtra("TOKEN", registerAndLogin.getToken());

                    activity.startActivity(intent);
                    break;

                case "404":
//                    Toast.makeText(activity, activity.getResources().getString(R.string.login_error), Toast.LENGTH_SHORT).show();
                    break;

                case "500":
//                    Toast.makeText(activity, activity.getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error:", e.getMessage());
        }

        return result;
    }
}
