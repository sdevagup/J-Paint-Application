package view.gui;

import controller.Point;
import model.shapeConfig;
import model.ShapeShadingType;
import model.dialogs.ColorSingleton;
import view.interfaces.IDrawShape;

import java.awt.*;

public class DrawEllipseStrategy implements IDrawShape 
{
// variables
    private shapeConfig shapeConfig;
    private ShapeShadingType shapeShadingType;
    private Color primaryColor;
    private Color secondaryColor;
    private int width;
    private int height;
    private Point adjustStart;
    private Point adjustEnd;
    private Point startValuePoint;
// constructor DrawEllipseStrategy

    public DrawEllipseStrategy(shapeConfig shapeConfig) 
    {
        this.shapeConfig = shapeConfig;
        this.shapeShadingType = shapeConfig.getShadingType();
        this.primaryColor = ColorSingleton.getColor(shapeConfig.getPrimaryColor());
        this.secondaryColor = ColorSingleton.getColor(shapeConfig.getSecondaryColor());
        this.width = shapeConfig.getWidth();
        this.height = shapeConfig.getHeight();
        this.adjustStart = shapeConfig.getadjustStart();
        this.startValuePoint = shapeConfig.getstartValuePoint();
        this.adjustEnd = shapeConfig.getadjustEnd();
    }
// execute paint

    public void paint(Graphics g) 
    {
        Graphics2D g2 = (Graphics2D) g;
        // outline
        if (shapeShadingType.equals(ShapeShadingType.OUTLINE))  
        {
            g.setColor(primaryColor);
            g2.setStroke(new BasicStroke(8));
            g.drawOval(adjustStart.getX(), adjustStart.getY(), width, height);
        } 
        // filled in
        else if (shapeShadingType.equals(ShapeShadingType.FILLED_IN))
        {
            g.setColor(secondaryColor);
            g.fillOval(adjustStart.getX(), adjustStart.getY(), width, height);
        }
        // outline and filled in
        else if (shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN))
        {
            g.setColor(primaryColor);
            g2.setStroke(new BasicStroke(8));
            g.drawOval(adjustStart.getX(), adjustStart.getY(), width, height);
            g.setColor(secondaryColor);
            g.fillOval(adjustStart.getX(), adjustStart.getY(), width, height);
        }
    }

    public boolean contains(Point startValuePoint)
    {
        return (adjustStart.getX() < startValuePoint.getX() && adjustStart.getY() < startValuePoint.getY()
                && adjustStart.getX() + width > startValuePoint.getX() && adjustStart.getY() + height > startValuePoint.getY());
    }
// adjust program start and end
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

// get start and end value point
    public Point getstartValuePoint() 
    {
        return startValuePoint;
    }

    public Point getendValuePoint()
    {
        return adjustEnd;
    }

    @Override
    public void setadjustStart(Point adjustStart) 
    {
        this.adjustStart = adjustStart;
    }

   
    @Override
    public void addX(int dx) 
    {
        adjustStart.setX(adjustStart.getX() + dx);
        adjustEnd.setX(adjustEnd.getX() + dx);

    }

    @Override
    public void addY(int dy) 
    {
        adjustStart.setY(adjustStart.getY() + dy);
        adjustEnd.setY(adjustEnd.getY() + dy);

    }
// get shape config
    
    
    public shapeConfig getshapeConfig() 
    {
        return shapeConfig;
    }
// get width and height
    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
// set shading type
    public void setShapeShadingType(ShapeShadingType shapeShadingType)
    {
        this.shapeShadingType = shapeShadingType;
    }
// set primary and secondary color
    public void setPrimaryColor(Color primaryColor)
    {
        this.primaryColor = primaryColor;
    }

    public void setSecondaryColor(Color secondaryColor)
    {
        this.secondaryColor = secondaryColor;
    }

	
}



