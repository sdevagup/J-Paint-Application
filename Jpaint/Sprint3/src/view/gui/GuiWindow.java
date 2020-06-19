package view.gui;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.*;
import javax.swing.border.*;

import view.interfaces.IGuiWindow;
import view.nameEvent;

import java.awt.*;

public class GuiWindow extends JFrame implements IGuiWindow 
{
    private final int fulfillWidth = 1300;
    private final int fulfillHeight = 800;
    private final String fulfillTitle = "JPaint";
    private final Insets fulfillButtonDimensions  = new Insets(2, 5, 2, 5);
    private final Map<nameEvent, JButton> buttonEvent = new HashMap<>();
    private final PaintCanvas canvas;
// gui window list
    public GuiWindow(PaintCanvas canvas) 
    {
    	// set the title and size
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(fulfillTitle);
        setSize(fulfillWidth, fulfillHeight);
        JPanel panelWindow = createWindow();
        this.canvas = canvas;
        panelWindow.add(canvas, BorderLayout.CENTER);
        validate();
    }
// get button
    @Override
    public JButton getButton(nameEvent nameEvent)
    {
        if (!buttonEvent.containsKey(nameEvent))
            throw new NoSuchElementException("No button exists for action " + nameEvent.toString());

        return buttonEvent.get(nameEvent);
    }
// create jpanel
    private JPanel createWindow()
    {
        JPanel contentPane = createBackgroundPanel();
        JPanel buttonPanel = createMenu();
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        return contentPane;
    }
// create menu
    private JPanel createMenu()
    {
        JPanel buttonPanel = createButtonPanel();

        for (nameEvent nameEvent : nameEvent.values())
        {
            addButtonToPanel(nameEvent, buttonPanel);
        }

        return buttonPanel;
    }
// addbutton to panel
    private void addButtonToPanel(nameEvent nameEvent, JPanel panel) 
    {
        JButton newButton = createButton(nameEvent);
        buttonEvent.put(nameEvent, newButton);
        panel.add(newButton);
    }
// create the button
    private JButton createButton(nameEvent nameEvent)
    {
        JButton newButton = new JButton(nameEvent.toString());
        newButton.setForeground(Color.BLACK);
        newButton.setBackground(Color.WHITE);
        newButton.setBorder(createButtonBorder());
        return newButton;
    }
// create the button border
    private Border createButtonBorder() 
    {
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(fulfillButtonDimensions);
        return new CompoundBorder(line, margin);
    }
// create button panel
    private JPanel createButtonPanel()
    {
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.setBackground(Color.WHITE);
        return panel;
    }
// create background
    private JPanel createBackgroundPanel() 
    {
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        return contentPane;
    }
}
