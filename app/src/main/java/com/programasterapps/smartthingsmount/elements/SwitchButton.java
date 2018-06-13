package com.programasterapps.smartthingsmount.elements;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class SwitchButton extends AppCompatButton {
    private String ID;
    private boolean isOn;

    public SwitchButton(Context context, String ID, boolean status){
        super(context);
        this.ID = ID;
        this.isOn = status;
    }

    public void setIsOn(boolean status){
        this.isOn = status;
    }

    public boolean getIsOn(){
        return this.isOn;
    }
}
