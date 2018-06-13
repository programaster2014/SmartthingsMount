package com.programasterapps.smartthingsmount;

import android.view.View;

import com.programasterapps.smartthingsmount.elements.RoutineButton;

public class RoutineButtonListener implements View.OnClickListener {
    private String authorization;


    public RoutineButtonListener(String authorization){
        this.authorization = authorization;
    }
    @Override
    public void onClick(View view) {
        RoutineButton button = (RoutineButton)view;
        SmartthingsCommandAsyncTask task = new SmartthingsCommandAsyncTask(authorization, "execute", button.getID().replace(" ", "%20"), "routine");
        task.execute();
    }
}
