package com.its.its.model.http;

import android.util.Log;

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
import java.util.Map;

/**
 * Created by BiLac on 3/8/2017.
 */

public class CommonRequest {

    public static final String link = "http://192.168.100.19:8080/backend/";
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    //Open a connection and connect to server.
    public static HttpURLConnection sendRequest(String method, String api, Map<String, String> headers,
                                                Map<String, String> bodies) throws IOException {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(api);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);

            headerValidation(connection, method, headers, bodies);

//            for(String key : headers.keySet()){
//                if(key.equals("Content-Type")){
//                    connection.setRequestProperty("Content-Type", headers.get("Content-Type"));
////                    break;
//                }
//
//                if(key.equals("Accept")){
//                    connection.setRequestProperty("Accept", headers.get("Accept"));
////                    break;
//                }
//
//                if(key.equals("Authorization")){
//                    connection.setRequestProperty("Authorization", headers.get("Authorization"));
////                    break;
//                }
//
//                if(key.equals("Localization")){
//                    connection.setRequestProperty("Localization", headers.get("Localization"));
////                    break;
//                }
//
//                if(key.equals("DeviceId")) {
//                    connection.setRequestProperty("DeviceId", headers.get("DeviceId"));
////                    break;
//                }
//            }
//
//            if(!method.equals(GET) && bodies != null){
//                connection.setDoOutput(true);
//
//                JSONObject object = new JSONObject(bodies);
//                OutputStream outputStream = connection.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                bufferedWriter.write(object.toString());
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();
//            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }

    //Validate the headers and methods.
    private static void headerValidation(HttpURLConnection connection, String method, Map<String, String> headers,
                                         Map<String, String> bodies) {
        try {
            for (String key : headers.keySet()) {
                if (key.equals("Content-Type")) {
                    connection.setRequestProperty("Content-Type", headers.get("Content-Type"));
//                    break;
                }

                if (key.equals("Accept")) {
                    connection.setRequestProperty("Accept", headers.get("Accept"));
//                    break;
                }

                if (key.equals("Authorization")) {
                    connection.setRequestProperty("Authorization", headers.get("Authorization"));
//                    break;
                }

                if (key.equals("Localization")) {
                    connection.setRequestProperty("Localization", headers.get("Localization"));
//                    break;
                }

                if (key.equals("DeviceId")) {
                    connection.setRequestProperty("DeviceId", headers.get("DeviceId"));
//                    break;
                }
            }

            if (!method.equals(GET) && bodies != null) {
                connection.setDoOutput(true);

                JSONObject object = new JSONObject(bodies);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(object.toString());
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InputStreamReader receiveResponse(String method, String api, HashMap<String, String> headers,
                                          HashMap<String, String> bodies) throws IOException {
        InputStreamReader result = null;
        HttpURLConnection connection = sendRequest(method, api, headers, bodies);
        connection.connect();
        int responseCode = connection.getResponseCode();
        Log.d("Response Code ", responseCode + "");
        switch (responseCode){
            case 200:
                InputStream inputStream = connection.getInputStream();
                result = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                break;

            default:
                break;
        }
        return result;
    }
}
