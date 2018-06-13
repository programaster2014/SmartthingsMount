package com.programasterapps.smartthingsmount.elements;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class RoutineButton extends AppCompatButton{
    private String ID;

    public RoutineButton(Context context, String id) {
        super(context);
        this.ID = id;
    }

    public String getID() {
        return ID;
    }
}
