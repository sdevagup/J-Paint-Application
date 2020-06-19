package controller;

import model.interfaces.IlistShapeSubject;
import model.shapeConfig;
import model.interfaces.IApplicationState;
import view.interfaces.IDrawShape;
// ShapeSelectOperation class
public class ShapeSelectOperation implements IOperation
{
// variables
    private IlistShapeSubject listShape;
    private IDrawShape selectedShape;
    private IApplicationState stateApp;
    Boolean containsSelectedShape = false;
// constuctor
    public ShapeSelectOperation(IApplicationState stateApp, IlistShapeSubject listShape, shapeConfig shapeConfig) {
        this.listShape = listShape;
        this.stateApp = stateApp;
    }
// run method

    @Override
    public void run() 
    {
        System.out.println("Select mode ");

        for (IDrawShape shape : listShape.getlistShape())
        {
            boolean contain = shape.contains(stateApp.getstartValuePoint());
            if (contain)
            {
                containsSelectedShape = true;
                selectedShape = shape;
                listShape.addSelectedList(selectedShape);
                System.out.println(">> Shape selected. " + listShape.getpresentShapeList().size());
                break;
            } 
            else 
            {
                //listShape.getpresentShapeList().remove(shape);
                containsSelectedShape = false;
                //System.out.println("Shape not selected.");
            }
        }
        if (containsSelectedShape == false) 
        {
            listShape.clearSelectedlistShape();
            listShape.getClipBoard().clear();
            System.out.println("Shape List cleared. Shapes selected: " + listShape.getpresentShapeList().size());
        }
    }
// get shape
    public IDrawShape getSelectedShape() 
    {
        return selectedShape;
    }
}