package com.programasterapps.smartthingsmount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private SmartthingsThread smartthingsThread;
    private CommunicationHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.handler = new CommunicationHandler(this);

        smartthingsThread = new SmartthingsThread(this, handler);
        new Thread(smartthingsThread).start();
    }
}
