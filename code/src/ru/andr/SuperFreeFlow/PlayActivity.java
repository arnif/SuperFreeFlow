package ru.andr.SuperFreeFlow;

/**
 * Created by arnif on 9/14/14.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


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

        int color = settings.getInt( "pathColor", Color.CYAN );
        Board board = (Board) findViewById( R.id.board );
        board.setColor( color );

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String puzzleFlow = extras.getString("puzzleFlows");
            int puzzleSize = Integer.parseInt(extras.getString("puzzleSize"));
            int levelId = extras.getInt("levelId");
            int bestMove = extras.getInt("bestMoves");

            board.setBoardSize(puzzleSize);
            board.setFlow(puzzleFlow);
            board.setLevelId(levelId);
            board.setBestMove(bestMove);
            board.createLevel();
            board.initTextView();

            TextView t = (TextView) findViewById(R.id.levelId);
            t.setText("Level " + (levelId + 1));
            t.setTextSize(24);
        }
    }

    public void goHome(View view) {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        startActivity(i);
    }
}