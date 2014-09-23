package ru.andr.SuperFreeFlow;

/**
 * Created by arnif on 9/14/14.
 */

public class Coordinate {

    private int m_col;
    private int m_row;
    private int m_color;
    private boolean m_isDot;


    private boolean m_isConnected;

    Coordinate( int col, int row, int color ) {
        m_col = col;
        m_row = row;
        m_color = color;
        m_isDot = false;
        m_isConnected = false;

    }

    public int getCol() {
        return m_col;
    }

    public int getRow() {
        return m_row;
    }

    public int getColor() { return m_color; }

    public  void setColor(int color) {
        this.m_color = color;
    }

    public boolean getIsDot() { return m_isDot; }

    public void setDot(boolean dot) {
        this.m_isDot = dot;
    }

    public boolean isConnected() {
        return m_isConnected;
    }

    public void setIsConnected(boolean m_isConnected) {
        this.m_isConnected = m_isConnected;
    }


    @Override
    public boolean equals( Object other ) {
        if ( !(other instanceof Coordinate) ) {
            return false;
        }
        Coordinate otherCo = (Coordinate) other;
        return otherCo.getCol() == this.getCol()&& otherCo.getRow() == this.getRow();
    }
}
