package controller;

import interfaces.IUndoable;
import model.interfaces.IlistShapeSubject;
import model.shapeConfig;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;

import java.util.ArrayList;

// ShapeMoveOperation class
public class ShapeMoveOperation implements IOperation, IUndoable
{
    private IApplicationState stateApp;
    private IlistShapeSubject listShape;
    private shapeConfig shapeConfig;
    private IDrawShape old_shape;
    private IDrawShape new_shape;
    private ArrayList<IDrawShape> templistShape;

// constructor
    public ShapeMoveOperation(IApplicationState stateApp, IlistShapeSubject listShape, shapeConfig shapeConfig) {
        this.stateApp = stateApp;
        this.listShape = listShape;
        this.shapeConfig = shapeConfig;
    }
// run function
    @Override
    public void run() 
    {


        templistShape = new ArrayList<IDrawShape>();

        int dx = stateApp.getendValuePoint().getX() - stateApp.getstartValuePoint().getX();
        int dy = stateApp.getendValuePoint().getY() - stateApp.getstartValuePoint().getY();

        for (IDrawShape selectedShape : listShape.getpresentShapeList()) 
        {
            old_shape = selectedShape;
            templistShape.add(old_shape);
            listShape.remove(old_shape);

            for (IDrawShape tempShape : templistShape)
            {
                tempShape.addX(dx);
                tempShape.addY(dy);
                new_shape = tempShape;
                listShape.add(new_shape);
            }
            templistShape.clear();
        }
        OperationHistory.add(this);
    }

// undo function
    @Override
    public void undo() 
    {
        
        listShape.remove(new_shape);
        listShape.add(old_shape);

    }
// redo function
    @Override
    public void redo()
    {
     
        listShape.add(new_shape);
        listShape.remove(old_shape);

    }
}
