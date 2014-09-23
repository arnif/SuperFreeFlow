package ru.andr.SuperFreeFlow;


import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnif on 9/14/14.
 */

public class Board extends View {

    private int NUM_CELLS;
    private int m_cellWidth;
    private int m_cellHeight;
    private String m_flows;
    private int flowCount = 0;

    private Rect m_rect = new Rect();
    private Paint m_paintGrid  = new Paint();
    private Paint m_paintPath  = new Paint();
    private Path m_path = new Path();
    ShapeDrawable m_shape = new ShapeDrawable(new OvalShape());
    private StringBuilder level = new StringBuilder();
    //private String level1 = "r.b..g..r........y..yg..b";
    private int[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.MAGENTA };
    private ArrayList<Coordinate> theLevel = new ArrayList<Coordinate>();

    private Context mContext;
    private Vibrator v;

    private int levelId;

    private Global mGlobals = Global.getInstance();

    private int current_color = Color.BLACK;

    private Cellpath m_cellPath = new Cellpath();

    private Cellpath[] cellpathArrayList;

    private int xToCol( int x ) {
        return (x - getPaddingLeft()) / m_cellWidth;
    }

    private int yToRow( int y ) {
        return (y - getPaddingTop()) / m_cellHeight;
    }

    private int colToX( int col ) {
        return col * m_cellWidth + getPaddingLeft() ;
    }

    private int rowToY( int row ) {
        return row * m_cellHeight + getPaddingTop() ;
    }

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        m_paintGrid.setStyle( Paint.Style.STROKE );
        m_paintGrid.setColor( Color.GRAY );

        m_paintPath.setStyle( Paint.Style.STROKE );
        m_paintPath.setColor(Color.GREEN);
        m_paintPath.setStrokeWidth(32);
        m_paintPath.setStrokeCap( Paint.Cap.ROUND );
        m_paintPath.setStrokeJoin( Paint.Join.ROUND );
        m_paintPath.setAntiAlias( true );
        flowCount = 0;

        v = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);


    }

    @Override
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
        super.onMeasure( widthMeasureSpec, heightMeasureSpec );
        int width  = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int size = Math.min(width, height);
        setMeasuredDimension( size + getPaddingLeft() + getPaddingRight(),
                size + getPaddingTop() + getPaddingBottom() );
    }

    @Override
    protected void onSizeChanged( int xNew, int yNew, int xOld, int yOld ) {
        int sw = Math.max(1, (int) m_paintGrid.getStrokeWidth());
        m_cellWidth  = (xNew - getPaddingLeft() - getPaddingRight() - sw) / NUM_CELLS;
        m_cellHeight = (yNew - getPaddingTop() - getPaddingBottom() - sw) / NUM_CELLS;
    }

    @Override
    protected void onDraw( Canvas canvas ) {

        for ( int r=0; r<NUM_CELLS; ++r ) {
            for (int c = 0; c<NUM_CELLS; ++c) {
                int x = colToX( c );
                int y = rowToY( r );
                m_rect.set(x, y, x + m_cellWidth, y + m_cellHeight);
                canvas.drawRect( m_rect, m_paintGrid );

                m_shape.setBounds(m_rect);

                for (Coordinate coordinate : theLevel) {
                    if (c == coordinate.getCol() && r == coordinate.getRow()) {
                        m_shape.getPaint().setColor(coordinate.getColor());
                        m_shape.draw(canvas);
                    }
                }

            }
        }

        if (cellpathArrayList.length != 0) {


            for (int i = 0; i < cellpathArrayList.length; i++) {
                if (!cellpathArrayList[i].isEmpty()) {
                    List<Coordinate> coordinateList = cellpathArrayList[i].getCoordinates();
                    Coordinate co = coordinateList.get(0);
                    int litur = co.getColor();
                    m_paintPath.setColor(litur);

                    m_path.moveTo(colToX(co.getCol()) + m_cellWidth / 2,
                            rowToY(co.getRow()) + m_cellHeight / 2);

                    for (Coordinate coordinate : coordinateList) {
                        m_path.lineTo(colToX(coordinate.getCol()) + m_cellWidth / 2,
                                rowToY(coordinate.getRow()) + m_cellHeight / 2);
                    }
                    canvas.drawPath( m_path, m_paintPath);
                    m_path.reset();
                }

            }


        }


    }

    private boolean areNeighbours( int c1, int r1, int c2, int r2 ) {
        return Math.abs(c1-c2) + Math.abs(r1-r2) == 1;
    }

    @Override
    public boolean onTouchEvent( MotionEvent event ) {

        int x = (int) event.getX();         // NOTE: event.getHistorical... might be needed.
        int y = (int) event.getY();
        int c = xToCol( x );
        int r = yToRow( y );

        if ( c >= NUM_CELLS || r >= NUM_CELLS ) {
            return true;
        }

        if ( event.getAction() == MotionEvent.ACTION_DOWN ) {


            for (Coordinate coordinate : theLevel) {

                if (c == coordinate.getCol() && r == coordinate.getRow()) {
                    if (coordinate.getIsDot()) {

                        current_color = coordinate.getColor();
                        Cellpath cellpath = cellpathArrayList[theLevel.indexOf(coordinate)];
                        cellpath.setColor(current_color);
                        cellpath.append(coordinate);

                    }
                }

            }

            Coordinate board = getBoard(c, r);

           /*
            if (board != null) {

                if (board.getIsDot()) {
                    System.out.println("ITS A DOT!");
                    Coordinate other = null;
                    boolean found = false;

                    for (int i = 0; i < cellpathArrayList.length; i++) {
                        if (cellpathArrayList[i].getCoordinates().get(0).getColor() == current_color) {
                            for (Coordinate coordinate : theLevel) {
                                if (coordinate.getColor() == current_color && !board.equals(coordinate)) {
                                    System.out.println("1x max");
                                    found = true;
                                    other = new Coordinate(coordinate.getCol(), coordinate.getRow(), current_color);
                                    other.setDot(true);
                                    break;

                                }
                            }


                            if(found) {
                                cellpathArrayList[i].reset();

                                cellpathArrayList[i] = new Cellpath();


                                Coordinate tempCord = new Coordinate(c, r, current_color);
                                tempCord.setDot(true);
                                cellpathArrayList[i].append(tempCord);
                                if (other != null) {
                                    //cellpathArrayList[i].append(other);
                                    System.out.println(other.getColor() + " " + other.getIsDot() + " " + other.getCol() + " " + other.getRow() );
                                }

                                invalidate();
                                break;
                            }
                        }

                    }
                }
            }

            */






        }
        else if ( event.getAction() == MotionEvent.ACTION_MOVE ) {

            //m_path.lineTo( colToX(c) + m_cellWidth / 2, rowToY(r) + m_cellHeight / 2 );

            boolean dontConnect = false;

            v.vibrate(10);

            if (cellpathArrayList.length < 0) {
                return true;
            }

            for (int i = 0; i < cellpathArrayList.length; i++) {

                if (!cellpathArrayList[i].isEmpty() && cellpathArrayList[i].getCoordinates().get(0).getColor() == current_color) {
                    List<Coordinate> coordinateList = cellpathArrayList[i].getCoordinates();
                    Coordinate last = coordinateList.get(coordinateList.size()-1);

                    if ( areNeighbours(last.getCol(),last.getRow(), c, r)) {

                        Coordinate board = getBoard(c, r);
                        if (board == null  || board.getColor() == current_color) {
                            Coordinate co = new Coordinate(c, r, current_color);

                            for (Coordinate coordinate : theLevel) {
                                if (coordinate.getCol() == c && coordinate.getRow() == r && coordinate.getColor() != current_color) {
                                    dontConnect = true;
                                }
                                if (coordinate.getCol() == c && coordinate.getRow() == r && coordinate.getColor() == current_color) {
                                    System.out.println("ITS THERE!!!");
                                    dontConnect = false;
                                    co.setDot(true);
                                    co.setIsConnected(true);


                                    v.vibrate(500);
                                }
                            }

                            if (!dontConnect) {

                                cellpathArrayList[i].append(co);
                                cellpathArrayList[i].setColor(current_color);


                                invalidate();
                            }



                        }


                    }

                }

            }



        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (checkWin()) {
                v.vibrate(1000);
                System.out.println("YOU WON!!!!!!");
                Toast.makeText(getContext(), "You win!", Toast.LENGTH_LONG).show();
                //create nextLevel button
                final Button nextLevelBtn = (Button) getRootView().findViewById(R.id.nextLevelBtn);
                nextLevelBtn.setVisibility(View.VISIBLE);
                if (mGlobals.mPuzzles.size() == getLevelId() + 1) {
                    System.out.println("your done!");
                    nextLevelBtn.setText("Done, go home");
                }
                nextLevelBtn.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        System.out.println("nr of puzzles " + mGlobals.mPuzzles.size());
                        System.out.println("level ID " + getLevelId());

                        if (mGlobals.mPuzzles.size() > getLevelId() + 1) {
                            Intent i = new Intent();
                            i.setClass(mContext, PlayActivity.class);

                            String flows = mGlobals.mPuzzles.get(getLevelId() + 1).getFlows();
                            String size = mGlobals.mPuzzles.get(getLevelId() + 1).getSize();

                            i.putExtra("puzzleFlows", flows);
                            i.putExtra("puzzleSize", size);
                            i.putExtra("levelId", getLevelId() +1);
                            mContext.startActivity(i);
                        } else {


                            Intent i = new Intent();
                            i.setClass(mContext, MainActivity.class);
                            mContext.startActivity(i);

                        }


                    }
                });

            }
            //save current board.
            m_cellPath.setColor(current_color);
            //cellpathArrayList.add(m_cellPath);

        }
        //System.out.println(level.toString());
        return true;
    }


    private char getCharFromColor(int current_color) {
        //private Character[] colors = {'r', 'b', 'y', 'g', 'c', 'm' };
        if (current_color == Color.RED) { return 'R'; }
        if (current_color == Color.BLUE) { return 'B'; }
        if (current_color == Color.YELLOW) { return 'Y'; }
        if (current_color == Color.GREEN) { return 'G'; }
        if (current_color == Color.CYAN) { return 'C'; }
        if (current_color == Color.MAGENTA) { return 'M'; }
        return 0;
    }

    public void setColor( int color ) {
        m_paintPath.setColor( color );
        invalidate();
    }

    public void setFlow(String flows) {
        m_flows = flows;
    }

    public void setBoardSize(int size) {
        NUM_CELLS = size;
    }

    public Coordinate getBoard(int col, int row) {
        for (Cellpath cellpath : cellpathArrayList) {
            for (Coordinate coordinate : cellpath.getCoordinates()) {
                if (coordinate.getCol() == col && coordinate.getRow() == row) {
                    return coordinate;
                }
            }
        }
        return null;
    }

    public int getColorAtCoord(char c) {
        //private Character[] colors = {'r', 'b', 'y', 'g', 'c', 'm' };
        if (Character.toLowerCase(c) == 'r') { return Color.RED; }
        if (Character.toLowerCase(c) == 'b') { return Color.BLUE; }
        if (Character.toLowerCase(c) == 'y') { return Color.YELLOW; }
        if (Character.toLowerCase(c) == 'g') { return Color.GREEN; }
        if (Character.toLowerCase(c) == 'c') { return Color.CYAN; }
        if (Character.toLowerCase(c) == 'm') { return Color.MAGENTA; }
        return 0;
    }


    public void createLevel() {

        flowCount = 0;

        level.setLength(NUM_CELLS * NUM_CELLS);
        int cCount = 0;

        for (int j = 0; j < NUM_CELLS * NUM_CELLS; j++) {
            level.setCharAt(j, '.');
        }

        for(int i = 0; i < m_flows.length(); i++) {

            if (m_flows.charAt(i) == '(') {
                char x1 = m_flows.charAt(i + 1);
                char y1 = m_flows.charAt(i + 3);

                char x2 = m_flows.charAt(i + 5);
                char y2 = m_flows.charAt(i + 7);

                Coordinate co = new Coordinate((Character.getNumericValue(x1)), (Character.getNumericValue(y1)), colors[cCount]);
                co.setDot(true);

                Coordinate co2 = new Coordinate((Character.getNumericValue(x2)), (Character.getNumericValue(y2)), colors[cCount]);
                co2.setDot(true);

                theLevel.add(co);
                theLevel.add(co2);

                cCount++;


            }
        }

        cellpathArrayList = new Cellpath[cCount * 2];


        for (int j = 0; j < cCount * 2; j++) {
            cellpathArrayList[j] = new Cellpath();

        }

    }

    private boolean checkWin() {
        flowCount = theLevel.size() / 2;
        int totalCord = 0;
        for (Cellpath cellpath : cellpathArrayList) {
            for (Coordinate coordinate : cellpath.getCoordinates()) {
                totalCord++;
                if (coordinate.isConnected()) {
                    flowCount--;
                }
            }

        }
        System.out.println("k is " + flowCount);
        System.out.println("total cord " + totalCord);
        System.out.println(NUM_CELLS * NUM_CELLS);

        TextView textView = (TextView) getRootView().findViewById(R.id.flowCount);
        textView.setText("Flows: " + (theLevel.size()/2 - flowCount) + "/" + theLevel.size()/2);

        return flowCount == 0 && totalCord == NUM_CELLS * NUM_CELLS;
    }

    public void initTextView() {
        System.out.println(theLevel.size());
        TextView textView = (TextView) getRootView().findViewById(R.id.flowCount);
        textView.setText("Flows: 0/" + theLevel.size() /2);

        Button nextLevelBtn = (Button) getRootView().findViewById(R.id.nextLevelBtn);
        nextLevelBtn.setVisibility(View.GONE);
    }

    public void setLevelId(int id) {
        levelId = id;
    }

    public int getLevelId() {
        return levelId;
    }


}