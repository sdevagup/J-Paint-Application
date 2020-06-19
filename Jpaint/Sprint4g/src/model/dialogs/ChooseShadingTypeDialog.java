package model.dialogs;

import model.ShapeShadingType;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

public class ChooseShadingTypeDialog implements IDialogChoice<ShapeShadingType>
{
    private final IApplicationState stateApp;

// constructor
    public ChooseShadingTypeDialog(IApplicationState stateApp) 
    {

        this.stateApp = stateApp;
    }

    // get title
    @Override
    public String getDialogTitle() 
    {
        return "Shading Type";
    }

   // get dialog option
    @Override
    public ShapeShadingType[] getDialogOptions() 
    {
        return ShapeShadingType.values();
    }
    // get dialog text
    @Override
    public String getDialogText() 
    {
        return "Select a shading type from the menu below:";
    }
// get selection
    @Override
    public ShapeShadingType getCurrentSelection() 
    {
        return stateApp.getActiveShapeShadingType();
    }
}
