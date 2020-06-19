package main;

import controller.IJPaintController;
import controller.JPaintController;
import events.ShapeMouseListener;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args){
    	PaintCanvas paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();

        ShapeMouseListener mouseListener = new ShapeMouseListener(paintCanvas);
        
        paintCanvas.addMouseListener(mouseListener);
        paintCanvas.addMouseMotionListener(mouseListener);        
       
    }
}
