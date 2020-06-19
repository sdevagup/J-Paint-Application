package model;

import view.gui.DrawEllipseStrategy;
import view.gui.DrawRectangleStrategy;
import view.gui.DrawTriangleStrategy;
import view.interfaces.IDrawShape;

public class ShapeFactory
{

    public IDrawShape createShape(shapeConfig shapeConfig)
    {
        ShapeType shapeType = shapeConfig.getShapeType();
        IDrawShape shape = null;
// found the shape factory
        // rectangle
        if (shapeType.equals(ShapeType.RECTANGLE))
        {
            System.out.println("Rectangle drawn.");
            shape = new DrawRectangleStrategy(shapeConfig);
            // ellipse
        } else if (shapeType.equals(ShapeType.ELLIPSE)) 
        {
            System.out.println("Ellipse drawn.");
            shape = new DrawEllipseStrategy(shapeConfig);
            // triangle
        } else if (shapeType.equals(ShapeType.TRIANGLE))
        {
            System.out.println("Triangle drawn.");
            shape = new DrawTriangleStrategy(shapeConfig);
        } else 
        {
            
        }
        return shape;
    }
}
