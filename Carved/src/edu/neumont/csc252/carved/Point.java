package edu.neumont.csc252.carved;

/**
 * Created by kderousselle on 9/6/14.
 */
public class Point
{
    private int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object obj) {

        boolean equals = false;

        if(obj instanceof Point)
        {
            Point other = (Point) obj;

            if(this.x == other.getX())
            {
                if(this.y == other.getY())
                {
                    equals = true;
                }
            }
        }

        return equals;
    }
}
