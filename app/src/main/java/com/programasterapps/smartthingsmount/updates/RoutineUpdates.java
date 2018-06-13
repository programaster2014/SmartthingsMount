package com.programasterapps.smartthingsmount.updates;

import com.programasterapps.smartthingsmount.enums.Action;
import com.programasterapps.smartthingsmount.devices.Routine;

public class RoutineUpdates {
    public Routine routine;
    public Action action;

    public RoutineUpdates(Routine routine, Action action){
        this.routine = routine;
        this.action = action;
    }
}
