package model.dialogs;

import model.shapeColor;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

public class ChooseSecondaryColorDialog implements IDialogChoice<shapeColor> 
{

    private final IApplicationState stateApp;
// constructor
    public ChooseSecondaryColorDialog(IApplicationState stateApp) 
    {
        this.stateApp = stateApp;
    }
// shape color
    @Override
    public shapeColor[] getDialogOptions()
    {
        return shapeColor.values();
    }
// shape color selection
    @Override
    public shapeColor getCurrentSelection() 
    {
        return stateApp.getActiveSecondaryColor();
    }
    // get title
    @Override
    public String getDialogTitle() 
    {
        return "Secondary Color";
    }
// get text
    @Override
    public String getDialogText()
    {
        return "Select a secondary color from the menu below:";
    }

}
