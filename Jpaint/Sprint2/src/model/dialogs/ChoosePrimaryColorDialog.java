package model.dialogs;

import model.shapeColor;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

public class ChoosePrimaryColorDialog implements IDialogChoice<shapeColor> 
{

    private final IApplicationState stateApp;
// choose primary color dialog
    public ChoosePrimaryColorDialog(IApplicationState stateApp)
    {
        this.stateApp = stateApp;
    }
    // get dialog option
    @Override
    public shapeColor[] getDialogOptions() 
    {
        return shapeColor.values();
    }
// get seletion
    @Override
    public shapeColor getCurrentSelection()
    {
        return stateApp.getActivePrimaryColor();
    }
// get diaglog  title
    @Override
    public String getDialogTitle() 
    {
        return "Primary Color";
    }
// get text
    @Override
    public String getDialogText()
    {
        return "Select a primary color from the menu below:";
    }

   
}
