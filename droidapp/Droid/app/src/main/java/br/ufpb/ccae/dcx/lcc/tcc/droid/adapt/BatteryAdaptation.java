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
public class BatteryAdaptation extends BroadcastReceiver {


    private static final int LOW_BATTERY = 15;
    private static final float BATTERY_LEVEL_HIGH = 1.0f;
    private static final float BATTERY_LEVEL_LOW = 0.1f;

    private Context context;
    private Intent intent;


    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        this.adapt();
    }

    private void increaseScreenBrightnessLevel() {

        changeScreenBrightnessLevel(BATTERY_LEVEL_HIGH);

    }

    private void decreaseScreenBrightnessLevel() {

        changeScreenBrightnessLevel(BATTERY_LEVEL_LOW);

    }

    private void adapt() {

        int batteryLevel = intent.getIntExtra("level", -1);

        if (shouldIncrease(batteryLevel)) {
            decreaseScreenBrightnessLevel();
        } else {
            increaseScreenBrightnessLevel();
        }

    }

    private boolean shouldIncrease(int batteryLevel) {
        return (batteryLevel <= LOW_BATTERY);
    }

    private void changeScreenBrightnessLevel(float level) {
        Activity activity = (Activity) context;
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.screenBrightness = level;
        activity.getWindow().setAttributes(layoutParams);
    }

}
