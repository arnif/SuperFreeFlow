package ru.andr.SuperFreeFlow;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arnif on 9/23/14.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "PUZZLE_DB";
    public static final int DB_VERSION = 1;

    public static final String TablePuzzle = "puzzles";
    public static final String[] TablePuzzleCols = {"_id", "sid", "size", "best_moves", "best_time"};

    private static final String sqlCreateTablePuzzle =
            "CREATE TABLE puzzles(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " sid INTEGER NOT NULL," +
            " size INTEGER NOT NULL," +
            " best_moves INTEGER, " +
            " best_time TEXT " +
            ");";

    private static final String sqlDropTablePuzzles =
            "DROP TABLE IF EXISTS puzzles";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateTablePuzzle);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDropTablePuzzles);
        onCreate(db);
    }

}
