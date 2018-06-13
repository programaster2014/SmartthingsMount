package com.programasterapps.smartthingsmount.common;

import com.programasterapps.smartthingsmount.enums.Action;
import com.programasterapps.smartthingsmount.updates.RoutineUpdates;
import com.programasterapps.smartthingsmount.updates.SwitchUpdates;
import com.programasterapps.smartthingsmount.devices.Routine;
import com.programasterapps.smartthingsmount.devices.Switch;

import java.util.HashMap;

public class DifferenceChecker {

    public static HashMap<String, SwitchUpdates> getSwitchDifferences(HashMap<String, Switch> current, HashMap<String, Switch> previous){
        HashMap<String, SwitchUpdates> updates = new HashMap<>();

        for(String key : current.keySet()){
            if(!previous.containsKey(key)){
                updates.put(key, new SwitchUpdates(key, current.get(key), Action.Add));
            }
            else{
                if(!current.get(key).getValue().equals(previous.get(key).getValue())){

                    updates.put(key, new SwitchUpdates(key, current.get(key), Action.SwitchChanged));
                }
            }
        }

        for(String key : previous.keySet()){
            if(!current.containsKey(key)){
                updates.put(key, new SwitchUpdates(key, previous.get(key), Action.Delete));
            }
        }

        return updates;
    }

    public static HashMap<String, RoutineUpdates> getRoutineDifferences(HashMap<String, Routine> current, HashMap<String, Routine> previous){
        HashMap<String, RoutineUpdates> updates = new HashMap<>();

        for(String key : current.keySet()){
            if(!previous.containsKey(key)){
                updates.put(key, new RoutineUpdates(current.get(key), Action.Add));
            }
        }

        for(String key : previous.keySet()){
            if(!current.containsKey(key)){
                updates.put(key, new RoutineUpdates(previous.get(key), Action.Delete));
            }
        }

        return updates;
    }

    public static HashMap<String, Routine> getDeepCopyRoutines(HashMap<String, Routine> current){
        HashMap<String, Routine> temp = new HashMap<>();
        for(String key : current.keySet()){
            Routine routine = new Routine(key);
            temp.put(key, routine);
        }
        return temp;
    }

}
