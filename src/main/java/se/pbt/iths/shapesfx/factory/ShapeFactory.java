package se.pbt.iths.shapesfx.factory;

import javafx.scene.paint.Paint;
import se.pbt.iths.shapesfx.models.shapes.nonpolygonal.Circle;
import se.pbt.iths.shapesfx.models.shapes.ShapeTemplate;
import se.pbt.iths.shapesfx.models.shapes.polygonal.Square;
import se.pbt.iths.shapesfx.models.shapes.polygonal.Triangle;

/**
 * A factory class to create shape instances based on given parameters.
 * The shapes can be of type circle, square, or triangle.
 */
public class ShapeFactory {

    /**
     * Creates and returns a shape instance of the specified type.
     * The type should match one of the supported shape names (circle, square, triangle).
     *
     * @param type  The type of the shape to create (e.g. "circle", "square", "triangle").
     * @param name  The name of the shape.
     * @param paint The paint used to fill the shape.
     * @param size  The size parameter for the shape, e.g. radius for a circle.
     * @return A new shape instance corresponding to the specified type.
     * @throws IllegalArgumentException If the specified type is not recognized.
     */
    public static ShapeTemplate createShape(String type, String name, Paint paint, double size) {
        return switch (type.toLowerCase()) {
            case "circle" -> new Circle(name, paint, size);
            case "square" -> new Square(name, paint, size);
            case "triangle" -> new Triangle(name, paint, size);
            default -> throw new IllegalArgumentException("Invalid shape type");
        };
    }
}
