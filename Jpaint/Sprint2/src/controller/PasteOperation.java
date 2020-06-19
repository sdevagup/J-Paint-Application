package controller;

import interfaces.IUndoable;
import model.interfaces.IlistShapeSubject;
import model.shapeConfig;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;

import java.util.ArrayList;
//PasteOperationclass
public class PasteOperation implements IOperation, IUndoable 
{
    private IlistShapeSubject listShape;
    private IApplicationState stateApp;
    private shapeConfig shapeConfig;
    private IDrawShape duplicatedShape;
    private IDrawShape oldshape;
    private IDrawShape newShape;
    private ArrayList<IDrawShape> templistShape = new ArrayList<IDrawShape>();

// constructor
    public PasteOperation(IApplicationState stateApp, IlistShapeSubject listShape, shapeConfig shapeConfig) {
        this.stateApp = stateApp;
        this.shapeConfig = shapeConfig;
        this.listShape = listShape;
    }
// execute the function
    public void run()
    {
// draw the shape
        for (IDrawShape selectedShape : listShape.getpresentShapeList())
        {
            newShape = selectedShape;
            selectedShape.addX(50);
            selectedShape.addY(50);

            ShapeCreateOperation shape = new ShapeCreateOperation(stateApp, listShape, selectedShape.getshapeConfig());
            templistShape.add(shape.shapeFactory.createShape(selectedShape.getshapeConfig()));
        }

        for (IDrawShape selectedShape : templistShape)
        {
            listShape.add(selectedShape);
           
        }

        OperationHistory.add(this);
       
    }
// undo function
    @Override
    public void undo()
    {

        listShape.remove(newShape);
       
    }
// redo function
    @Override
    public void redo()
    {
        listShape.add(newShape);

    }
}