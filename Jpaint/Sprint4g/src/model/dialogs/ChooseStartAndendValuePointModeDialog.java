package model.dialogs;

import model.StartAndendValuePointMode;
import model.interfaces.IApplicationState;
import view.interfaces.IDialogChoice;

public class ChooseStartAndendValuePointModeDialog implements IDialogChoice<StartAndendValuePointMode>
{
	// variables
    private final IApplicationState stateApp;

    public ChooseStartAndendValuePointModeDialog(IApplicationState stateApp)
    {

        this.stateApp = stateApp;
    }

   // get dialog option

    @Override
    public StartAndendValuePointMode[] getDialogOptions()
    {
        return StartAndendValuePointMode.values();
    }
// get selection
    @Override
    public StartAndendValuePointMode getCurrentSelection() 
    {
        return stateApp.getActiveStartAndendValuePointMode();
    }
    // get dialog title
    @Override
    public String getDialogTitle()
    {
        return "Start and End Point Mode";
    }
// get dialog text
    @Override
    public String getDialogText()
    {
        return "Select a shading type from the menu below:";
    }
}
