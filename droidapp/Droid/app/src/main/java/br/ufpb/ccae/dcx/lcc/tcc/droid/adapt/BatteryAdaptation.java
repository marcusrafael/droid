package br.ufpb.ccae.dcx.lcc.tcc.droid.adapt;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by xavier on 11/22/15.
 */
public class BatteryAdaptation extends BroadcastReceiver implements SelfAdaptable {

    private Context context;
    private static int LOW_BATTERY = 15;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        int currentBatteryValue = intent.getIntExtra("level", -1);
        if (currentBatteryValue <= LOW_BATTERY) {
            adaptDown();
        } else {
            adaptUp();
        }

        Toast.makeText(context, "BATTERY LEVEL: " + currentBatteryValue + "%", Toast.LENGTH_LONG).show();

    }

    @Override
    public void adaptUp() {
        Activity activity = (Activity) context;
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.screenBrightness = 1.0f;
        activity.getWindow().setAttributes(layoutParams);
    }

    @Override
    public void adaptDown() {
        Activity activity = (Activity) context;
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.screenBrightness = 0.1f;
        activity.getWindow().setAttributes(layoutParams);
    }
}
