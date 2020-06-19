package view.gui;

import model.shapeConfig;
import model.StartAndendValuePointMode;
import model.interfaces.IApplicationState;
import model.interfaces.IlistShapeSubject;
import view.interfaces.IMouseAdapterObserver;

import javax.swing.*;
import java.awt.event.MouseListener;
//MouseObserver class 
public class MouseObserver extends JFrame implements IMouseAdapterObserver 
{
	// create variable
    private IApplicationState stateApp;
    private PaintCanvas paintCanvas;
    private IlistShapeSubject listShape;
    private shapeConfig shapeConfig;
// Constructor
    public MouseObserver(IApplicationState stateApp, PaintCanvas paintCanvas, IlistShapeSubject listShape, shapeConfig shapeConfig) {
        this.stateApp = stateApp;
        this.paintCanvas = paintCanvas;
        this.listShape = listShape;
        this.shapeConfig = shapeConfig;
        stateApp.registerObserver(this);
    }

// run function for mouse event
    public void run()
    {

        MouseListener[] mouseListeners = paintCanvas.getMouseListeners();
        for (MouseListener mouseListener : mouseListeners)
        {
            paintCanvas.removeMouseListener(mouseListener);

        }
        StartAndendValuePointMode startAndendValuePointMode = stateApp.getActiveStartAndendValuePointMode();

        if (startAndendValuePointMode.equals(StartAndendValuePointMode.DRAW)) 
        {
          
            paintCanvas.addMouseListener(new MouseDrawAdapter(stateApp, listShape, shapeConfig));

        } else if (startAndendValuePointMode.equals(StartAndendValuePointMode.SELECT)) 
        {
          
            paintCanvas.addMouseListener(new MouseSelectAdapter(stateApp, listShape, shapeConfig));
        } else if (startAndendValuePointMode.equals(StartAndendValuePointMode.MOVE)) 
        {
           
            paintCanvas.addMouseListener(new MouseMoveAdapter(stateApp, listShape, shapeConfig));
        } else
        {
        }
    }
}
