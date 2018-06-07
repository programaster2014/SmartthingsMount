package com.programasterapps.smartthingsmount;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SmartthingsThread implements Runnable{
    private static final String base_url = "https://graph-na02-useast1.api.smartthings.com:443/api/smartapps/installations/c5db80eb-1a27-4ae5-80d7-ffb49e892a08/all";

    private String json = "";
    private JSONObject smartthingsObject = null;

    private Context context;
    private CommunicationHandler handler;

    public SmartthingsThread(Context context, CommunicationHandler handler){
        this.context = context;
        this.handler = handler;

        try {
            InputStream is = this.context.getAssets().open("smartthings.json");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            smartthingsObject = new JSONObject(json);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while(true){
            try {
                URL url = new URL(base_url);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Authorization", "Bearer " + smartthingsObject.getString("access_token"));

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String inputLine;
                while((inputLine = reader.readLine()) != null){
                    builder.append(inputLine);
                }
                reader.close();
                System.out.println(builder.toString());

                Thread.sleep(2000);
            } catch (IOException | InterruptedException | JSONException e) {
                Log.e("SmartthingsThread", e.getMessage());
            }
        }
    }


}
