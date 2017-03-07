package com.its.its.model.http;

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
 * Created by BiLac on 3/8/2017.
 */

public class CommonRequest {
    public static HttpURLConnection sendRequest(String method, String api, HashMap<String, String> headers,
                                                HashMap<String, String> bodies) throws IOException {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(api);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);

            //CHECK HEADER, IF NULL NO NEED TO CHECK
            if(!headers.equals(null)) {
                connection.setRequestProperty("Content-Type", headers.get("Content-Type"));
                connection.setRequestProperty("Accept", headers.get("Accept"));
                connection.setRequestProperty("Authorization", headers.get("Authorization"));
                connection.setRequestProperty("Localization", headers.get("Localization"));
            }

            if(!method.equals("GET")){
                connection.setDoOutput(true);

                JSONObject object = new JSONObject(bodies);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(object.toString());
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            connection.connect();
        }

        return connection;
    }

    public static  String receiveResponse(String method, String api, HashMap<String, String> headers,
                                          HashMap<String, String> bodies) throws IOException {
        String result = "";
        HttpURLConnection connection = sendRequest(method, api, headers, bodies);
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while((line = reader.readLine()) != null){
            builder.append(line);
        }

        reader.close();
        inputStream.close();
        result = builder.toString();

        return  result;
    }
}
