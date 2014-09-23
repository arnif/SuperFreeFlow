package ru.andr.SuperFreeFlow;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnif on 9/16/14.
 */
public class ArcadeSelectBoardActivity extends Activity implements View.OnClickListener {

    private Global mGlobals = Global.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arcade_select_board);


        try {
            List<Challenges> challenges = new ArrayList<Challenges>();
            readPack(getAssets().open("packs/regular.xml"), challenges);
            mGlobals.mChallenges = challenges;

            LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
            layout.setOrientation(LinearLayout.VERTICAL);

            for (Challenges mChallenge : mGlobals.mChallenges) {
                LinearLayout row = new LinearLayout(this);
                row.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                Button btnTag = new Button(this);
                btnTag.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                btnTag.setText(mChallenge.getPuzzleName());
                btnTag.setId(Integer.parseInt(mChallenge.getPuzzleId()));
                btnTag.setOnClickListener(this);
                row.addView(btnTag);
                layout.addView(row);
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(getBaseContext(), LevelOverviewActivity.class);
        i.putExtra("levelId", view.getId());
        startActivity(i);
    }


    private void readPack(InputStream is, List<Challenges> challenges) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse( is );
            NodeList nList = doc.getElementsByTagName( "challenge" );
            for ( int c=0; c<nList.getLength(); ++c ) {
                Node nNode = nList.item(c);
                if ( nNode.getNodeType() == Node.ELEMENT_NODE ) {
                    Element eNode = (Element) nNode;
                    String id = eNode.getAttribute("id");
                    String name = eNode.getAttribute("name");
                    challenges.add( new Challenges( id, name ) );
                }
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}