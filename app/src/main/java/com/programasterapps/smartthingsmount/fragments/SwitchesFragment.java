package com.programasterapps.smartthingsmount.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.programasterapps.smartthingsmount.R;
import com.programasterapps.smartthingsmount.RoutineButtonListener;
import com.programasterapps.smartthingsmount.elements.RoutineButton;
import com.programasterapps.smartthingsmount.elements.SwitchButton;
import com.programasterapps.smartthingsmount.enums.Action;
import com.programasterapps.smartthingsmount.updates.RoutineUpdates;
import com.programasterapps.smartthingsmount.updates.SwitchUpdates;

import java.util.ArrayList;
import java.util.HashMap;

public class SwitchesFragment extends Fragment{
    private String authorization;
    private TableLayout switchList;

    public static SwitchesFragment createInstance(){
        return new SwitchesFragment();
    }

    public void setAuthorization(String authorization){
        this.authorization = authorization;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.switch_fragment, container, false);
        switchList = (TableLayout)view.findViewById(R.id.switch_table);
        return view;
    }

    public void update_switch_list(HashMap<String, SwitchUpdates> switches){
        for(String key : switches.keySet()){
            if(switches.get(key).action.equals(Action.Add)){
                SwitchButton button = new SwitchButton(this.getContext(),switches.get(key).aSwitch.getId(), switches.get(key).aSwitch.getValue());
            }
            else if(switches.get(key).action.equals(Action.Delete)){

            }
        }
    }
}
