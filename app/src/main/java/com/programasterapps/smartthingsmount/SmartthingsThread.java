package com.programasterapps.smartthingsmount;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.programasterapps.smartthingsmount.common.DifferenceChecker;
import com.programasterapps.smartthingsmount.devices.Routine;
import com.programasterapps.smartthingsmount.devices.Switch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class SmartthingsThread implements Runnable{
    private static final String base_url = "https://graph-na02-useast1.api.smartthings.com:443/api/smartapps/installations/c5db80eb-1a27-4ae5-80d7-ffb49e892a08/all";
    public static final String ROUTINE = "routine";
    public static final String SWITCH = "switch";

    private boolean firstRun;

    private String json = "";
    private JSONObject smartthingsObject = null;
    private JSONArray  deviceStatus = null;

    private HashMap<String, Switch> switches;
    private HashMap<String, Routine> routines;

    private Context context;
    private CommunicationHandler handler;

    public SmartthingsThread(Context context, CommunicationHandler handler){
        this.context = context;
        this.handler = handler;
        this.firstRun = false;

        switches = new HashMap<>();
        routines = new HashMap<>();

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

                deviceStatus = new JSONArray(builder.toString());
                getSwitches();
                getRoutines();

                Thread.sleep(2000);
                firstRun = true;
            } catch (IOException | InterruptedException | JSONException e) {
                Log.e("SmartthingsThread", e.getMessage());
            }
        }
    }

    protected String getAuthorization(){
        try {
            return smartthingsObject.getString("access_token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getSwitches(){

        try {
            for(int counter = 0; counter < deviceStatus.length(); counter++){
                JSONObject object = (JSONObject) deviceStatus.get(counter);
                if(object.getString("name").equals(SWITCH)) {
                    JSONArray values = (JSONArray) object.getJSONArray("value");
                    HashMap<String, Switch> current = new HashMap<>();

                    for (int value_index = 0; value_index < values.length(); value_index++) {
                        JSONObject json_switch = values.getJSONObject(value_index);

                        Switch theSwitch = new Switch(
                                json_switch.getString("name"),
                                json_switch.getString("id"),
                                json_switch.getString("value")
                        );

                        current.put(theSwitch.getId(), theSwitch);
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("NAME", SWITCH);
                    bundle.putSerializable(SWITCH, DifferenceChecker.getSwitchDifferences(current, switches));
                    Message message = new Message();
                    message.setData(bundle);
                    handler.sendMessage(message);

                    switches = current;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getRoutines(){
        try {
            for(int counter = 0; counter < deviceStatus.length(); counter++){
                JSONObject object = (JSONObject) deviceStatus.get(counter);
                if(object.getString("name").equals(ROUTINE)) {
                    JSONArray values = (JSONArray) object.getJSONArray("value");
                    HashMap<String, Routine> current = new HashMap<>();
                    for (int value_index = 0; value_index < values.length(); value_index++) {
                        JSONObject json_switch = values.getJSONObject(value_index);

                        Routine theRoutine = new Routine(
                                json_switch.getString("name")
                        );

                        current.put(theRoutine.getName(), theRoutine);
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("NAME", ROUTINE);
                    bundle.putSerializable(ROUTINE, DifferenceChecker.getRoutineDifferences(current, routines));
                    Message message = new Message();
                    message.setData(bundle);
                    handler.sendMessage(message);

                    routines = current;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void checkForRoutineDifferences(HashMap<String, Routine> current){

    }


}
