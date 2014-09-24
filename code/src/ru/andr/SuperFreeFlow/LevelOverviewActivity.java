package ru.andr.SuperFreeFlow;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnif on 9/16/14.
 */
public class LevelOverviewActivity extends Activity {

    private Global mGlobals = Global.getInstance();
    private ArrayList<Integer> bestMovesArr = new ArrayList<Integer>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_overview);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            try {
                int levelId = extras.getInt("levelId");
                List<Puzzle> puzzles = new ArrayList<Puzzle>();
                readPack(getAssets().open("packs/regular.xml"), puzzles, levelId);
                mGlobals.mPuzzles = puzzles;
                createFlowList();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void createFlowList() {
        //thx http://stackoverflow.com/questions/10989390/creating-listview-programmatically
        // ✔

        LinearLayout ll = new LinearLayout(this);
        ListView lv = new ListView(this);

        int size = mGlobals.mPuzzles.size();

        String[] values = new String[size];

        ArrayList<Integer> doneLevels = new ArrayList<Integer>();

        for (int i = 0; i < size; i++) {
            bestMovesArr.add(0);
        }

        PuzzleAdapter mSA = new PuzzleAdapter(this);

        SimpleCursorAdapter mCA;
        Cursor mCursor;

        mCursor = mSA.queryPuzzle();
        String cols[] = DbHelper.TablePuzzleCols;
        String from[] = { cols[1], cols[2], cols[3], cols[4] };
        startManagingCursor(mCursor);


        if (mCursor.moveToFirst()) {

            do {
                int id = mCursor.getInt(0);
                int sid = mCursor.getInt(1);
                int boardSize = mCursor.getInt(2);
                int bestMoves = mCursor.getInt(3);
                String bestTime = mCursor.getString(4);

                if (boardSize == Integer.parseInt(mGlobals.mPuzzles.get(0).getSize())) {
                    doneLevels.add(sid);
                    bestMovesArr.set(sid, bestMoves);
                }

            } while (mCursor.moveToNext());

        }
        //mCursor.close();

        mGlobals.bestMovesGlobal = bestMovesArr;

        for(int i = 0; i < mGlobals.mPuzzles.size(); i++){
            if (doneLevels.contains(i)) {
                int numberOfFlows = mGlobals.getNumberOfFlows(mGlobals.mPuzzles.get(i).getFlows());
                if (mGlobals.bestMovesGlobal.get(i) == numberOfFlows) {
                    values[i] = "★ Level " + (i + 1) + "                    Best: " + mGlobals.bestMovesGlobal.get(i);
                } else {
                    values[i] = "✔ Level " + (i + 1) + "                    Best: " + mGlobals.bestMovesGlobal.get(i);
                }

            } else {
                values[i] = "Level " + (i + 1);
            }

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                Intent i = new Intent(getBaseContext(), PlayActivity.class);
                i.putExtra("puzzleFlows", mGlobals.mPuzzles.get(arg2).getFlows());
                i.putExtra("puzzleSize", mGlobals.mPuzzles.get(arg2).getSize());
                if (!bestMovesArr.isEmpty()) {
                    i.putExtra("bestMoves", bestMovesArr.get(arg2));
                } else {
                    i.putExtra("bestMoves", 0);
                }
                i.putExtra("levelId", arg2);
                startActivity(i);

            }

        });

        ll.addView(lv);
        setContentView(ll);

    }




    private void readPack(InputStream is, List<Puzzle> puzzles, int levelId) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse( is );
            NodeList nList = doc.getElementsByTagName( "puzzle" );
            for ( int c=0; c<nList.getLength(); ++c ) {
                Node nNode = nList.item(c);
                if ( nNode.getNodeType() == Node.ELEMENT_NODE ) {
                    Element eNode = (Element) nNode;

                    //String id = eNode.getAttribute("id");
                    //String name = eNode.getAttribute("name");
                    String size = eNode.getElementsByTagName( "size" ).item(0).getFirstChild().getNodeValue();
                    String flows = eNode.getElementsByTagName( "flows" ).item(0).getFirstChild().getNodeValue();
                    if (String.valueOf(levelId).equals("1") && size.equals("5")) {
                        puzzles.add( new Puzzle( size, flows ) );
                    }
                    if (String.valueOf(levelId).equals("2") && size.equals("6")) {
                        puzzles.add( new Puzzle( size, flows ) );
                    }

                }
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}