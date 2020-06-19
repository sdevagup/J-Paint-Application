package controller;

import model.interfaces.IlistShapeSubject;
import model.shapeConfig;
import model.interfaces.IApplicationState;
import view.nameEvent;
import view.interfaces.ImoduleUI;
// JPaintController class implements  IJPaintController

public class JPaintController implements IJPaintController 
{
	//create the variable  moduleUI, stateApp, listShape, shapeConfig
    private final ImoduleUI moduleUI;
    private final IApplicationState stateApp;
    private IlistShapeSubject listShape;
    private shapeConfig shapeConfig;
// constructor JPaintController
    public JPaintController(ImoduleUI moduleUI, IApplicationState stateApp, IlistShapeSubject listShape, shapeConfig shapeConfig) {
        this.moduleUI = moduleUI;
        this.stateApp = stateApp;
        this.listShape = listShape;
        this.shapeConfig = shapeConfig;
    }
// setup method
    @Override
    public void setup()
    {
        setupEvents();
    }
// setup events method for GUI
    private void setupEvents()
    {
        moduleUI.addEvent(nameEvent.CHOOSE_SHAPE, () -> stateApp.setActiveShape());
        moduleUI.addEvent(nameEvent.CHOOSE_PRIMARY_COLOR, () -> stateApp.setActivePrimaryColor());
        moduleUI.addEvent(nameEvent.CHOOSE_SECONDARY_COLOR, () -> stateApp.setActiveSecondaryColor());
        moduleUI.addEvent(nameEvent.CHOOSE_SHADING_TYPE, () -> stateApp.setActiveShadingType());
        moduleUI.addEvent(nameEvent.CHOOSE_START_POINT_endValuePoint_MODE, () -> stateApp.setActiveStartAndendValuePointMode());
      
        moduleUI.addEvent(nameEvent.CLEAR, () -> new ClearAllOperation(listShape).run());
    }
}
