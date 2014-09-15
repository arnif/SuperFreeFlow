package ru.andr.SuperFreeFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnif on 9/14/14.
 */
public class Cellpath {

    private ArrayList<Coordinate> m_path = new ArrayList<Coordinate>();
    private int m_color;


    public void append( Coordinate co ) {
        int idx = m_path.indexOf(  co );
        if ( idx >= 0 ) {
            for ( int i=m_path.size()-1; i > idx; --i ) {
                m_path.remove(i);
            }
        }
        else {
            m_path.add(co);
        }
    }

    public List<Coordinate> getCoordinates() {
        return m_path;
    }

    public void setColor(int color) {
        m_color = color;
    }

    public int getColor() {
        return m_color;
    }

    public void reset() {
        m_path.clear();
    }

    public boolean isEmpty() {
        return m_path.isEmpty();
    }
}