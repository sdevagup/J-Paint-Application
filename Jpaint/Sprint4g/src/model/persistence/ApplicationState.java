package model.persistence;

import controller.Point;
import model.*;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import model.interfaces.IlistShapeSubject;
import view.gui.ColorObserver;
import view.gui.IColorObserver;
import view.interfaces.ImoduleUI;
import view.interfaces.IMouseAdapterObserver;

import java.util.ArrayList;

public class ApplicationState implements IApplicationState
{
	// create variables
    private final ImoduleUI moduleUI;
    private final IDialogProvider dialogProvider;
    private IlistShapeSubject listShape;
    private ArrayList<IMouseAdapterObserver> mouseObservers = new ArrayList<IMouseAdapterObserver>();
    private ArrayList<IColorObserver> colorObservers = new ArrayList<IColorObserver>();
    private ShapeType activeShapeType;
    private shapeColor activePrimaryColor;
    private shapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndendValuePointMode activeStartAndendValuePointMode;
    private Point startValuePoint;
    private Point endValuePoint;
    private Point adjustStart;
    private Point adjustEnd;
    private int width;
    private int height;

// get shape configuration
    public shapeConfig getCurrentshapeConfig() 
    {
        shapeConfig shapeConfig = new shapeConfig();
        shapeConfig.setPrimaryColor(activePrimaryColor);
        shapeConfig.setSecondaryColor(activeSecondaryColor);
        shapeConfig.setShadingType(activeShapeShadingType);
        shapeConfig.setShapeType(activeShapeType);
        shapeConfig.setendValuePoint(endValuePoint);
        shapeConfig.setstartValuePoint(startValuePoint);
        shapeConfig.setadjustStart(adjustStart);
        shapeConfig.setadjustEnd(adjustEnd);
        shapeConfig.setWidth(width);
        shapeConfig.setHeight(height);

        return shapeConfig;
    }
// application state 

    public ApplicationState(ImoduleUI moduleUI, IlistShapeSubject listShape)
    {
        this.moduleUI = moduleUI;
        this.listShape = listShape;
        this.dialogProvider = new DialogProvider(this);
        setfulfills();
    }
// set fill color
    private void setfulfills()
    {
        activeShapeType = ShapeType.ELLIPSE;
        activePrimaryColor = shapeColor.BLUE;
        activeSecondaryColor = shapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeStartAndendValuePointMode = StartAndendValuePointMode.DRAW;
    }
// set primary color
    public void setActivePrimaryColor(shapeColor activePrimaryColor) 
    {
        this.activePrimaryColor = activePrimaryColor;
    }
// set seconday color
    public void setActiveSecondaryColor(shapeColor activeSecondaryColor)
    {
        this.activeSecondaryColor = activeSecondaryColor;
    }
// set start point
    public void setstartValuePoint(Point startValuePoint)
    {
        this.startValuePoint = startValuePoint;
    }
// set end point
    public void setendValuePoint(Point endValuePoint) 
    {
        this.endValuePoint = endValuePoint;
    }
// set width
    public void setWidth(int width)
    {
        Point adjustStart = getadjustStart();
        Point adjustEnd = getadjustEnd();
        this.width = adjustEnd.getX() - adjustStart.getX();
    }
// set height
    public void setHeight(int height) 
    {
        Point adjustStart = getadjustStart();
        Point adjustEnd = getadjustEnd();
        this.height = adjustEnd.getY() - adjustStart.getY();
    }
// set shape
    @Override
    public void setActiveShape() 
    {
        activeShapeType = moduleUI.getDialogResponse(dialogProvider.getChooseShapeDialog());
        System.out.println("IApplicationState - changed shapeConfig: " + activeShapeType);
    }
// set primary color
    @Override
    public void setActivePrimaryColor()
    {
        activePrimaryColor = moduleUI.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
        System.out.println("IApplicationState - changed Primary Color " + activePrimaryColor);
        notifyColorObservers();
    }
// set secondary color
    @Override
    public void setActiveSecondaryColor() 
    {
        activeSecondaryColor = moduleUI.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
        System.out.println("IApplicationState - changed Secondary Color: " + activeSecondaryColor);
        notifyColorObservers();
    }
// set shadding type
    @Override
    public void setActiveShadingType() 
    {
        activeShapeShadingType = moduleUI.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
        System.out.println("IApplicationState - changed Shading Type: " + activeShapeShadingType);
        notifyColorObservers();
    }
// set satrt and end value point
    @Override
    public void setActiveStartAndendValuePointMode()
    {
        activeStartAndendValuePointMode = moduleUI.getDialogResponse(dialogProvider.getChooseStartAndendValuePointModeDialog());
        System.out.println("IApplicationState - changed Mouse Mode: " + activeStartAndendValuePointMode);
        notifyObservers();

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
// get shape
    @Override
    public ShapeType getActiveShapeType() 
    {
        return activeShapeType;
    }
// get primary color
    @Override
    public shapeColor getActivePrimaryColor()
{
        return activePrimaryColor;
    }
// get secondary color
    @Override
    public shapeColor getActiveSecondaryColor()
    {
        return activeSecondaryColor;
    }
// get shading type
    @Override
    public ShapeShadingType getActiveShapeShadingType()
    {
        return activeShapeShadingType;
    }
// get strat and end value
    @Override
    public StartAndendValuePointMode getActiveStartAndendValuePointMode()
    {
        return activeStartAndendValuePointMode;
    }

    public Point getstartValuePoint() 
    {
        return startValuePoint;
    }

    public Point getendValuePoint() 
    {
        return endValuePoint;
    }
// get adjust start and end
    public Point getadjustStart() 
    {
        int startX = Math.min(startValuePoint.getX(), endValuePoint.getX());
        int startY = Math.min(startValuePoint.getY(), endValuePoint.getY());
        adjustStart = new Point(startX, startY);
        return adjustStart;
    }

    public Point getadjustEnd()
    {
        int endX = Math.max(startValuePoint.getX(), endValuePoint.getX());
        int endY = Math.max(startValuePoint.getY(), endValuePoint.getY());
        adjustEnd = new Point(endX, endY);
        return adjustEnd;
    }
// set adjust strat and end
    public void setadjustStart(Point adjustStart) 
    {
        this.adjustStart = adjustStart;
    }

    public void setadjustEnd(Point adjustEnd) 
    {
        this.adjustEnd = adjustEnd;
    }

// create registerObserver method
    @Override
    public void registerObserver(IMouseAdapterObserver o) 
    {
        mouseObservers.add(o);
    }

    @Override
    public void notifyObservers() 
    {
     
        for (IMouseAdapterObserver observer : mouseObservers) 
        {
            observer.run();
           

        }
    }

// registerColorObserver
    @Override
    public void registerColorObserver(IColorObserver o) 
    {
        colorObservers.add(o);
    }
// notifyColorObservers
    @Override
    public void notifyColorObservers()
    {
       

        ColorObserver colorObserver = new ColorObserver(getCurrentshapeConfig(), listShape, this);

        for (IColorObserver observer : colorObservers) 
        {
            colorObserver.run();
          

        }
    }
}

