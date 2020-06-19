package model.dialogs;

import model.shapeColor;

import java.awt.Color;
import java.util.EnumMap;

public final class ColorSingleton 
{

    private static final EnumMap<shapeColor, Color> color = new EnumMap<>(shapeColor.class);
// put the color
    static 
    {


        color.put(shapeColor.BLACK, Color.BLACK);
        color.put(shapeColor.BLUE, Color.BLUE);
        color.put(shapeColor.CYAN, Color.CYAN);
        color.put(shapeColor.DARK_GRAY, Color.DARK_GRAY);
        color.put(shapeColor.GRAY, Color.GRAY);
        color.put(shapeColor.GREEN, Color.GREEN);
        color.put(shapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        color.put(shapeColor.MAGENTA, Color.MAGENTA);
        color.put(shapeColor.ORANGE, Color.ORANGE);
        color.put(shapeColor.PINK, Color.PINK);
        color.put(shapeColor.RED, Color.RED);
        color.put(shapeColor.WHITE, Color.WHITE);
        color.put(shapeColor.YELLOW, Color.YELLOW);

    }

   
// get the color
    public static Color getColor(shapeColor enumColor)
    {


        return color.get(enumColor);


    }

}