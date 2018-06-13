package com.programasterapps.smartthingsmount.updates;

import com.programasterapps.smartthingsmount.enums.Action;
import com.programasterapps.smartthingsmount.devices.Switch;

public class SwitchUpdates {
    public String name;
    public Switch aSwitch;
    public Action action;

    public SwitchUpdates(String name, Switch aSwitch, Action action){
        this.name = new String(name);
        this.aSwitch = Switch.getInstance(aSwitch);
        this.action = action;
    }
}
