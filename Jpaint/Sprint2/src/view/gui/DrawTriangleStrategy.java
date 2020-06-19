package view.gui;

import controller.Point;
import model.shapeConfig;
import model.ShapeShadingType;
import model.dialogs.ColorSingleton;
import view.interfaces.IDrawShape;

import java.awt.*;

public class DrawTriangleStrategy implements IDrawShape
{
// create variable
    private shapeConfig shapeConfig;
    private ShapeShadingType shapeShadingType;
    private Color primaryColor;
    private Color secondaryColor;
    private int width;
    private int height;
    private Point adjustStart;
    private Point adjustEnd;
    private Point startValuePoint;
    private int[] x = new int[3];
    private int[] y = new int[3];

// create triangle shape
    public DrawTriangleStrategy(shapeConfig shapeConfig) 
    {
        this.shapeConfig = shapeConfig;
        this.shapeShadingType = shapeConfig.getShadingType();
        this.primaryColor = ColorSingleton.getColor(shapeConfig.getPrimaryColor());
        this.secondaryColor = ColorSingleton.getColor(shapeConfig.getSecondaryColor());
        this.adjustStart = shapeConfig.getadjustStart();
        this.adjustEnd = shapeConfig.getadjustEnd();
        this.startValuePoint = shapeConfig.getstartValuePoint();
        this.x[0] = shapeConfig.getadjustStart().getX();
        this.x[1] = shapeConfig.getadjustEnd().getX();
        this.x[2] = shapeConfig.getadjustStart().getX();

        this.y[0] = shapeConfig.getadjustStart().getY();
        this.y[1] = shapeConfig.getadjustEnd().getY();
        this.y[2] = shapeConfig.getadjustEnd().getY();
    }

// paint function for shading type
    public void paint(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;
        // outline
        if (shapeShadingType.equals(ShapeShadingType.OUTLINE)) 
        {
            g.setColor(primaryColor);
            g2.setStroke(new BasicStroke(8));
            g.drawPolygon(x, y, 3);

        } 
     // filledin
        else if (shapeShadingType.equals(ShapeShadingType.FILLED_IN)) 
        {
          
            g.setColor(secondaryColor);
            g.fillPolygon(x, y, 3);

        } 
        // outline and filled in
        else if (shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) 
        {
            g.setColor(primaryColor);
            g2.setStroke(new BasicStroke(8));
            g.drawPolygon(x, y, 3);
            g.setColor(secondaryColor);
            g.fillPolygon(x, y, 3);
        }
    }
// set the area using math function
    double area(int x1, int y1, int x2, int y2, int x3, int y3) 
    {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }

    
    boolean isInside(int x1, int y1, int x2, int y2, int x3, int y3, int x, int y) {

        double A = area(x1, y1, x2, y2, x3, y3);
        double A1 = area(x, y, x2, y2, x3, y3);
        double A2 = area(x1, y1, x, y, x3, y3);
        double A3 = area(x1, y1, x2, y2, x, y);

       
        return (A == A1 + A2 + A3);
    }


    public boolean contains(Point startValuePoint) 
    {
        if (isInside(x[0], y[0], x[1], y[1], x[2], y[2], startValuePoint.getX(), startValuePoint.getY())) {
            return true;
        } else {
            return false;
        }
    }
// get point
    public Point getstartValuePoint()
    {
        return startValuePoint;
    }

    public Point getendValuePoint() 
    {
        return adjustEnd;
    }
// adjust point
    @Override
    public void setadjustStart(Point adjustStart)
    {
        this.adjustStart = adjustStart;
    }

    @Override
    public void setadjustEnd(Point adjustEnd) 
    {
        this.adjustEnd = adjustEnd;
    }

    public Point getadjustStart() 
    {
        return adjustStart;
    }

    @Override
    public Point getadjustEnd() 
    {
        return adjustEnd;
    }
// add x and y value
    @Override
    public void addX(int dx)
    {
        this.x[0] = adjustStart.getX() + dx;
        this.x[1] = adjustEnd.getX() + dx;
        this.x[2] = adjustStart.getX() + dx;
    }

    @Override
    public void addY(int dy)
    {
        this.y[0] = adjustStart.getY() + dy;
        this.y[1] = adjustEnd.getY() + dy;
        this.y[2] = adjustEnd.getY() + dy;
    }

    public shapeConfig getshapeConfig()
    {
        return shapeConfig;
    }
// get width and height value
    public int getWidth()
    {
        return width;
    }

    public int getHeight() 
    {
        return height;
    }
// set shape ShadingType
    public void setShapeShadingType(ShapeShadingType shapeShadingType) 
    {
        this.shapeShadingType = shapeShadingType;
    }
// setPrimaryColor
    public void setPrimaryColor(Color primaryColor)
    {
        this.primaryColor = primaryColor;
    }
// setPrimaryColor
    public void setSecondaryColor(Color secondaryColor)
    {
        this.secondaryColor = secondaryColor;
    }
}



