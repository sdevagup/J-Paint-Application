package main;
// import the all header file
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

// create the class 

public class index extends JFrame {
// Set text area and undo manager
  JTextArea textarea;

  UndoManager unto_manager;

  public static void main(String[] files) {
    new index().setVisible(true);
  }
// constructor
  public index() {
// create the container and jpanel
    Container content_pane = getContentPane();
    content_pane.add(textarea = new JTextArea(20, 60), BorderLayout.CENTER);
    JPanel pane;
    content_pane.add(pane = new JPanel(), BorderLayout.NORTH);
// invoke the undomanager
    unto_manager = new UndoManager();
    textarea.getDocument().addUndoableEditListener(unto_manager);

    // Create the buttons
    JButton button_paste, button_undo, button_redo, button_cut, button_copy, button_delete;
    
 // Create Copy button
    pane.add(button_copy = new JButton("Copy"));
    // Add action listener
    button_copy.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	// invoke Copy method
    	  textarea.copy();
      }
    });
    // Create cut button
    pane.add(button_cut = new JButton("Cut"));
    
    // Add action listener
    button_cut.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  // invoke cut method
        textarea.cut();
      }
    });
 
 // Create paste button
    pane.add(button_paste = new JButton("Paste"));
 // Add action listener
    button_paste.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	// invoke paste method
        textarea.paste();
      }
    });
 // Create delete button
    pane.add(button_delete = new JButton("Delete"));
 // Add action listener
    button_delete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	// Set text clear
        textarea.setText("");
      }
    });
    
 // Create undo button
    pane.add(button_undo = new JButton("Undo"));
 // Add action listener
    button_undo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	// invoke undo method
        if (unto_manager.canUndo()) {
          unto_manager.undo();
        } else {
          warning_message("Can't undo");
        }
      }
    });
 // Create redo button
    pane.add(button_redo = new JButton("Redo"));
 // Add action listener
    button_redo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	// invoke redo method
        if (unto_manager.canRedo()) {
          unto_manager.redo();
        } else {
          warning_message("Can't redo");
        }
      }
    });
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    DrawUndo.main(null);
    

  }
  // Warning message method

  void warning_message(String warn) {
    JOptionPane.showMessageDialog(this, "Warning mesage: " + warn, "Warning",
        JOptionPane.WARNING_MESSAGE);
      }
  
 
}
