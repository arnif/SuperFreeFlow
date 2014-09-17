package ru.andr.SuperFreeFlow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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

        for(int i = 0; i < mGlobals.mPuzzles.size(); i++){
            values[i] = "Level " + (i + 1);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //Toast.makeText(getBaseContext(), ""+arg2,     Toast.LENGTH_SHORT).show();
                Log.d("DEBUG", "" + arg2);

                //System.out.println(mGlobals.mPuzzles.get(arg2).getFlows());
                Intent i = new Intent(getBaseContext(), PlayActivity.class);
                i.putExtra("puzzleFlows", mGlobals.mPuzzles.get(arg2).getFlows());
                i.putExtra("puzzleSize", mGlobals.mPuzzles.get(arg2).getSize());
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