package view.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import view.interfaces.PaintCanvasBase;

public class PaintCanvas extends PaintCanvasBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public PaintCanvas() {
		 
	}
	
    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }
    
    @Override
    public void paint(Graphics g) {
    	    
     	super.paint(g);

    	 int px = Math.min(x1,x2);
         int py = Math.min(y1,y2);
         int pw=Math.abs(x1-x2);
         int ph=Math.abs(y1-y2);
         
         Graphics2D g2 = (Graphics2D)g;  
         g2.setColor(Color.BLUE);
    	 g2.fillRect(px,py,pw,ph);


    }
    
   
    
	public void setStartPoint(int x, int y) {
        this.x1 = x;
        this.y1 = y;
    }

    public void setEndPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
        
       repaint();

    }
}
