package main;

	import java.awt.BorderLayout;
	import java.awt.Component;
	import java.awt.Container;
	import javax.swing.Action;
	import javax.swing.JFrame;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JToolBar;
	import java.awt.Graphics;
	
	import java.awt.event.MouseListener;

	import javax.swing.AbstractAction;
	
	import javax.swing.event.UndoableEditListener;
	import javax.swing.undo.AbstractUndoableEdit;
	import javax.swing.undo.CannotRedoException;
	import java.awt.Polygon;
	import java.awt.event.ActionEvent;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import javax.swing.undo.CannotUndoException;
	import javax.swing.undo.UndoManager;
	import javax.swing.undo.UndoableEditSupport;
// Create class
	public class DrawUndo {
	  public static void main(String args[]) {
		  // Set the frame title draw
	    JFrame java_frame = new JFrame("Draw");
	    java_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // create the undo drawing panel methd
	    UndoableDrawingPanel panel_draw = new UndoableDrawingPanel();
// create the object for UndoManager
	    UndoManager um = new UndoManager();
	    panel_draw.addUndoEditListener(um);
// create object for JToolBar
	    JToolBar bar_tool = new JToolBar();
	    // Add undo helper
	    bar_tool.add(UndoHelper.getUndofunction(um));
	    bar_tool.add(UndoHelper.getRedoFunction(um));
// create the content pane
	    Container con = java_frame.getContentPane();
	    // add the content
	    con.add(bar_tool, BorderLayout.NORTH);
	    con.add(panel_draw, BorderLayout.CENTER);
	    // Set the frame size
	    java_frame.setSize(500, 500);
	   java_frame.setVisible(true);
	  }
	}
// create the class and extends jpanel
	class UndoableDrawingPanel extends JPanel {
	  UndoableEditSupport undoEditSupport = new UndoableEditSupport(this);
// create polygon
	  Polygon poly = new Polygon();
// create UndoableDrawingPanel method
	  public UndoableDrawingPanel() {
		  // create MouseListener
	    MouseListener ml = new MouseAdapter() {
	      public void mouseReleased(MouseEvent mouseEvent) {
	        undoEditSupport.postEdit(new UndoDrawEdit(UndoableDrawingPanel.this));
	        poly.addPoint(mouseEvent.getX(), mouseEvent.getY()); 
	        // invoke repaint method
	        repaint();
	      }
	    };
	    // Add mouse listener
	    addMouseListener(ml);
	  }
// create addUndoEditListener method
	  public void addUndoEditListener(
	      UndoableEditListener undoEdit) {
	    undoEditSupport.addUndoableEditListener(undoEdit);
	  }
// remove the UndoEditListener
	  public void removeUndoEditListener(
	      UndoableEditListener undoEdit) {
	    undoEditSupport.removeUndoableEditListener(undoEdit);
	  }
// Set the polygon
	  public void setPolygon(Polygon polyValue) {
	    poly = polyValue;
	    repaint();
	  }
// get the polygon
	  public Polygon getPolygon() {
	    Polygon result;
	    if (poly.npoints == 0) {
	    	result = new Polygon();
	    } else {
	    	result = new Polygon(poly.xpoints, poly.ypoints,poly.npoints);
	    }
	    return result;
	  }

	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawPolygon(poly);
	  }
	}
// create the UndoHelper class
	class UndoHelper {
// get the undo and redo functions
	  public static Action getUndofunction(UndoManager um, String label) {
	    return new UndoFunction(um, label);
	  }

	  public static Action getUndofunction(UndoManager um) {
	    return new UndoFunction(um, "Undo");
	  }

	  public static Action getRedoFunction(UndoManager um, String label) {
	    return new RedoFunction(um, label);
	  }

	  public static Action getRedoFunction(UndoManager um) {
	    return new RedoFunction(um, "Redo");
	  }
// create UndoRedo class
	  private abstract static class UndoRedo extends AbstractAction {
	    UndoManager undoManager = new UndoManager();

	    String errmsg = "Cannot undo";

	    String errtitle = "Undo Problem";

	    protected UndoRedo(UndoManager um, String name) {
	      super(name);
	      undoManager = um;
	    }
// set the error message
	    public void seterrmsg(String newValue) {
	      errmsg = newValue;
	    }
// set the error title
	    public void seterrtitle(String newValue) {
	      errtitle = newValue;
	    }
// show the message
	    protected void showMsg(Object source) {
	      if (source instanceof Component) {
	    	  // Show the dialog box
	        JOptionPane.showMessageDialog((Component) source, errmsg,errtitle, JOptionPane.WARNING_MESSAGE);
	      } else {
	    	  // print the message
	        System.err.println(errmsg);
	      }
	    }
	  }
// create UndoFunction
	  public static class UndoFunction extends UndoRedo {
	    public UndoFunction(UndoManager um, String name) {
	      super(um, name);
	      seterrmsg("Cannot undo");
	      seterrtitle("Undo Problem");
	    }
// perform action
	    public void actionPerformed(ActionEvent actionEvent) {
	      try {
	        undoManager.undo();
	      } catch (CannotUndoException cannotUndoException) {
	    	  showMsg(actionEvent.getSource());
	      }
	    }
	  }
// create RedoFunction
	  public static class RedoFunction extends UndoRedo {
	    String errmsg = "Cannot redo";

	    String errtitle = "Redo Problem";

	    public RedoFunction(UndoManager um, String name) {
	      super(um, name);
	      seterrmsg("Cannot redo");
	      seterrtitle("Redo Problem");
	    }
// perform action
	    public void actionPerformed(ActionEvent actionEvent) {
	      try {
	        undoManager.redo();
	      } catch (CannotRedoException cannotRedoException) {
	    	  showMsg(actionEvent.getSource());
	      }
	    }
	  }

	}
// create the class UndoDrawEdit
	class UndoDrawEdit extends AbstractUndoableEdit {
	  UndoableDrawingPanel panel;

	  Polygon polygon, savedPolygon;

	  public UndoDrawEdit(UndoableDrawingPanel panel) {
	    this.panel = panel;
	    polygon = panel.getPolygon();
	  }
// Get the name
	  public String getName() {
	    return "Polygon of size " + polygon.npoints;
	  }
// perform redo
	  public void redo() throws CannotRedoException {
	    super.redo();
	    if (savedPolygon == null) {
	      // Should never get here, as super() doesn't permit redoing
	      throw new CannotRedoException();
	    } else {
	      panel.setPolygon(savedPolygon);
	      savedPolygon = null;
	    }
	  }
// perform undo
	  public void undo() throws CannotUndoException {
	    super.undo();
	    savedPolygon = panel.getPolygon();
	    panel.setPolygon(polygon);
	  }
	}


