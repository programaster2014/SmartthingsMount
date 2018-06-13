package com.programasterapps.smartthingsmount.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.programasterapps.smartthingsmount.R;
import com.programasterapps.smartthingsmount.RoutineButtonListener;
import com.programasterapps.smartthingsmount.elements.RoutineButton;
import com.programasterapps.smartthingsmount.enums.Action;
import com.programasterapps.smartthingsmount.updates.RoutineUpdates;

import java.util.ArrayList;
import java.util.HashMap;

public class RoutineFragment extends Fragment {
    private LinearLayout routineList;
    private String authorization;

    public static RoutineFragment createInstance(String authorization){
        RoutineFragment routineFragment = new RoutineFragment();
        routineFragment.setAuthorization(authorization);
        return routineFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.routine_fragment, container, false);
        routineList = (LinearLayout)view.findViewById(R.id.routine_list_linearlayout);
        return view;
    }

    public void setAuthorization(String authorization){
        this.authorization = authorization;
    }

    public void update_routine_list(HashMap<String, RoutineUpdates> routines){
        for(String key : routines.keySet()){
            if(routines.get(key).action.equals(Action.Add)){
                RoutineButton routineButton = new RoutineButton(this.getContext(), routines.get(key).routine.getName());
                routineButton.setText(routines.get(key).routine.getName());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
                routineButton.setLayoutParams(params);

                routineButton.setOnClickListener(new RoutineButtonListener(authorization));
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
