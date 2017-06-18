package es.ulpgc.eii.android.project2.listener;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import es.ulpgc.eii.android.project2.MainActivity;

public class ExitListener implements DialogInterface.OnClickListener {

    private Activity activity;

    public ExitListener(Context context) {
        this.activity = (Activity) context;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
        Intent intent = new Intent(activity.getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        activity.startActivity(intent);
    }

}