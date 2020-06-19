package view.interfaces;

import view.nameEvent;
//ImoduleUI interface
public interface ImoduleUI {
    void addEvent(nameEvent nameEvent, IEventCallback command);

    <T> T getDialogResponse(IDialogChoice dialogChoice);
}
