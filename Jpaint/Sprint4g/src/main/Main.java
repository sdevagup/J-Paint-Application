package main;



//Import the applet  
import java.applet.*;           
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import controller.RedoOperation;
import controller.UndoOperation;

    

//Create the ObjectDraw class extends the Applet 

public class Main extends Applet
{
	 
	// Set the graphics 
Graphics graphics_img;
// Set the image graphics
Image img; 


// Set the shape all vector
Vector shapesAll = new Vector();          
//present shape
Shape shape_Present;                       
                        

boolean series;                       
Vector curve;
//The mouse pressed movement
int x_down, y_down;                         
                        
//Set the grid size 
int grid_size = 1; 
// Set the maximum grid size
int maxgrid_size = 64;                         

definition_table descripe;                    

// Frame description.
Frame_describe descripeFrame;        


//Set the background color
static public Color bgColor = Color.white, groupColor   = Color.black;
//Set the font style
static public Font font_style = new Font("Helvetica", Font.BOLD, 18);

// Slider grid
Class_slider Slider_grid = new Class_slider(this, " ", font_style, 1, 1, 64, 2);
//Set the Choice menu for shape
Choice choice_Shape = new Choice();       
// Default shape is Rectangle. 
int chose_shape = Rectangle;  
// Draw the shape
int draw_shape = chose_shape;              
                                         
// Choice menu for draw 
Choice Choice_menu = new Choice();         
int choose_mode = DRAW;                    
// Choice menu for color 
Choice color_menu = new Choice();        
Color color_choose = Color.magenta;          
Vector colorAll = new Vector();          
// Choice menu for fill
Choice menu_fill = new Choice();         
boolean mode_fill = false;


// Set the mode of array is draw,  move, group and ungroup.

static String mode_array[] = { "Draw", "Move", "Group", "Ungroup"};

// Set the number for the mode.

static final int DRAW = 0, MOVE = 1,  GROUP = 2, UNGROUP = 3;


// Set the shape like Rectangle, curve, line and Ellipse.
static String shape_array[] = {"Rectangle", "Curve",  "Line", "Ellipse"};
// Set the number for shape
static final int Rectangle = 0,  CURVE = 1, LINE = 2, Ellipse = 3;

// Initiate the applet
public void init()
 {
	  // Set the layout is left
 setLayout(new FlowLayout(FlowLayout.LEFT)); 
 // Set the background color.
 setBackground(bgColor);             
 // Set the image size
 img = createImage(size().width, size().height);   
 graphics_img = img.getGraphics();             
 

	
 // set the font

 

 Choice_menu.setFont(font_style);  
 
 
 for( int i = 0; i < mode_array.length; i++ )      
 	// Add the mode items
   Choice_menu.addItem(mode_array[i]);
 setmode_array(choose_mode);
 
 
 ButtonHandler buttonHandler = new ButtonHandler();
 
// add choice menu to applet
 add(Choice_menu);	
 
//Add the shape item
 choice_Shape.setFont(font_style);              
 for( int i = 0; i < shape_array.length; i++ )     
   choice_Shape.addItem(shape_array[i]);
 // Add shape menu to applet
 add(choice_Shape);				
//Set the font
 menu_fill.setFont(font_style);
 // Add the fill items
 menu_fill.addItem("NoFill");
 
 menu_fill.addItem("Fill");
 
// Add fill menu to applet
 add(menu_fill);				
//Add the color 
 color_menu.setFont(font_style);    
 
 addColor("Magenta",   Color.magenta);
 addColor("Green",     Color.green);
 addColor("Cyan",      Color.cyan);    
 addColor("LightGray", Color.lightGray);
 addColor("Pink",      Color.pink);
 addColor("White",     Color.white);
 addColor("Black",     Color.black);         
 addColor("Red",       Color.red);           
 addColor("Blue",      Color.blue);
 addColor("Orange",    Color.orange);
 addColor("Yellow",    Color.yellow);
// add color menu to applet
 add(color_menu);
 GroupDraw.main(null);
 }
	    
private class ButtonHandler implements ActionListener
{
   // handles button events
   public void actionPerformed( ActionEvent event )
   {
       if (event.getActionCommand().equals("Undo")){
     	 UndoOperation uo = new UndoOperation();
     	 uo.run();
       }
       else if (event.getActionCommand().equals("Redo")){
    	   RedoOperation ro = new RedoOperation();
    	   ro.run(); 
       }
       else if (event.getActionCommand().equals("Exit")){
     	  System.exit(0);
       }
        
   } // end method actionPerformed
}	
// graphics clear method

void graphics_clear()
 {
	  graphics_img.clearRect(0, 0, size().width, size().height);
 }


// display function used to display the all shape in window

void display()
 {
 for( Enumeration e = shapesAll.elements(); e.hasMoreElements(); )
   {
 	// Draw the image
   ((Shape)e.nextElement()).draw(graphics_img);
   }
 // Default color is black
 graphics_img.setColor(Color.magenta);
 // Set the image size
 graphics_img.drawRect(0, 0, size().width-1, size().height-1);	
 repaint();
 }


//display_function used to redraw the all image

void display_function()
 {
	  // Invoke the mehods
 graphics_clear();
 display();
 }


//Draw the new shape
void shapeNew(int x, int y, Color color, boolean mode_fill)
 {
	  // there are four shape is present such as Rectangle, curve, line and Ellipse 
 switch( chose_shape )
   {
   case Rectangle:   shape_Present = new Rectangle(x, y, color, mode_fill); 
   break;

   case CURVE: shape_Present = new Line(x, y, color);           
   break;

   case LINE:  shape_Present = new Line(x, y, color);          
   break;
   
   case Ellipse:  shape_Present = new Ellipse(x, y, color, mode_fill); 
   break;

   
   }
 //Invoke the methods
 display_function();
 shape_Present.draw(graphics_img);
 repaint();    
 }


// Increase the current shape size  

void shape_growth(int x, int y)
 {
 display_function();
 // Adjust the shape size
 shape_Present.width = x - shape_Present.x;   
 shape_Present.height = y - shape_Present.y;
 shape_Present.draw(graphics_img);
 repaint();
 }


//Completed shape

void finishShape()
 {
	  shape_Present.complete();
 shapesAll.addElement(shape_Present);
 }


//Move the shape using mouse 

void moveShape(int x, int y)
 {
 if( shape_Present == null )
   return;
 // Set the location
 shape_Present.moveBy(x - x_down, y - y_down);
 x_down = x;
 y_down = y;
 }


//Set the mode

void setmode_array(int i)
 {
 switch( i )
   {
   
   
   }
 choose_mode = i;
 Choice_menu.select(mode_array[i]);
 }


//Find the shape.

boolean findShape(int x, int y)
 {
 for (int i = shapesAll.size()-1; i >= 0; i-- )
   {
   Shape s = (Shape)shapesAll.elementAt(i);
   if( s.inside(x, y) )
     {
 	  shape_Present = s;
 	  // Return true when found the shape 
     return true;
     }
   }
 shape_Present = null;
 // Return false when not found the shape
 return false;
 }







//Create the group element

void Group(int minSize)
 {
	  // establish the Group
 Vector ele = generateGroup(shape_Present);
 if( ele.size() < minSize )
   {
   // Print the warning message
   System.out.println("Group size " + ele.size() + " not created");
   shape_Present = null;
   return;
   }

 
 for( Enumeration e = ele.elements(); e.hasMoreElements(); )
 	// Remove the element in Group 
 	shapesAll.removeElement(e.nextElement());


 shape_Present = new Group(shape_Present.x, shape_Present.y, this, ele);
 //Add the element
 shapesAll.addElement(shape_Present);
 }


// Create the ungroup function

void unGroup()
 {
 if( shape_Present == null )
   return;
 if( !(shape_Present instanceof Group) )
   return;
 // Remove the element
 shapesAll.removeElement(shape_Present);

 Group Group = (Group)shape_Present;

 if( Group.shared )
   {
   
   Group = Group.deepCopy();
   }

 

 for( Enumeration e = Group.ele.elements(); e.hasMoreElements();)
   {
   Shape s = ((Shape)e.nextElement());
   s.moveBy(shape_Present.x, shape_Present.y);
   // Add the element
   shapesAll.addElement(s);
   }
     


 shape_Present = null;
 }


//Generate the group

Vector generateGroup(Shape sh)
 {
 Vector vect = new Vector();
 for( Enumeration ele = shapesAll.elements(); ele.hasMoreElements(); )
   {
   Shape shape = (Shape)ele.nextElement();
   // check whether t is inside boundary of s
   if(    shape.x >= sh.x && shape.y >= sh.y && shape.x + shape.width  <= sh.x + sh.width && shape.y + shape.height <= sh.y + sh.height )
     {
 	  // Add the element
     vect.addElement(shape);        
     }
   }
 return vect;
 }


// group all function

void Group_all()
 {
	  shape_Present = new Rectangle(0, 0, groupColor, false);
	  shape_Present.width = size().width;
	  shape_Present.height = size().height;
 }



// mouse down is press the mouse

public boolean mouseDown(Event e, int x, int y)
 {
 x = (x/grid_size)*grid_size;
 y = (y/grid_size)*grid_size;
 x_down = x; y_down = y;
//Choose the mode like move, draw, and group
 switch( choose_mode )
   {
   case MOVE:
        findShape(x, y);
        break;

   case DRAW:
 	  series = false;
        chose_shape = draw_shape;
        if( chose_shape == CURVE )
          {
     	   series = true;
          curve = new Vector();
          }
        shapeNew(x, y, color_choose, mode_fill);
        break;

   case GROUP:     
        chose_shape = Rectangle;
        shapeNew(x, y, groupColor, false);
        break;

   
   }
 return true;
 }


// Drag the mouse

public boolean mouseDrag(Event e, int x, int y)
 {
 x = (x/grid_size)*grid_size;
 y = (y/grid_size)*grid_size;
 // Choose the mode for drag the mouse
 switch( choose_mode )
   {
   case MOVE:
        moveShape(x, y);
        display_function();
        break;

   case DRAW:
        if( series )
          {
          
          shape_growth(x, y);
          // invoke finish shape
          finishShape();
          //Add element
          curve.addElement(shape_Present);
          shapeNew(x, y, color_choose, mode_fill);
          }
        else
          {
          shape_growth(x, y);
          }
        break;

   case GROUP:
        shape_growth(x, y);
        break;
   }
 return true;
 }


// mouse up is released the mouse

public boolean mouseUp(Event v, int x, int y)
 {
 x = (x/grid_size)*grid_size;
 y = (y/grid_size)*grid_size;
 // Choose the mouse
 switch( choose_mode )
   {
   case MOVE:
 	  shape_Present = null;
        break;

   case DRAW:
        finishShape();
        if( series )
          {
          // finish the curve
          curve.addElement(shape_Present);
          shape_Present = 
            new Group(shape_Present.x, shape_Present.y, this, curve);

         
          for( Enumeration ele = curve.elements(); ele.hasMoreElements(); )
         	 // remove the element
         	 shapesAll.removeElement(ele.nextElement());
//Add element
          shapesAll.addElement(shape_Present);
          }
        break;
//the  group and ungroup are performed
   case GROUP:
        Group(2);
        break;

   case UNGROUP:
        findShape(x, y);
        unGroup();
        break;

   
   }

 display_function();
 return true;
 }


//Handle the action

public boolean action(Event event, Object arg)
 {
 

  if( event.target == Choice_menu )
   {
   setmode_array(findString((String)arg, mode_array));
   }

 else if( event.target == choice_Shape )
   {
 	// Set the shape
   draw_shape = chose_shape = findString((String)arg, shape_array);
   setmode_array(DRAW);            
   }

 else if( event.target == menu_fill )
   {
 	// Set the fill mode
   mode_fill = ((String)arg == "Fill" );
   setmode_array(DRAW);            
   }

 else if( event.target == color_menu )
   {
 	// Set the color
   color_choose = colorFromString((String)arg);
   setmode_array(DRAW);            
   }

 return super.action(event, arg);    
 }


//update function

public void update(Graphics g)
 {
 paint(g);
 }



//paint method
public void paint(Graphics g)
 {
	
	  // draw image and set the default black color  
 g.drawImage(img, 0, 0, null);
 g.setColor(Color.magenta);
 
 }



// add the color menu

void addColor(String name, Color color)
 {
	  // Add item
 color_menu.addItem(name);
 // Add element
 colorAll.addElement(new group_Class(name, color));
 }

 
// Return the color

public Color colorFromString(String string)
 {
 for( Enumeration e = colorAll.elements(); e.hasMoreElements(); )
  {
  group_Class p = (group_Class)(e.nextElement());
  if( string.equals(p.fir) )
    return (Color)(p.sec);
  }
//Print the message
 System.err.println("Color not understood: " + string + " using black");
 // Return black color
 return Color.black;
 }


// return the color string

public String colorToString(Color color)
 {
 for( Enumeration e = colorAll.elements(); e.hasMoreElements(); )
  {
  group_Class p = (group_Class)(e.nextElement());
  if( color.equals(p.sec) )
    return (String)(p.fir);
  }
 return color.toString();
 }


static int findString(String toBeFound, String array[])
 {
 for( int i = 0; i < array.length; i++ )
   {
   if( toBeFound.equals(array[i]) )
     return i;
   }
 return -1;
 }


} 


//Create the abstract for shape class

abstract class Shape extends java.awt.Rectangle
{
Color color;         

boolean filled;      

// Create constructor

Shape(int x, int y, Color color, boolean filled)
 {
 
 this.color = color;
 this.filled = filled;
 this.x = x;
 this.y = y;
 }

Shape(Shape original)  	
 {
 
 width = original.width;
 height = original.height;
 
 x = original.x;
 y = original.y;
 color = original.color;
 filled = original.filled;
 }


// Shape move

void moveBy(int dx, int dy)
 {
 x += dx;
 y += dy;
 }


// Complete shape

void complete()
 { }
//Copy shape
abstract Shape copy();                        
abstract void draw(int x, int y, Graphics g); 
abstract void describe(int indentation, StringBuffer buff, Main app);

void draw(Graphics g)                       
 {
 draw(0, 0, g);
 }
//describe function
void describe(StringBuffer buff, Main app) 
 {
 describe(0, buff, app);
 } 
//indentation function
static void indentation(int indent, StringBuffer buff)
 {
 for( int i = 0; i < indent; i++ )
   buff.append(" ");
 }
}  


//Create the Rectangle shape

class Rectangle extends Shape
{
Rectangle(int x, int y, Color color, boolean filled)
 {
 super(x, y, color, filled);
 }

Rectangle(Rectangle Rectangle)                  
 {
 super((Shape)Rectangle);
 }
//Copy the shape
Shape copy()
 {
 return new Rectangle(this);
 }
//Draw the shape
void draw(int x, int y, Graphics g)       
 {

	int width = this.width;     
	int height = this.height;
 x = this.x + x;            
 y = this.y + y;


//manage negative width and height
 if( width < 0 )             
   {
   width = -width;
   x -= width;
   }
 if( height < 0 )
   {
   height = -height;
   y -= height;
   }
//Set the color
 g.setColor(color);
 // Fill the shape
 if( filled )
   g.fillRect(x, y, width, height);    
 else
   g.drawRect(x, y, width, height);    
 }

// Complete the shape 

void complete()
 {
 if( width < 0 )
   {
   x += width;
   width = -width;
   }
 if( height < 0 )
   {
   y += height;
   height = -height;
   }
 }
//Describe the shape
void describe(int indent, StringBuffer buff, Main app)
 {
	  indentation(indent, buff);
	  // Append the shape
 buff.append("(" + (filled ? "filled " : "")+ "rectangle " + app.colorToString(color) + " "+ x + " " + y + " "+ width + " " + height + ")\n"); 
 }
} 


//Create the Ellipse shape

class Ellipse extends Shape
{
Ellipse(int x, int y, Color color, boolean filled)
 {
 super(x, y, color, filled);
 }

Ellipse(Ellipse Ellipse)               
 {
 super((Shape)Ellipse);
 }
//Copy shape
Shape copy()
 {
 return new Ellipse(this);
 }
//Draw the shape
void draw(int x, int y, Graphics g)       
 {

//Set the location
	  
	int width = this.width;     
	int height = this.height;
 x = this.x + x;            
 y = this.y + y;



 if( width < 0 )             
   {
   width = -width;
   x -= width;
   }
 if( height < 0 )
   {
   height = -height;
   y -= height;
   }
//Set the color
 g.setColor(color);
 // Fill the Ellipse
 if( filled )
   g.fillOval(x, y, width, height);    
 else
 	// draw the Ellipse
   g.drawOval(x, y, width, height);    
 }

// Complete Ellipse shape

void complete()
 {
 if( width < 0 )
   {
   x += width;
   width = -width;
   }
 if( height < 0 )
   {
   y += height;
   height = -height;
   }
 }
//describe the Ellipse
void describe(int indent, StringBuffer buff, Main app)
 {
	  indentation(indent, buff);
 buff.append("(" + (filled ? "filled " : "")
                 + "Ellipse " + app.colorToString(color) + " "
                 + x + " " + y + " "
                 + width + " " + height + ")\n"); 
 }
}  


//line function

class Line extends Shape
{
Line(int x, int y, Color color)
 {
 super(x, y, color, false);	        
 }

Line(Line line)                       
 {
 super((Shape)line);
 }
//Copy the line
Shape copy()
 {
 return new Line(this);
 }
//Draw the line
void draw(int x, int y, Graphics g)              
 {
	  // Set the color
 g.setColor(color);
 g.drawLine(this.x + x, this.y + y, this.x+x+width, this.y+y+height);    
 }


// Set the width and height 

public boolean inside(int x, int y)
 {
 int x0 = this.x;
 int y0 = this.y;
 int width = this.width;
 int height = this.height;
 if( width < 0 )
   {
   width = -width;
   x0 -= width;
   }
 if( height < 0 )
   {
   height = -height;
   y0 -= height;
   }
 return  x >= x0
      && x <  x0 + width 
      && y >= y0 
      && y <  y0 + height;
 }

//Describe the line
void describe(int indent, StringBuffer buff, Main app)
 {
	  indentation(indent, buff);
	  // Append the line
 buff.append("(line " + app.colorToString(color) + " " + x + " " + y + " " + width + " " + height + ")\n"); 
 }
}  


//Create the group

class Group extends Shape
{
Vector ele;                       

boolean shared = false;               

Group(int x, int y, Main app, Vector shapes)
 {
 super(x, y, null, false);	       
 ele = initele(shapes, app.shape_Present);
 }

Group(Group group)                    
 {                                   
 super((Shape)group);
 ele = group.ele;
 shared = group.shared = true;       
 }

Shape copy()
 {
 return new Group(this);
 }


Group deepCopy()
 {
 Group result = (Group)copy();
 Vector old = result.ele;
 result.ele = new Vector();
 for( Enumeration e = old.elements(); e.hasMoreElements(); )
   {
   result.ele.addElement(((Shape)e.nextElement()).copy());
   }
 return result;
 }


// initiate the element.

Vector initele(Vector ele, Shape wrapper)
 {
 Vector v = new Vector();
 
// Find the max and min value
 int x_min = x+wrapper.width, y_min = y+wrapper.height, xmax = -1, ymax = -1;
 for( Enumeration e = ele.elements(); e.hasMoreElements(); )
   {
   Shape s = (Shape)e.nextElement();

   if( s.width >= 0 )
     {
     x_min = Math.min(x_min, s.x);
     xmax = Math.max(xmax, s.x+s.width-1);
     }
   else
     {
     x_min = Math.min(x_min, s.x+s.width+1);
     xmax = Math.max(xmax, s.x);
     }
             
   if( s.height >= 0 )
     {
     y_min = Math.min(y_min, s.y);
     ymax = Math.max(ymax, s.y+s.height-1);
     }
   else
     {
     y_min = Math.min(y_min, s.y+s.height+1);
     ymax = Math.max(ymax, s.y);
     }
   }

 x = x_min; 
 y = y_min;
 width  = xmax - x_min + 1; 
 height = ymax - y_min + 1;


 for( Enumeration e = ele.elements(); e.hasMoreElements(); )
   {
   Shape s = (Shape)e.nextElement();
   s.x -= x_min;
   s.y -= y_min;
   // Add elements
   v.addElement(s);
   }
 return v;
 }


// Draw function

void draw(int x, int y, Graphics g)
 {
 for( Enumeration e = ele.elements(); e.hasMoreElements(); )
   {
   ((Shape)e.nextElement()).draw(this.x + x, this.y + y, g);
   }
 }
//Describe the draw
void describe(int indent, StringBuffer buff, Main app)
 {
	  indentation(indent, buff);
 buff.append("(group " + x + " " + y);
 if( shared )
   {
   int i = app.descripe.get_index(ele);
   buff.append(" (ref " + i +"))\n");
   }
 else
   {
 	// Append 
   buff.append("\n");
   for( Enumeration e = ele.elements(); e.hasMoreElements(); )
     {
     ((Shape)(e.nextElement())).describe(indent+2, buff, app);
     }
   indentation(indent, buff);
   buff.append(")\n");
   }
 }
}  


//Create group class

class group_Class
{
Object fir;
Object sec;

group_Class(Object fir, Object sec)
 {
 this.fir = fir;
 this.sec = sec; 
 }
}  


//Definition table is table function

class definition_table extends java.util.Vector
{
// get table index value

int get_index(Vector ele)
 {
 int i = indexOf(ele);
 if( i < 0 )
   {
   i = size();
  // Add the element
   addElement(ele);
   }
 return i;
 }


//Describe the all definition

void describe(StringBuffer buff, Main app)
 {
 int i = 0;
 for( Enumeration e = elements(); e.hasMoreElements(); )
   {
 	// append definition
   buff.append("(def " + i +"\n");
   describe1(i++, (Vector)e.nextElement(), buff, app);
   buff.append(")\n");
   }      
 }


// Display the one definition 

void describe1(int i, Vector V, StringBuffer buff, Main app)
 {
 for( Enumeration e = V.elements(); e.hasMoreElements(); )
   {
   ((Shape)e.nextElement()).describe(2, buff, app);
   }      
 }
} 


/**
*  A DescriptionFrame is a frame containing text descriptions
**/

class Frame_describe extends Frame
{
	// Set the string buffer
StringBuffer shape;                           
StringBuffer defs;                            
//Set the text area
TextArea ta = new TextArea();


UndoManager manager = new UndoManager();


//Create the clear button
Button button_clear = new Button("Clear");     
//Choose the font
Choice Choose_font = new Choice();
//Set the font size
static int fontSize[] = {8, 10, 12, 14, 18, 24}; 

Frame_describe(Main app)              
 {
 super("Description");
 setLayout(new FlowLayout(FlowLayout.CENTER));
 Button b1;
	Button b2, b3;
 manager = new UndoManager();
	b1 = new Button("Undo");
	b2 = new Button("Redo");
	b3 = new Button("Exit");
	b1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				manager.undo();
			} catch (Exception ex) {
			}
		}
	});
	b2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				manager.redo();
			} catch (Exception ex) {
			}
		}
	});
	b3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	

	add(b1);
	add(b2);
	add(b3);
	
	setVisible(true);
	pack();
 
 add(button_clear);

 
 for( int i = 0; i < fontSize.length; i++ )
 	// add the font item
   Choose_font.addItem(new Integer(fontSize[i]).toString());

 Choose_font.select("18");
 ta.setFont(new Font("Helvetica", Font.BOLD, 18));
 Choose_font.setFont(app.font_style);   
 add(Choose_font);

 // establish textArea
 setBackground(app.bgColor);
 add(ta);

 resize(app.size().width, 2*app.size().height/3);
 button_clear.setFont(app.font_style);  
 }
//Clear the text 
void clearText()
 {
 shape = new StringBuffer();
 defs = new StringBuffer();
 }
//update function
void update()
 {
 ta.appendText(defs.toString());
 ta.appendText(shape.toString());
 show();
 }

public boolean action(Event event, Object arg)
 {
 if( event.target == button_clear )
   {
 	// Clear the text
   ta.setText("");             
   }

 else if( event.target == Choose_font )
   {
   ta.setFont(new Font("Helvetica",Font.BOLD,new Integer((String)arg).intValue()));
   }

 return super.action(event, arg);    
 }
}  


//set the label,

class Class_slider
{
double Value;                

Label label;                  
         



Class_slider(Applet app, String lab,Font font,double initial,int min, int max,int fieldSize)         
 {
	  
 label = new Label(lab);
 label.setFont(font);
 app.add(label);             

 this.Value = initial;       

           
 }




} 
