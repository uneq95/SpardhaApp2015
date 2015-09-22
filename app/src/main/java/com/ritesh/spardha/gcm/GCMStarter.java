package com.ritesh.spardha.gcm;

import android.content.Context;
import android.content.Intent;

/**
 * Created by ritesh_kumar on 23-Sep-15.
 */
public class GCMStarter {

    Context context;

    public GCMStarter(Context context){
        this.context=context;

    }
    public void GCMEnable(){
        context.startService(new Intent(context, RegistrationIntentService.class));
    }
}
