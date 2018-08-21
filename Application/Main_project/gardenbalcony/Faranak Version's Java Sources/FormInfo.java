package com.example.fkarimi.mysecondapplication;

import android.app.Activity;
import android.widget.LinearLayout;

/**
 * Created by F.KARIMI on 11/08/2018.
 */

public class FormInfo {
    private Activity activity;
    private LinearLayout LeanLay;
    public FormInfo(Activity activity, int LayoutId)
    {
        this.activity = activity;
        this.LeanLay = (LinearLayout) activity.findViewById(LayoutId);

    }

}
