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

import view.interfaces.ImoduleUI;

import javax.swing.*;

//MouseSelectAdapter class 
public class MouseSelectAdapter extends MouseAdapter 
{
	// create variable
    private Point startValuePoint;
    private Point endValuePoint;
    private IApplicationState stateApp;
    private IlistShapeSubject listShape;
    private shapeConfig shapeConfig;
    ArrayList<shapeColor> shapeColor = new ArrayList();

// Constructor MouseSelectAdapter
    public MouseSelectAdapter(IApplicationState stateApp, IlistShapeSubject listShape, shapeConfig shapeConfig) {
        this.stateApp = stateApp;
        this.listShape = listShape;
        this.shapeConfig = shapeConfig;


    }
// press mouse
    @Override
    public void mousePressed(MouseEvent e)
    {
        // get the x and y value
        startValuePoint = new Point(e.getX(), e.getY());
        stateApp.setstartValuePoint(startValuePoint);
       

    }
// release mouse

    @Override
    public void mouseReleased(MouseEvent e)
    {
// get the x and y value
        endValuePoint = new Point(e.getX(), e.getY());
        stateApp.setendValuePoint(endValuePoint);
        StartAndendValuePointMode mouseMode = stateApp.getActiveStartAndendValuePointMode();
       
        ShapeSelectOperation newSelect = new ShapeSelectOperation(stateApp, listShape, shapeConfig);
        newSelect.run();
    }


}
