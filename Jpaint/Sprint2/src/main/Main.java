package main;

import controller.IJPaintController;
import controller.JPaintController;
import view.gui.MouseObserver;
import model.shapeConfig;
import model.listShape;
import model.interfaces.IApplicationState;
import model.persistence.ApplicationState;
import view.gui.*;
import view.interfaces.IGuiWindow;
import view.interfaces.ImoduleUI;

import java.awt.*;

public class Main 
{

    public static void main(String[] args) 
    {
// invoke function
        listShape listShape = new listShape();
        shapeConfig shapeConfig = new shapeConfig();
        PaintCanvas paintCanvas = new PaintCanvas(listShape);
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        ImoduleUI moduleUI = new Gui(guiWindow);

        IApplicationState stateApp = new ApplicationState(moduleUI, listShape);

       
        paintCanvas.setCursor((new Cursor(Cursor.CROSSHAIR_CURSOR)));

        IJPaintController controller = new JPaintController(moduleUI, stateApp, listShape, shapeConfig);
        MouseObserver mouseObserver = new MouseObserver(stateApp, paintCanvas, listShape, shapeConfig);
        mouseObserver.run();
        controller.setup();
    }
}