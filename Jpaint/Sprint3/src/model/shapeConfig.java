package model;

import controller.Point;
import view.interfaces.IDrawShape;

public class shapeConfig
{
// create variable
    private shapeColor primaryColor;
    private shapeColor secondaryColor;
    private Point startValuePoint;
    private Point endValuePoint;
    private ShapeShadingType shadingType;
    private ShapeType shapeType;
    private IDrawShape selectedShape;
    private Point adjustStart;
    private Point adjustEnd;
    private int width;
    private int height;
// set colors primary and secondary
    public void setPrimaryColor(shapeColor primaryColor)
    {
        this.primaryColor = primaryColor;
    }

    public void setSecondaryColor(shapeColor secondaryColor)
    {
        this.secondaryColor = secondaryColor;
    }

   // set shape

    public void setShapeType(ShapeType shapeType)
    {
        this.shapeType = shapeType;
    }

    public shapeColor getPrimaryColor()
    {
        return primaryColor;
    }

    public shapeColor getSecondaryColor()
    {
        return secondaryColor;
    }
// get the point
    public Point getstartValuePoint() 
    {
        return startValuePoint;
    }
    public void setstartValuePoint(Point startValuePoint)
    {
        this.startValuePoint = startValuePoint;
    }

    public void setendValuePoint(Point endValuePoint)
    {
        this.endValuePoint = endValuePoint;
    }
// set shading type
    public void setShadingType(ShapeShadingType shadingType) 
    {
        this.shadingType = shadingType;
    }
    public Point getendValuePoint() 
    {
        return endValuePoint;
    }

    public ShapeShadingType getShadingType() 
    {
        return shadingType;
    }
// shape type
    public ShapeType getShapeType() 
    {
        return shapeType;
    }

// set width and height
    public void setWidth(int width) 
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
// get width and height
    public int getWidth() 
    {
        Point adjustStart = getadjustStart();
        Point adjustEnd = getadjustEnd();
        return adjustEnd.getX() - adjustStart.getX();
    }

    public int getHeight()
    {
        Point adjustStart = getadjustStart();
        Point adjustEnd = getadjustEnd();
        return adjustEnd.getY() - adjustStart.getY();
    }
// get and set started point
    public Point getadjustStart()
    {
        int startX = Math.min(startValuePoint.getX(), endValuePoint.getX());
        int startY = Math.min(startValuePoint.getY(), endValuePoint.getY());
        return new Point(startX, startY);
    }

    public Point getadjustEnd()
    {
        int endX = Math.max(startValuePoint.getX(), endValuePoint.getX());
        int endY = Math.max(startValuePoint.getY(), endValuePoint.getY());
        return new Point(endX, endY);
    }

    public void setadjustEnd(Point adjustEnd)
    {
        this.adjustEnd = adjustEnd;
    }

    public void setadjustStart(Point adjustStart) 
    {
        this.adjustStart = adjustStart;
    }

// set and get shape
    public void setSelectedShape(IDrawShape shape)
    {
        this.selectedShape = shape;

    }

    public IDrawShape getSelectedShape() 
    {
        return selectedShape;
    }
}
