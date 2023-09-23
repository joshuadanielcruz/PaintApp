package PaintApp;

import javafx.scene.paint.Color;

/**
 * Implementation of the Drawable Interface
 * @author Joshua Cruz
 */
public interface Drawable {
    public abstract double getX();
    public abstract double getY();
    public abstract double getHeight();
    public abstract double getWidth();
    public abstract Color getFillColor();
    public abstract Color getStrokeColor();
}
