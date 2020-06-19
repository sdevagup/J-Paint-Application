package model.interfaces;

import controller.Point;
import model.*;
import view.gui.IColorObserver;
import view.interfaces.IMouseAdapterObserver;

public interface IApplicationState 
{
	// invoke methods
    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndendValuePointMode();
    

    ShapeType getActiveShapeType();

    shapeColor getActivePrimaryColor();

    shapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    StartAndendValuePointMode getActiveStartAndendValuePointMode();

    shapeConfig getCurrentshapeConfig();
    

    void setstartValuePoint(Point startValuePoint);

    void setendValuePoint(Point endValuePoint);

    Point getstartValuePoint();

    Point getendValuePoint();

    Point getadjustStart();

    Point getadjustEnd();
    

    void setActivePrimaryColor(shapeColor activePrimaryColor);

    void setActiveSecondaryColor(shapeColor activeSecondaryColor);

    void registerObserver(IMouseAdapterObserver o);

    void notifyObservers();

    void registerColorObserver(IColorObserver o);

    void notifyColorObservers();


}