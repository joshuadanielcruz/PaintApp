package PaintApp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Implementation of the Rectangle class.
 * @author Joshua Cruz
 */
public class Rectangle extends GeometricObject{

    /**
     * Rectangle constructor
     * @param x the x-coordinate of the Rectangle
     * @param y the y-coordinate of the Rectangle
     * @param height the height of the Rectangle
     * @param width the width of the Rectangle
     * @param fillColor the fill color of the Rectangle
     * @param strokeColor the stroke color of the Rectangle
     */
    public Rectangle(double x, double y, double height, double width, Color fillColor, Color strokeColor) {
        super(x, y, width, height, fillColor, strokeColor);
    }

    /**
     * Method to draw the Rectangle
     * @param graphicsContext graphics context
     */
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        graphicsContext.strokeRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }
}
