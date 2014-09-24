package ru.andr.SuperFreeFlow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by sindrisigurjonsson on 23/09/14.
 */
public class SettingsActivity extends Activity {

    private Global mGlobal = Global.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        CheckBox muteBox = (CheckBox) findViewById(R.id.checkbox_mute);
        muteBox.setChecked(mGlobal.isMuted);

        CheckBox vibrateBox = (CheckBox) findViewById(R.id.checkbox_vibrate);
        vibrateBox.setChecked(mGlobal.isVibrate);

    }

    public void onCheckboxClicked(View view) {

        SharedPreferences settings = getSharedPreferences( "Settings", MODE_PRIVATE );
        SharedPreferences.Editor editor = settings.edit();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_mute:
                editor.putBoolean("muteSettings", !mGlobal.isMuted);
                mGlobal.isMuted = !mGlobal.isMuted;
                editor.commit();
                break;
            case R.id.checkbox_vibrate:
                editor.putBoolean("vibrateSettings", !mGlobal.isVibrate);
                mGlobal.isVibrate = !mGlobal.isVibrate;
                editor.commit();
                break;
        }
    }

    public void clearDb(View view) {

        final SettingsActivity settingsActivity = this;
        //thx http://stackoverflow.com/questions/2257963/how-to-show-a-dialog-to-confirm-that-the-user-wishes-to-exit-an-android-activity
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Reset Progress")
                .setMessage("Are you sure you want to reset your progress?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PuzzleAdapter puzzleAdapter = new PuzzleAdapter(settingsActivity);
                        puzzleAdapter.deleteAll();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}