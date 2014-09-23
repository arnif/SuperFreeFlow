package ru.andr.SuperFreeFlow;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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



        SharedPreferences settings = getSharedPreferences("Mute", MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();

        final boolean temp = settings.getBoolean("muteStatus", true);

        System.out.println(temp);

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        SharedPreferences settings = getSharedPreferences( "Settings", MODE_PRIVATE );
        SharedPreferences.Editor editor = settings.edit();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_mute:
                editor.putBoolean("muteSettings", !mGlobal.isMuted);
                editor.commit();
                break;
            case R.id.checkbox_vibrate:
                editor.putBoolean("vibrateSettings", !mGlobal.isVibrate);
                editor.commit();
                break;
        }
    }
}