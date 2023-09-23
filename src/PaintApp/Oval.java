package PaintApp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Implementation of the Oval class.
 * @author Joshua Cruz
 */
public class Oval extends GeometricObject{

    /**
     * Constructor for Circle class
     * @param x the x-coordinate of the Oval
     * @param y the y-coordinate of the Oval
     * @param height the height of the Oval
     * @param width the width of the Oval
     * @param fillColor the fill color of the Oval
     * @param strokeColor the stroke color of the Oval
     */
    public Oval(double x, double y, double height, double width, Color fillColor, Color strokeColor) {
        super(x, y, width, height, fillColor, strokeColor);
    }

    /**
     * Method to draw the Oval
     * @param graphicsContext graphics context
     */
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.fillOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        graphicsContext.strokeOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

}
