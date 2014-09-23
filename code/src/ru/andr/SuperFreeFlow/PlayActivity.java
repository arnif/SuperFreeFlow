package ru.andr.SuperFreeFlow;

/**
 * Created by arnif on 9/14/14.
 */
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;


public class PlayActivity extends Activity {


    private Global mGlobals = Global.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        SharedPreferences settings = getSharedPreferences( "ColorPref", MODE_PRIVATE );
        SharedPreferences test = getSharedPreferences("Settings", MODE_PRIVATE);

        mGlobals.isMuted = test.getBoolean("muteSettings", true);
        mGlobals.isVibrate = test.getBoolean("vibrateSettings", true);

        System.out.println("mute " + mGlobals.isMuted);
        System.out.println("vib " + mGlobals.isVibrate);

        int color = settings.getInt( "pathColor", Color.CYAN );
        Board board = (Board) findViewById( R.id.board );
        board.setColor( color );

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String puzzleFlow = extras.getString("puzzleFlows");
            int puzzleSize = Integer.parseInt(extras.getString("puzzleSize"));
            System.out.println(puzzleFlow);
            board.setBoardSize(puzzleSize);
            board.setFlow(puzzleFlow);
            board.createLevel();
        }
    }
}