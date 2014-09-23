package ru.andr.SuperFreeFlow;

import java.util.List;

/**
 * Created by arnif on 9/15/14.
 */
public class Global {

    public List<Pack> mPacks;
    public List<Challenges> mChallenges;
    public List<Puzzle> mPuzzles;
    public String selectedPuzzleFlow;
    public boolean isMuted = false;
    public boolean isVibrate = false;
    ///
    private static Global mInstance = new Global();


    public static Global getInstance() {
        return mInstance;
    }

    private Global() {}
}
