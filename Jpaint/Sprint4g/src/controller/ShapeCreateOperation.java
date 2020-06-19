package controller;

import interfaces.IUndoable;
import model.interfaces.IlistShapeSubject;
import model.shapeConfig;
import model.ShapeFactory;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;
// ShapeCreateOperation 
public class ShapeCreateOperation implements IOperation, IUndoable 
{

    ShapeFactory shapeFactory = new ShapeFactory();
    // create variable
    private final IApplicationState stateApp;
    private shapeConfig shapeConfig;
    private IlistShapeSubject listShape;


    private IDrawShape shape;
// constructor
    public ShapeCreateOperation(IApplicationState stateApp, IlistShapeSubject listShape, shapeConfig shapeConfig) {
        this.stateApp = stateApp;
        this.listShape = listShape;
        this.shapeConfig = shapeConfig;
    }
// execute
    @Override
    public void run()
    {
        //  System.out.println("ShapeCreateOperation running... ");
        shapeConfig = stateApp.getCurrentshapeConfig();
        shape = shapeFactory.createShape(shapeConfig);
        this.listShape.add(shape);
        OperationHistory.add(this);

    }
// getshape method
    public IDrawShape getShape()
    {
        return shape;
    }
// undo function
    @Override
    public void undo()
    {
        listShape.remove(shape);
    }
// redo function
    @Override
    public void redo()
    {
        listShape.add(shape);
    }


}
