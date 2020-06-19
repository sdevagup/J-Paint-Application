package view.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controller.Point;
import controller.ShapeCreateOperation;
import controller.ShapeMoveOperation;
import controller.ShapeSelectOperation;
import model.shapeColor;
import model.dialogs.DialogProvider;
import model.interfaces.IlistShapeSubject;
import model.shapeConfig;
import model.StartAndendValuePointMode;
import model.interfaces.IApplicationState;
import org.junit.Test;
import view.interfaces.ImoduleUI;

import javax.swing.*;


public class DrawClickHandler extends MouseAdapter 
{
	// variables
    private Point startValuePoint;
    private Point endValuePoint;
    private IApplicationState stateApp;
    private IlistShapeSubject listShape;
    private shapeConfig shapeConfig;
    ArrayList<shapeColor> shapeColor = new ArrayList();

// constructor
    public DrawClickHandler(IApplicationState stateApp, IlistShapeSubject listShape, shapeConfig shapeConfig) {
        this.stateApp = stateApp;
        this.listShape = listShape;
        this.shapeConfig = shapeConfig;


    }

   
// mouse click movement
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if (stateApp.getActiveStartAndendValuePointMode() == StartAndendValuePointMode.DRAW) {
           

            shapeColor primaryColor = stateApp.getActivePrimaryColor();
            shapeColor.add(primaryColor);
            shapeColor secondaryColor = stateApp.getActiveSecondaryColor();
            shapeColor.add(secondaryColor);

            if (SwingUtilities.isLeftMouseButton(e)) 
            {
            	// left click
                System.out.println("LEFT MOUSE>> Color Flip");
                stateApp.setActivePrimaryColor(shapeColor.get(0));
                stateApp.setActiveSecondaryColor(shapeColor.get(1));

            } 
            else if (SwingUtilities.isRightMouseButton(e)) 
            {
            	// right click
                System.out.println("<<RIGHT MOUSE Color Flip");
                stateApp.setActivePrimaryColor(shapeColor.get(1));
                stateApp.setActiveSecondaryColor(shapeColor.get(0));
            } else {
            }
        }
    }
// press mouse 
    @Override
    public void mousePressed(MouseEvent e)
    {
    	// get x and y value
        startValuePoint = new Point(e.getX(), e.getY());
        stateApp.setstartValuePoint(startValuePoint);
        System.out.println("DrawClickHandler reporting Mouse was pressed. Point (Start): " + "(" + startValuePoint.getX() + "," + startValuePoint.getY() + ")");

    }
// release mouse

    @Override
    public void mouseReleased(MouseEvent e) 
    {
// get x and y value
        endValuePoint = new Point(e.getX(), e.getY());
        stateApp.setendValuePoint(endValuePoint);
        StartAndendValuePointMode mouseMode = stateApp.getActiveStartAndendValuePointMode();
        System.out.println("DrawClickHandler reporting Mouse was released. Point(End): " + "(" + endValuePoint.getX() + "," + endValuePoint.getY() + ")");

        switch (mouseMode) 
        {
        // draw mode
            case DRAW:
                ShapeCreateOperation newShape = new ShapeCreateOperation(stateApp, listShape, shapeConfig);
                newShape.run();
                break;
// move
            case MOVE:
                ShapeMoveOperation newMove = new ShapeMoveOperation(stateApp, listShape, shapeConfig);
                newMove.run();
                break;
// select
            case SELECT:
                ShapeSelectOperation newSelect = new ShapeSelectOperation(stateApp, listShape, shapeConfig);
                newSelect.run();
                break;
        }
    }


}
