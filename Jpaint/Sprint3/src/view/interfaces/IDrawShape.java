package view.interfaces;
import model.ShapeShadingType;
import model.ShapeType;
import controller.Point;
import model.shapeConfig;


import java.awt.*;

public interface IDrawShape {
	// invoke function
    void paint(Graphics g);

    boolean contains(Point start_Point);

    Point getstartValuePoint();

    Point getendValuePoint();
    

    void addX(int dx);

    void addY(int dy);

    void setadjustEnd(Point adjustEnd);

    void setadjustStart(Point adjustStart);
    

    Point getadjustStart();

    Point getadjustEnd();

    shapeConfig getshapeConfig();
    

    int getWidth();

    int getHeight();

    
    void setShapeShadingType(ShapeShadingType shapeShadingType);

    void setPrimaryColor(Color primaryColor);

    void setSecondaryColor(Color secondaryColor);

}