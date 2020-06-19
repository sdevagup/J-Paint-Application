package model.dialogs;

import model.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

public class ChooseShapeDialog implements IDialogChoice<ShapeType>
{
    private final IApplicationState stateApp;
// constructor
    public ChooseShapeDialog(IApplicationState stateApp)
    {

        this.stateApp = stateApp;
    }

// set shape type    

    @Override
    public ShapeType[] getDialogOptions()
    {
        return ShapeType.values();
    }
// get selection shape type
    @Override
    public ShapeType getCurrentSelection()
    {
        return stateApp.getActiveShapeType();
    }
    // get title
    @Override
    public String getDialogTitle() 
    {
        return "shapeConfig";
    }
// get text
    @Override
    public String getDialogText() 
    {
        return "Select a shape from the menu below:";
    }
}
