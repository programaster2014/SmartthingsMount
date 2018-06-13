package com.programasterapps.smartthingsmount;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.programasterapps.smartthingsmount.elements.RoutineButton;
import com.programasterapps.smartthingsmount.enums.Action;
import com.programasterapps.smartthingsmount.updates.RoutineUpdates;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private SmartthingsThread smartthingsThread;
    private CommunicationHandler handler;

    private FragmentTransaction ft;
    private FragmentManager fm;

    private LinearLayout routineList;
    private boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_land);

        getSupportActionBar().hide();
        this.handler = new CommunicationHandler(this);

        smartthingsThread = new SmartthingsThread(this, handler);
        new Thread(smartthingsThread).start();

        this.isLandscape = getIsLandscape();

        if(this.isLandscape){
            fm = this.getFragmentManager();
            ft = fm.beginTransaction();

            ft.commit();
        }
        else{

        }

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
        for(String key : routines.keySet()){
            if(routines.get(key).action.equals(Action.Add)){
                RoutineButton routineButton = new RoutineButton(this, routines.get(key).routine.getName());
                routineButton.setText(routines.get(key).routine.getName());
                LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
                routineButton.setLayoutParams(params);

                routineButton.setOnClickListener(new RoutineButtonListener(smartthingsThread.getAuthorization()));
                routineList.addView(routineButton);
            }
            else if(routines.get(key).action.equals(Action.Delete)){
                ArrayList<RoutineButton> delete = new ArrayList<>();
                for(int counter = 0; counter < routineList.getChildCount(); counter++){
                    RoutineButton button = (RoutineButton)routineList.getChildAt(counter);
                    if(button.getID().equals(routines.get(key).routine.getName())){
                        delete.add(button);
                    }
                }

                for(RoutineButton button : delete){
                    routineList.removeView(button);
                }
            }
        }
    }

}
