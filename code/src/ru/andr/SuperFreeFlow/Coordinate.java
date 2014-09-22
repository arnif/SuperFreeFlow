package ru.andr.SuperFreeFlow;

/**
 * Created by arnif on 9/14/14.
 */

public class Coordinate {

    private int m_col;
    private int m_row;
    private int m_color;

    Coordinate( int col, int row, int color ) {
        m_col = col;
        m_row = row;
        m_color = color;
    }

    public int getCol() {
        return m_col;
    }

    public int getRow() {
        return m_row;
    }

    public int getColor() { return m_color; }

    @Override
    public boolean equals( Object other ) {
        if ( !(other instanceof Coordinate) ) {
            return false;
        }
        Coordinate otherCo = (Coordinate) other;
        return otherCo.getCol() == this.getCol()&& otherCo.getRow() == this.getRow();
    }
}
