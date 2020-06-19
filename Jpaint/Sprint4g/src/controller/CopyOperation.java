package controller;

import model.interfaces.IlistShapeSubject;
import model.shapeConfig;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;
// Create the copyoperation and implements ioperation
public class CopyOperation implements IOperation
{
    IApplicationState stateApp;
    IlistShapeSubject listShape;
    shapeConfig shapeConfig;

    public CopyOperation(IApplicationState stateApp, IlistShapeSubject listShape, shapeConfig shapeConfig)
    {
        this.stateApp = stateApp;
        this.listShape = listShape;
        this.shapeConfig = shapeConfig;
    }
// create the run function
    @Override
    public void run() 
    {
        for (IDrawShape shape : listShape.getpresentShapeList())
        {
            listShape.addClipBoardShapes(shape);
        }
        System.out.println(listShape.getClipBoard().size() + " Shapes Copied.");

      
    }
}
