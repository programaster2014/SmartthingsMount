package com.programasterapps.smartthingsmount;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class SmartthingsCommandAsyncTask extends AsyncTask<Void, Void, Void>{
    private String base_url = "https://graph-na02-useast1.api.smartthings.com:443/api/smartapps/installations/c5db80eb-1a27-4ae5-80d7-ffb49e892a08/";
    private String authorization;
    private String command;
    private String type;
    private String name;


    public SmartthingsCommandAsyncTask(String authorization, String command, String name, String type){
        this.authorization = authorization;
        this.command = command;
        this.type = type;
        this.name = name;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String concat = base_url + type + "/" + name + "/" + command;

            URL url = new URL(concat);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + authorization);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
