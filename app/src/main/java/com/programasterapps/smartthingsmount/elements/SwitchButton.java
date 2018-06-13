package com.programasterapps.smartthingsmount.elements;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class SwitchButton extends AppCompatButton {
    private String ID;
    private String status;

    public SwitchButton(Context context, String ID, String status){
        super(context);
        this.ID = ID;
        this.status = status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public boolean getIsOn(){
        if(this.status.equals("on")){
            return true;
        }
        return false;
    }
}
