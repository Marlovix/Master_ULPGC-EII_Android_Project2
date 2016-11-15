package es.ulpgc.eii.android.project2.listener;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class ExitListener implements DialogInterface.OnClickListener {

    private Activity activity;

    public ExitListener(Context context) {
        this.activity = (Activity) context;
    }

    // The player closes the final dialog and the application after to get the victory //
    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        activity.finish();
    }

}