package com.programasterapps.smartthingsmount;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.programasterapps.smartthingsmount.fragments.RoutineFragment;
import com.programasterapps.smartthingsmount.fragments.SwitchesFragment;
import com.programasterapps.smartthingsmount.updates.RoutineUpdates;
import com.programasterapps.smartthingsmount.updates.SwitchUpdates;

import java.util.HashMap;

public class CommunicationHandler extends Handler{

    private MainActivity activity;

    //Fragments
    RoutineFragment routineFragment;
    SwitchesFragment switchesFragment;

    public CommunicationHandler(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        if(this.activity != null && msg != null){
            Bundle bundle = msg.getData();
            if(bundle != null){
                String name = bundle.getString("NAME");
                if(name != null){
                    if(name.equals(SmartthingsThread.SWITCH)){
                        HashMap<String, SwitchUpdates> updates = (HashMap<String, SwitchUpdates>)bundle.getSerializable(SmartthingsThread.SWITCH);
                    }
                    else if(name.equals(SmartthingsThread.ROUTINE)){
                        HashMap<String, RoutineUpdates> updates = (HashMap<String, RoutineUpdates>)bundle.getSerializable(SmartthingsThread.ROUTINE);
                        this.activity.update_routine_list(updates);
                    }
                }
            }
        }
    }


}
