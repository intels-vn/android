package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.entity.User;
import com.its.its.model.http.CommonRequest;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
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
        Toast.makeText(activity, s, Toast.LENGTH_LONG).show();
        Log.d("DATA: ", s);
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
            headers.put("Localization", "vi");

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse(CommonRequest.POST, api, headers, null);

            DataReturn dataReturn = new Gson().fromJson(inputStreamReader, DataReturn.class);

            if(dataReturn.getStatus().equals("200")){

            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error:", e.getMessage());
        }

        return result;
    }
}
