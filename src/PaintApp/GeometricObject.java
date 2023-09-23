package PaintApp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Implementation of the Geometric Object class.
 * @author Joshua Cruz
 */
public abstract class GeometricObject implements Drawable {
    /** x - Coordinate */
    private final double x;
    /** y - Coordinate */
    private final double y;
    /** height */
    private final double height;
    /** width */
    private final double width;
    /** Fill color */
    private final Color fillColor;
    /** Stroke color */
    private final Color strokeColor;

    /**
     * Constructor for the GeometricObject class
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     * @param height the height of the object
     * @param width the width of the object
     * @param fillColor the color of the object
     * @param strokeColor the color of the object
     */
    public GeometricObject(double x, double y, double height, double width, Color fillColor, Color strokeColor) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
    }

    /**
     * Method to get the x-coordinate
     * @return x
     */
    @Override
    public double getX() {
        return x;
    }


    /**
     * Method to get the y-coordinate
     * @return y
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * Method to get the height
     * @return height
     */
    @Override
    public double getHeight() {
        return height;
    }

    /**
     * Method to get the width
     * @return width
     */
    @Override
    public double getWidth() {
        return width;
    }

    /**
     * Method to get the fill color
     * @return fillColor
     */
    public Color getFillColor() {
        return fillColor;
    }


    /**
     * Method to get the stroke color
     * @return strokeColor
     */
    public Color getStrokeColor() {
        return strokeColor;
    }


    /**
     * Method to draw the object
     * @param graphicsContext graphics context
     */
    public void draw(GraphicsContext graphicsContext) {

    }
}
