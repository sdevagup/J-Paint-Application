package view.interfaces;

public interface IDialogChoice<T> {
	// Invoke methods
    String getDialogTitle();

    String getDialogText();

    T[] getDialogOptions();

    T getCurrentSelection();
}
