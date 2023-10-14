package se.pbt.iths.doodledo.utils;

// TODO: Add exception handling

import javafx.geometry.Point2D;

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
     * @param centerPoint the center point of the shape
     * @param degrees  The angle of rotation in degrees. Positive values rotate counterclockwise, negative values rotate clockwise.
     * @return A new 2D array containing the rotated x and y coordinates of the vertices.
     */
    public double[][] rotatePoints(double[][] vertices, Point2D centerPoint, double degrees) {
        int numVertices = vertices[0].length;
        var rotatedX = new double[numVertices];
        var rotatedY = new double[numVertices];

        // Convert degrees to radians because trigonometric functions in Java expect angles to be in radians.
        double radians = Math.toRadians(degrees);

        for (int i = 0; i < numVertices; i++) {
            // Adjust the coordinates to place the center of rotation at the origin (0, 0).
            double x = vertices[0][i] - centerPoint.getX();
            double y = vertices[1][i] - centerPoint.getY();

            // Rotate the shifted point around the origin using trigonometric functions.
            double rotatedXComponent = x * Math.cos(radians) - y * Math.sin(radians);
            double rotatedYComponent = x * Math.sin(radians) + y * Math.cos(radians);

            // Revert the shifted point back to its original coordinate system by adding the center coordinates.
            rotatedX[i] = centerPoint.getX() + rotatedXComponent;
            rotatedY[i] = centerPoint.getY() + rotatedYComponent;
        }
        return new double[][]{rotatedX, rotatedY};
    }
}


