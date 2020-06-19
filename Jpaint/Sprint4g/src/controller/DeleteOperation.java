package controller;

import interfaces.IUndoable;
import model.interfaces.IlistShapeSubject;
import model.shapeConfig;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;

import java.util.ArrayList;

// create the delete operation 
public class DeleteOperation implements IOperation, IUndoable
{
	// create listshape, stateapp,shapeConfig and presentShape
    private IlistShapeSubject listShape;
    private IApplicationState stateApp;
    private shapeConfig shapeConfig;
    ArrayList<IDrawShape> presentShape;
// implement the DeleteOperation
    public DeleteOperation(IApplicationState stateApp, IlistShapeSubject listShape, shapeConfig shapeConfig) {
        this.stateApp = stateApp;
        this.shapeConfig = shapeConfig;
        this.listShape = listShape;
    }
// run the function
    public void run()
    {

        System.out.println("DeleteOperation executed.");
        presentShape = listShape.getpresentShapeList();
        for (IDrawShape shape : presentShape) 
        {
            listShape.remove(shape);
            listShape.notifyObserver();
        }
        // print the shape in canvas
        System.out.println("Canvas shapes: " + listShape.getlistShape().size());
        OperationHistory.add(this);

    }
    // Create the undo function

    @Override
    public void undo()
    {
        for (IDrawShape shape : presentShape) 
        {
            listShape.add(shape);
        }
    }
// creaet the redo function
    @Override
    public void redo() 
    {
        for (IDrawShape shape : presentShape) 
        {
            listShape.remove(shape);
        }
    }
}