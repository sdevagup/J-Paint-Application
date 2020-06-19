package view.gui;

import controller.ShapeCreateOperation;
import model.shapeConfig;
import model.ShapeFactory;
import model.listShape;
import model.dialogs.ColorSingleton;
import model.interfaces.IApplicationState;
import model.interfaces.IlistShapeSubject;
import view.interfaces.IDrawShape;

public class ColorObserver implements IColorObserver 
{
	// variable
    shapeConfig shapeConfig;
    IlistShapeSubject listShape;
    IApplicationState stateApp;

// constructor
    public ColorObserver(shapeConfig shapeConfig, IlistShapeSubject listShape, IApplicationState stateApp) {
        this.listShape = listShape;
        this.shapeConfig = shapeConfig;
        this.stateApp = stateApp;
        stateApp.registerColorObserver(this);
    }
// program execution

    @Override
    public void run()
    {
        
        for (IDrawShape shape : listShape.getpresentShapeList())
        {
            shape.setPrimaryColor(ColorSingleton.getColor(stateApp.getActivePrimaryColor()));
            shape.setSecondaryColor(ColorSingleton.getColor(stateApp.getActiveSecondaryColor()));
            shape.setShapeShadingType(stateApp.getActiveShapeShadingType());
            
        }
        // invoke methods
        listShape.notifyObserver();
        listShape.getpresentShapeList().clear();
       
    }
}
