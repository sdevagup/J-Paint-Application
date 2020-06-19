package events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import view.gui.PaintCanvas;

public class ShapeMouseListener implements MouseListener,MouseMotionListener {
	
	private PaintCanvas draw;
	public ShapeMouseListener(PaintCanvas draw){
		this.draw = draw;
	}

		public void mouseEntered(MouseEvent me) {

	    }

	    public void mousePressed(MouseEvent me){
	        draw.setStartPoint(me.getX(), me.getY());
	    }

	    public void mouseClicked(MouseEvent me){
	    }


	    public void mouseReleased(MouseEvent me){
		       draw.setEndPoint(me.getX(), me.getY());

	    }
	    public void mouseMoved(MouseEvent me){

	    }
	    public void mouseDragged(MouseEvent me){
	       draw.setEndPoint(me.getX(), me.getY());
	    }


	    public void mouseExited(MouseEvent me){
	    	
	    }
	    
}
