package ru.andr.SuperFreeFlow;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by sindrisigurjonsson on 23/09/14.
 */
public class AboutActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);


        TextView txt = new TextView(AboutActivity.this);
        txt.setText("Theodór Tómas Theodórsson\nÁrniFannar\nSindri Sigurjónsson");
        txt.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.FILL_PARENT,ActionBar.LayoutParams.WRAP_CONTENT));;
        lin.addView(txt);
    }
}

