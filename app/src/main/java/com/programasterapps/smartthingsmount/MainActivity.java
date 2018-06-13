package com.programasterapps.smartthingsmount;


import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.programasterapps.smartthingsmount.elements.RoutineButton;
import com.programasterapps.smartthingsmount.enums.Action;
import com.programasterapps.smartthingsmount.fragments.RoutineFragment;
import com.programasterapps.smartthingsmount.updates.RoutineUpdates;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private SmartthingsThread smartthingsThread;
    private CommunicationHandler handler;

    private FragmentTransaction ft;
    private FragmentManager fm;

    private LinearLayout routineList;
    private RoutineFragment routineFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_land);

        getSupportActionBar().hide();
        this.handler = new CommunicationHandler(this);

        smartthingsThread = new SmartthingsThread(this, handler);
        new Thread(smartthingsThread).start();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        Configuration configuration = getResources().getConfiguration();
        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){

            routineFragment = RoutineFragment.createInstance(smartthingsThread.getAuthorization());
            ft.replace(R.id.routine_list_landscape, routineFragment);
        }

        ft.commit();

        routineList = this.findViewById(R.id.routine_list_linearlayout);
    }

    private boolean getIsLandscape(){
        final int screenOrientation = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
        switch (screenOrientation){
            case Surface.ROTATION_0:
                return false;
            case Surface.ROTATION_90:
                return true;
            case Surface.ROTATION_180:
                return false;
            default:
                return true;
        }
    }

    public void update_routine_list(HashMap<String, RoutineUpdates> routines){
        routineFragment.setAuthorization(smartthingsThread.getAuthorization());
        routineFragment.update_routine_list(routines);
    }

}
