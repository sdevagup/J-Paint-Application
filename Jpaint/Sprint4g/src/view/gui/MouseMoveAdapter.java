package view.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controller.Point;
import controller.ShapeMoveOperation;
import model.shapeColor;
import model.interfaces.IlistShapeSubject;
import model.shapeConfig;
import model.interfaces.IApplicationState;


public class MouseMoveAdapter extends MouseAdapter
{
	// create variable
    private Point startValuePoint;
    private Point endValuePoint;
    private IApplicationState stateApp;
    private IlistShapeSubject listShape;
    private shapeConfig shapeConfig;
    // create array list
    ArrayList<shapeColor> shapeColor = new ArrayList();

// constructor
    public MouseMoveAdapter(IApplicationState stateApp, IlistShapeSubject listShape, shapeConfig shapeConfig) {
        this.stateApp = stateApp;
        this.listShape = listShape;
        this.shapeConfig = shapeConfig;


    }
// mouse press
    @Override
    public void mousePressed(MouseEvent e) 
    {
        startValuePoint = new Point(e.getX(), e.getY());
        stateApp.setstartValuePoint(startValuePoint);


    }

// mouse release
    @Override
    public void mouseReleased(MouseEvent e) 
    {

        endValuePoint = new Point(e.getX(), e.getY());
        stateApp.setendValuePoint(endValuePoint);
        System.out.println("Shape move executed." + "Point (Start): " + "(" + startValuePoint.getX() + "," + startValuePoint.getY() + ")" + "Point(End): " + "(" + endValuePoint.getX() + "," + endValuePoint.getY() + ")");
        ShapeMoveOperation newMove = new ShapeMoveOperation(stateApp, listShape, shapeConfig);
        newMove.run();
    }
}
