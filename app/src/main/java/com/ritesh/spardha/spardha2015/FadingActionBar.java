package com.ritesh.spardha.spardha2015;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

/**
 * Created by ritesh_kumar on 25-May-15.
 */
public class FadingActionBar extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FadingActionBarHelper helper = new FadingActionBarHelper()
                .actionBarBackground(new ColorDrawable(Color.parseColor("#A901DB")))
                .headerLayout(R.layout.header)
                .contentLayout(R.layout.sport_layout);
        setContentView(helper.createView(this));
        helper.initActionBar(this);
    }
}
