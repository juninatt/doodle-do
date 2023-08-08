package se.pbt.iths.shapesfx.factory;

import javafx.scene.paint.Paint;
import se.pbt.iths.shapesfx.models.Circle;
import se.pbt.iths.shapesfx.models.ShapeTemplate;
import se.pbt.iths.shapesfx.models.Square;
import se.pbt.iths.shapesfx.models.Triangle;

public class ShapeFactory {

    public static ShapeTemplate createShape(String type, String name, Paint paint, double size) {
        return switch (type.toLowerCase()) {
            case "circle" -> new Circle(name, paint, size);
            case "square" -> new Square(name, paint, size);
            case "triangle" -> new Triangle(name, paint, size);
            default -> throw new IllegalArgumentException("Invalid shape type");
        };
    }
}
