package se.pbt.iths.shapesfx.models.shapes;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.pbt.iths.shapesfx.models.shapes.polygonal.Square;
import se.pbt.iths.shapesfx.models.shapes.polygonal.Triangle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static se.pbt.iths.shapesfx.interfaces.Rotatable.ROW_X;
import static se.pbt.iths.shapesfx.interfaces.Rotatable.ROW_Y;

@DisplayName("VertexBasedShape tests:")
class VertexBasedShapeTest {

    @Nested
    @DisplayName("toSvgPath method:")
    class ToSvgPathTests {

        @Test
        @DisplayName("Converts vertices of a Square to SVG path")
        void convertSquareVerticesToSvgPath() {
            var shape = new Square("MySquare", Color.RED, 10);

            double[][] vertices = new double[2][4];
            vertices[ROW_X] = new double[]{0, 10, 10, 0};
            vertices[ROW_Y] = new double[]{0, 0, 10, 10};
            shape.setVertices(vertices);

            String expectedPath = "M 0.000000 0.000000 L 10.000000 0.000000 L 10.000000 10.000000 L 0.000000 10.000000 Z";

            String svgPath = shape.toSvgPath();

            assertEquals(expectedPath, svgPath);
        }

        @Test
        @DisplayName("Converts vertices of a Triangle to SVG path")
        void convertTriangleVerticesToSvgPath() {
            var shape = new Triangle("MyTriangle", Color.BLUE, 10);

            double[][] vertices = new double[2][3];
            vertices[ROW_X] = new double[]{5, 15, 10};
            vertices[ROW_Y] = new double[]{5, 5, 15};
            shape.setVertices(vertices);

            String expectedPath = "M 5.000000 5.000000 L 15.000000 5.000000 L 10.000000 15.000000 Z";

            String svgPath = shape.toSvgPath();

            assertEquals(expectedPath, svgPath);
        }


        @Test
        @DisplayName("Converts vertices of shape with invalid vertices to SVG path")
        void convertInvalidVerticesToSvgPath() {
            var shape = new Square("MySquare", Color.RED, 10);

            double[][] invalidVertices = new double[2][2]; // Invalid number of vertices
            invalidVertices[ROW_X] = new double[]{0, 10};
            invalidVertices[ROW_Y] = new double[]{0, 10};
            shape.setVertices(invalidVertices);

            assertThrows(IndexOutOfBoundsException.class, shape::toSvgPath);
        }
    }
}
