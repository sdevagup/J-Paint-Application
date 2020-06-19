package model.interfaces;

import model.shapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndendValuePointMode;
import view.interfaces.IDialogChoice;

public interface IDialogProvider 
{
	// invoke methods

    IDialogChoice<ShapeType> getChooseShapeDialog();

    IDialogChoice<shapeColor> getChoosePrimaryColorDialog();

    IDialogChoice<shapeColor> getChooseSecondaryColorDialog();

    IDialogChoice<ShapeShadingType> getChooseShadingTypeDialog();

    IDialogChoice<StartAndendValuePointMode> getChooseStartAndendValuePointModeDialog();


}
