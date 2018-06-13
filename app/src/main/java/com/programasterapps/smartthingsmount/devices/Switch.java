package com.programasterapps.smartthingsmount.devices;

public class Switch extends Device{
    private String value;

    public Switch(String name, String id, String value){
        super(name, id);
        this.value = value;
    }

    public static Switch getInstance(String name, String id, String value){
        return new Switch(name, id, value);
    }

    public static Switch getInstance(Switch aSwitch){
        return new Switch(new String(aSwitch.name), new String(aSwitch.id), new String(aSwitch.value));
    }

    public void setValue(String status){
        this.value = status;
    }
    public String getValue(){
        return this.value;
    }
    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
}
