package view.interfaces;
import model.ShapeType;
import model.ShapeShadingType;


public interface IShadedShape {
	// invoke methods
    void drawShadedShape();

    ShapeShadingType shapeShadingType();

    ShapeType getShapeType();

}
