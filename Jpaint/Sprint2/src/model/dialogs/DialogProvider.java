package model.dialogs;

import model.shapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndendValuePointMode;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import view.interfaces.IDialogChoice;

public class DialogProvider implements IDialogProvider 
{
    private final IDialogChoice<ShapeType> chooseShapeDialog;
    private final IDialogChoice<shapeColor> choosePrimaryColorDialog;
    private final IDialogChoice<shapeColor> chooseSecondaryColorDialog;
    private final IDialogChoice<ShapeShadingType> chooseShadingTypeDialog;
    private final IDialogChoice<StartAndendValuePointMode> chooseStartAndendValuePointModeDialog;
    private final IApplicationState stateApp;
    
// constructor
    public DialogProvider(IApplicationState stateApp)
    {
    	
        this.stateApp = stateApp;
        chooseShapeDialog = new ChooseShapeDialog(this.stateApp);
        choosePrimaryColorDialog = new ChoosePrimaryColorDialog(this.stateApp);
        chooseSecondaryColorDialog = new ChooseSecondaryColorDialog(this.stateApp);
        chooseShadingTypeDialog = new ChooseShadingTypeDialog(this.stateApp);
        chooseStartAndendValuePointModeDialog = new ChooseStartAndendValuePointModeDialog(this.stateApp);
    }
    
// dialog choice
    @Override
    public IDialogChoice<ShapeType> getChooseShapeDialog() 
    {
        return chooseShapeDialog;
    }
// primary color
    @Override
    public IDialogChoice<shapeColor> getChoosePrimaryColorDialog()
    {
        return choosePrimaryColorDialog;
    }
// secondary color
    @Override
    public IDialogChoice<shapeColor> getChooseSecondaryColorDialog()
    {
        return chooseSecondaryColorDialog;
    }
// shading type
    @Override
    public IDialogChoice<ShapeShadingType> getChooseShadingTypeDialog() 
    {
        return chooseShadingTypeDialog;
    }
// start and end value
    @Override
    public IDialogChoice<StartAndendValuePointMode> getChooseStartAndendValuePointModeDialog()
    {
        return chooseStartAndendValuePointModeDialog;
    }
}
