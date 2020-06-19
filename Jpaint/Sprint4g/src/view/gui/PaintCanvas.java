package view.gui;

import model.interfaces.IlistShapeObserver;
import model.interfaces.IlistShapeSubject;
import view.interfaces.IDrawShape;

import javax.swing.JComponent;
import java.awt.*;

public class PaintCanvas extends JComponent implements IlistShapeObserver
{
	// Create variable
    private final IlistShapeSubject listShape;

// constructor
    
    public PaintCanvas(IlistShapeSubject listShape)
    {
        this.listShape = listShape;
        listShape.register(this);
    }
// update function
    @Override
    public void update()
    {
       
        repaint();
    }
// paintComponent method
    @Override
    protected void paintComponent(Graphics g) 
    {
        for (IDrawShape shape : listShape.getlistShape())
        {
            shape.paint(g);
        }
    }

// graphics 2d function
    public Graphics2D getGraphics2D() 
    {
        return (Graphics2D) getGraphics();
    }
}
