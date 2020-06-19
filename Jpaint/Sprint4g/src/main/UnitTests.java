package main;

import controller.Point;
import model.*;
import model.dialogs.ColorSingleton;
import org.junit.Assert;
import org.junit.Test;
import view.interfaces.IDrawShape;

import java.awt.*;

public class UnitTests
{
// test the shape
    @Test
    public void TestshapeConfig()
    {
// rectangle
        ShapeType rectangle = ShapeType.RECTANGLE;
        //outline
        ShapeShadingType outline = ShapeShadingType.OUTLINE;
        Point startValuePoint = new Point(300, 200);
        Point endValuePoint = new Point(300, 200);

// shape config
        shapeConfig newConfiguration = new shapeConfig();
        newConfiguration.setShapeType(rectangle);
        newConfiguration.setShadingType(outline);
        newConfiguration.setstartValuePoint(startValuePoint);
        newConfiguration.setendValuePoint(endValuePoint);

        Assert.assertEquals(rectangle, newConfiguration.getShapeType());
        Assert.assertEquals(outline, newConfiguration.getShadingType());
        Assert.assertEquals(startValuePoint, newConfiguration.getstartValuePoint());
        Assert.assertEquals(endValuePoint, newConfiguration.getendValuePoint());
    }
// test color
    @Test
    public void TestColorAdapter() 
    {

        shapeColor red = shapeColor.RED;
        shapeColor blue = shapeColor.BLUE;
        Color primaryColor = Color.RED;
        Color secondaryColor = Color.BLUE;


        shapeConfig newConfiguration = new shapeConfig();
        newConfiguration.setPrimaryColor(red);
        newConfiguration.setSecondaryColor(blue);


        Assert.assertEquals(primaryColor, ColorSingleton.getColor(newConfiguration.getPrimaryColor()));
        Assert.assertEquals(secondaryColor, ColorSingleton.getColor(newConfiguration.getSecondaryColor()));
    }
// test shape factory
    @Test
    public void TestShapeFactory() 
    {
        ShapeType rectangle = ShapeType.RECTANGLE;
        ShapeShadingType outline = ShapeShadingType.OUTLINE;
        Point startValuePoint = new Point(300, 200);
        Point endValuePoint = new Point(300, 200);
        shapeColor red = shapeColor.RED;
        shapeColor blue = shapeColor.BLUE;
        Color primaryColor = Color.RED;
        Color secondaryColor = Color.BLUE;

        shapeConfig shapeConfig = new shapeConfig();
        ShapeFactory shape = new ShapeFactory();

        shapeConfig.setShapeType(rectangle);
        shapeConfig.setShadingType(outline);
        shapeConfig.setstartValuePoint(startValuePoint);
        shapeConfig.setendValuePoint(endValuePoint);
        shapeConfig.setPrimaryColor(red);
        shapeConfig.setSecondaryColor(blue);


        IDrawShape testshape = shape.createShape(shapeConfig);


        Assert.assertEquals(rectangle, testshape.getshapeConfig().getShapeType());
        Assert.assertEquals(outline, testshape.getshapeConfig().getShadingType());
        Assert.assertEquals(startValuePoint, testshape.getshapeConfig().getstartValuePoint());
        Assert.assertEquals(endValuePoint, testshape.getshapeConfig().getendValuePoint());
        Assert.assertEquals(primaryColor, ColorSingleton.getColor(testshape.getshapeConfig().getPrimaryColor()));
        Assert.assertEquals(secondaryColor, ColorSingleton.getColor(testshape.getshapeConfig().getSecondaryColor()));

    }
// test list of shape
    @Test
    public void TestlistShape()
    {
        ShapeType rectangle = ShapeType.RECTANGLE;
        ShapeShadingType outline = ShapeShadingType.OUTLINE;
        Point startValuePoint = new Point(300, 200);
        Point endValuePoint = new Point(300, 200);
        shapeColor red = shapeColor.RED;
        shapeColor blue = shapeColor.BLUE;
        Color primaryColor = Color.RED;
        Color secondaryColor = Color.BLUE;

        shapeConfig shapeConfig = new shapeConfig();
        ShapeFactory shape = new ShapeFactory();

        shapeConfig.setShapeType(rectangle);
        shapeConfig.setShadingType(outline);
        shapeConfig.setstartValuePoint(startValuePoint);
        shapeConfig.setendValuePoint(endValuePoint);
        shapeConfig.setPrimaryColor(red);
        shapeConfig.setSecondaryColor(blue);

        listShape listShape = new listShape();
        IDrawShape testshape = shape.createShape(shapeConfig);
        listShape.add(testshape);

        Assert.assertEquals(1, listShape.getlistShape().size());


    }
}
