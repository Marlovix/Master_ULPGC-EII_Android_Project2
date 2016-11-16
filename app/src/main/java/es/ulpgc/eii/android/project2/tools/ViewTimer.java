package es.ulpgc.eii.android.project2.tools;

import android.app.Activity;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class ViewTimer {

    // Disable a view for the seconds passed as parameters (OnClickListener off) //
    public static void disableViewForTime(View view, float timeSeconds) {
        view.setEnabled(false);
        final View v = view;
        Timer viewTimer = new Timer();
        viewTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                ((Activity) v.getContext()).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        v.setEnabled(true);
                    }
                });
            }
        }, (int) (timeSeconds * 1000)); // Milliseconds are required //
    }

}
