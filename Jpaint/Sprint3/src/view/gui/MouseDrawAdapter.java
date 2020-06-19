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


public class MouseDrawAdapter extends MouseAdapter 
{
	// create variable
    private Point startValuePoint;
    private Point endValuePoint;
    private IApplicationState stateApp;
    private IlistShapeSubject listShape;
    private shapeConfig shapeConfig;
    // arraylist for shape
    ArrayList<shapeColor> shapeColor = new ArrayList();

// constructor
    public MouseDrawAdapter(IApplicationState stateApp, IlistShapeSubject listShape, shapeConfig shapeConfig) {
        this.stateApp = stateApp;
        this.listShape = listShape;
        this.shapeConfig = shapeConfig;


    }
// click movement
    @Override
    public void mouseClicked(MouseEvent e) 
    {

        shapeColor primaryColor = stateApp.getActivePrimaryColor();
        shapeColor.add(primaryColor);
        shapeColor secondaryColor = stateApp.getActiveSecondaryColor();
        shapeColor.add(secondaryColor);

        if (SwingUtilities.isLeftMouseButton(e)) {
            System.out.println("Left click detected. Color Flip undo executed.");
            stateApp.setActivePrimaryColor(shapeColor.get(0));
            stateApp.setActiveSecondaryColor(shapeColor.get(1));

        } else if (SwingUtilities.isRightMouseButton(e)) {
            System.out.println("Right click detected. Color Flip executed.");
            stateApp.setActivePrimaryColor(shapeColor.get(1));
            stateApp.setActiveSecondaryColor(shapeColor.get(0));
        } else {

        }

    }

// press mousse
    @Override
    public void mousePressed(MouseEvent e) 
    {
        startValuePoint = new Point(e.getX(), e.getY());
        stateApp.setstartValuePoint(startValuePoint);
        System.out.println("Point (Start): " + "(" + startValuePoint.getX() + "," + startValuePoint.getY() + ")");

    }

// mouse realse
    @Override
    public void mouseReleased(MouseEvent e)
    {
        
        endValuePoint = new Point(e.getX(), e.getY());
        stateApp.setendValuePoint(endValuePoint);
        StartAndendValuePointMode mouseMode = stateApp.getActiveStartAndendValuePointMode();
        System.out.println("Point(End): " + "(" + endValuePoint.getX() + "," + endValuePoint.getY() + ")");
        ShapeCreateOperation newShape = new ShapeCreateOperation(stateApp, listShape, shapeConfig);
        newShape.run();
    }
}
