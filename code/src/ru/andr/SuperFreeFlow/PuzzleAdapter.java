package ru.andr.SuperFreeFlow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by arnif on 9/23/14.
 */
public class PuzzleAdapter {

    SQLiteDatabase db;
    DbHelper dbHelper;
    Context context;

    public PuzzleAdapter(Context c) {
        context = c;
    }

    public PuzzleAdapter openToRead() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public PuzzleAdapter openToWrite() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public long insertPuzzle(int pid, int size, int best_moves, String best_time) {
        String[] cols = DbHelper.TablePuzzleCols;
        ContentValues contentValues = new ContentValues();
        contentValues.put(cols[1], ((Integer)pid).toString());
        contentValues.put(cols[2], ((Integer)size).toString());
        contentValues.put(cols[3], ((Integer)best_moves).toString());
        contentValues.put(cols[4], best_time);
        openToWrite();
        long value = db.insert(DbHelper.TablePuzzle, null, contentValues);
        close();
        return value;
    }

    public long updatePuzzle(int pid, int size, int best_moves, String best_time) {
        String[] cols = DbHelper.TablePuzzleCols;
        ContentValues contentValues = new ContentValues();
        contentValues.put(cols[1], ((Integer)pid).toString());
        contentValues.put(cols[2], ((Integer)size).toString());
        contentValues.put(cols[3], ((Integer)best_moves).toString());
        contentValues.put(cols[4], best_time);
        openToWrite();
        long value = db.update(DbHelper.TablePuzzle, contentValues, cols[1] + "=" + pid, null);
        close();
        return value;
    }

    public long updatePuzzleBestTime(long id, String best_time) {
        String[] cols = DbHelper.TablePuzzleCols;
        ContentValues contentValues = new ContentValues();
        contentValues.put(cols[4], best_time);
        openToWrite();
        long value = db.update(DbHelper.TablePuzzle, contentValues, cols[0] + "=" + id, null);
        close();
        return value;
    }

    public long updatePuzzleBestMoves(long id, int best_moves) {
        String[] cols = DbHelper.TablePuzzleCols;
        ContentValues contentValues = new ContentValues();
        contentValues.put(cols[3], ((Integer)best_moves).toString());
        openToWrite();
        long value = db.update(DbHelper.TablePuzzle, contentValues, cols[0] + "=" + id, null);
        close();
        return value;
    }

    public Cursor queryPuzzle() {
        openToRead();
        Cursor cursor = db.query(DbHelper.TablePuzzle,
                                 DbHelper.TablePuzzleCols, null, null, null, null, null);
        return cursor;
    }

    public Cursor queryPuzzle(int pid, int size) {
        openToRead();
        String[] cols = DbHelper.TablePuzzleCols;
        Cursor cursor = db.query(DbHelper.TablePuzzle, cols, cols[1] + "=" + pid, null, null, null, null);
        return cursor;
    }

    public void deleteAll() {
        Cursor cursor = queryPuzzle();
        long rowId = cursor.getColumnIndexOrThrow("_id");

        if (cursor.moveToFirst()) {
            do {
                deleteRow(cursor.getLong((int) rowId));
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    public boolean deleteRow(long rowId) {
        return db.delete(DbHelper.TablePuzzle, rowId + "= _id", null) != 0;
    }




}
