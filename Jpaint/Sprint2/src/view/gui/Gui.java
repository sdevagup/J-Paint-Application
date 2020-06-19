package view.gui;

import javax.swing.*;

import view.nameEvent;
import view.interfaces.IDialogChoice;
import view.interfaces.IEventCallback;
import view.interfaces.IGuiWindow;
import view.interfaces.ImoduleUI;

public class Gui implements ImoduleUI
{

    private final IGuiWindow gui;
// Invoke GUI
    public Gui(IGuiWindow gui)
    {
        this.gui = gui;
    }
// add event 
    @Override
    public void addEvent(nameEvent nameEvent, IEventCallback callback)
    {
        JButton button = gui.getButton(nameEvent);
        button.addActionListener((ActionEvent) -> callback.run());
    }
// get dialog
    @Override
    public <T> T getDialogResponse(IDialogChoice dialogSettings) 
    {
        Object selectedValue = JOptionPane.showInputDialog(null,dialogSettings.getDialogText(), dialogSettings.getDialogTitle(), JOptionPane.PLAIN_MESSAGE, null,dialogSettings.getDialogOptions(),dialogSettings.getCurrentSelection());
        return selectedValue == null? (T) dialogSettings.getCurrentSelection(): (T) selectedValue;
    }
}
