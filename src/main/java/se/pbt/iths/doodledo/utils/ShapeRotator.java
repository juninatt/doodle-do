package se.pbt.iths.doodledo.utils;

// TODO: Add exception handling
/**
 * ShapeRotator provides functionality to rotate a set of 2D points around a specified center.
 * The rotation is performed by shifting the points so that the center of rotation is at the origin (0, 0),
 * applying a rotation matrix, and then reverting the shifted points back to their original coordinate system.
 */
public class ShapeRotator {


    /**
     * Rotates a set of vertices around a specified center point.
     *
     * @param vertices A 2D array containing the x and y coordinates of the vertices.
     *                 The first subarray contains the x-coordinates, and the second subarray contains the y-coordinates.
     * @param cx       The x-coordinate of the center of rotation.
     * @param cy       The y-coordinate of the center of rotation.
     * @param degrees  The angle of rotation in degrees. Positive values rotate counterclockwise, negative values rotate clockwise.
     * @return A new 2D array containing the rotated x and y coordinates of the vertices.
     */
    public double[][] rotatePoints(double[][] vertices, double cx, double cy, double degrees) {
        int numVertices = vertices[0].length;
        var rotatedX = new double[numVertices];
        var rotatedY = new double[numVertices];

        // Convert degrees to radians because trigonometric functions in Java expect angles to be in radians.
        double radians = Math.toRadians(degrees);

        for (int i = 0; i < numVertices; i++) {
            // Adjust the coordinates to place the center of rotation at the origin (0, 0).
            double x = vertices[0][i] - cx;
            double y = vertices[1][i] - cy;

            // Rotate the shifted point around the origin using trigonometric functions.
            double rotatedXComponent = x * Math.cos(radians) - y * Math.sin(radians);
            double rotatedYComponent = x * Math.sin(radians) + y * Math.cos(radians);

            // Revert the shifted point back to its original coordinate system by adding the center coordinates.
            rotatedX[i] = cx + rotatedXComponent;
            rotatedY[i] = cy + rotatedYComponent;
        }
        return new double[][]{rotatedX, rotatedY};
    }
}


