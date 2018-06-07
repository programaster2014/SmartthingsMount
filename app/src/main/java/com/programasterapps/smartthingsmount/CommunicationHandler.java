package com.programasterapps.smartthingsmount;

import android.os.Handler;
import android.os.Message;

public class CommunicationHandler extends Handler{

    private MainActivity activity;

    public CommunicationHandler(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }


}
