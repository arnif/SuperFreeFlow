package ru.andr.SuperFreeFlow;


import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnif on 9/14/14.
 */

public class Board extends View {

    private final int NUM_CELLS = 5;
    private int m_cellWidth;
    private int m_cellHeight;

    private Rect m_rect = new Rect();
    private Paint m_paintGrid  = new Paint();
    private Paint m_paintPath  = new Paint();
    private Path m_path = new Path();
    ShapeDrawable m_shape = new ShapeDrawable(new OvalShape());
    private String level1 = "r.b..g..r........y..yg..b";

    private Cellpath m_cellPath = new Cellpath();

    private ArrayList<Cellpath> cellpathArrayList = new ArrayList<Cellpath>();

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

        m_paintGrid.setStyle( Paint.Style.STROKE );
        m_paintGrid.setColor( Color.GRAY );

        m_paintPath.setStyle( Paint.Style.STROKE );
        m_paintPath.setColor(Color.GREEN);
        m_paintPath.setStrokeWidth(32);
        m_paintPath.setStrokeCap( Paint.Cap.ROUND );
        m_paintPath.setStrokeJoin( Paint.Join.ROUND );
        m_paintPath.setAntiAlias( true );
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
                char ch = getBoard(c, r);

                if (ch == 'b') {
                    m_shape.getPaint().setColor(Color.BLUE);
                    m_shape.draw(canvas);
                } else if (ch == 'r') {
                    m_shape.getPaint().setColor(Color.RED);
                    m_shape.draw(canvas);
                } else if (ch == 'y') {
                    m_shape.getPaint().setColor(Color.YELLOW);
                    m_shape.draw(canvas);
                } else if (ch == 'g') {
                    m_shape.getPaint().setColor(Color.GREEN);
                    m_shape.draw(canvas);
                }

            }
        }
        m_path.reset();

        if ( !cellpathArrayList.isEmpty() ) {

            for (Cellpath cellpath : cellpathArrayList) {

                List<Coordinate> colist = cellpath.getCoordinates();
                Coordinate co = colist.get(0);
                m_path.moveTo(colToX(co.getCol()) + m_cellWidth / 2,
                        rowToY(co.getRow()) + m_cellHeight / 2);

                for (Coordinate aColist : colist) {
                    co = aColist;
                    m_path.lineTo(colToX(co.getCol()) + m_cellWidth / 2,
                            rowToY(co.getRow()) + m_cellHeight / 2);
                }
            }

        }
        canvas.drawPath( m_path, m_paintPath);
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
            //m_path.reset();
            //m_path.moveTo( colToX(c) + m_cellWidth / 2, rowToY(r) + m_cellHeight / 2 );


            char ch = getBoard(c, r);

            if (ch == 'b') {
                m_cellPath = new Cellpath();
                m_paintPath.setColor(Color.BLUE);

                m_cellPath.append( new Coordinate(c,r) );
            } else if (ch == 'r') {
                m_cellPath = new Cellpath();
                m_paintPath.setColor(Color.RED);

                m_cellPath.append( new Coordinate(c,r) );
            } else if (ch == 'y') {
                m_cellPath = new Cellpath();
                m_paintPath.setColor(Color.YELLOW);

                m_cellPath.append( new Coordinate(c,r) );
            } else if (ch == 'g') {
                m_cellPath = new Cellpath();
                m_paintPath.setColor(Color.GREEN);

                m_cellPath.append( new Coordinate(c,r) );
            }
        }
        else if ( event.getAction() == MotionEvent.ACTION_MOVE ) {


            //m_path.lineTo( colToX(c) + m_cellWidth / 2, rowToY(r) + m_cellHeight / 2 );
            if ( !m_cellPath.isEmpty() ) {
                List<Coordinate> coordinateList = m_cellPath.getCoordinates();
                Coordinate last = coordinateList.get(coordinateList.size()-1);
                if ( areNeighbours(last.getCol(),last.getRow(), c, r)) {
                    m_cellPath.append(new Coordinate(c, r));

                    invalidate();
                }
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            System.out.println("Finger off!");
            //save current board.
            m_cellPath.setColor(m_paintPath.getColor());
            cellpathArrayList.add(m_cellPath);

        }
        return true;
    }

    public void setColor( int color ) {
        m_paintPath.setColor( color );
        invalidate();
    }

    public char getBoard(int col, int row) {
        return level1.charAt(col + row * NUM_CELLS);
    }
}