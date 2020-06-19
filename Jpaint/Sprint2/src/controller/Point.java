package controller;
// point class used to mouse pointing
public class Point 
{
    private int x;
    private int y;
    private Point point;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
 // get point value
    public Point getPoint()
    {
        return point;
    }
// set y value
    public void setY(int y) 
    {
        this.y = y;
    }
// get x value
    public int getX()
    {
        return x;
    }
// set x value
    public void setX(int x)
    {
        this.x = x;
    }
// get y value
    public int getY()
    {
        return y;
    }

}

