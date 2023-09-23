package PaintApp;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Implementation of the PaintApp using a Graphical User Interface.
 * @author Joshua Cruz
 */
public class FXGUITemplate extends Application {
    /** x-coordinate */
    private double x;
    /** y-coordinate */
    private double y;
    /** x-coordinate when mouse press */
    private double pressX;
    /** y-coordinate when mouse press */
    private double pressY;
    /** width of object */
    private double width;
    /** height of object */
    private double height;
    /** Int-value that represents the shape */
    private int shape = 0;
    /** TextFields */
    private TextField textFillR, textFillG, textFillB, textStrokeR, textStrokeG, textStrokeB;
    /** List */
    private final ArrayList<GeometricObject> list = new ArrayList<GeometricObject>();
    /** Canvas */
    Canvas canvas;
    /** Options Canvas */
    Canvas options;
    /** GraphicsContext */
    GraphicsContext graphicsContext;
    /** Options GraphicsContext */
    GraphicsContext optionsGraphicsContext;

    // Private Event Handlers and Helper Methods

    /**
     * Method for the Rectangle Button
     * @param e instance of the ActionEvent class
     */
    private void rectangleButton(ActionEvent e) {
        // the int 0 sets the shape to rectangle
        shape = 0;
    }
    /**
     * Method for the Oval Button
     * @param e instance of the ActionEvent class
     */
    private void ovalButton(ActionEvent e) {
        // the int 1 sets the shape to oval
        shape = 1;
    }

    /**
     * Method for the Clear button
     * @param e instance of the ActionEvent class
     */
    private void clearButton(ActionEvent e) {
        // Remove all shapes from the list
        if (list.size() > 0) {
            list.subList(0, list.size()).clear();
        }
        // Clear the canvas by filling it with white

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, 1000, 450);
    }

    /**
     * Method for when the mouse is pressed.
     * @param me parameter of type 'MouseEvent' that represents event generated when mouse is pressed
     */
    private void pressHandler(MouseEvent me) {
        if (me.getButton().equals(MouseButton.PRIMARY)) {
            // gets x and y coordinates of mouse and stores them to pressX and pressY
            pressX = me.getX();
            pressY = me.getY();
        }
    }

    /**
     * Method for when mouse is dragged
     * @param me parameter of type 'MouseEvent' that represents event generated when mouse is dragged
     */
    private void dragHandler(MouseEvent me) {

        // clears canvas

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, 1000, 450);

        // redraws all the geometric objects in the list

        for (GeometricObject i: list) {
            graphicsContext.setStroke(i.getStrokeColor());
            graphicsContext.setFill(i.getFillColor());
            i.draw(graphicsContext);
        }

        // calculates width, height, x, and y of new shape being drawn

        width = me.getX() - pressX;
        height = me.getY() - pressY;
        x = pressX;
        y = pressY;
        if (me.getX() < pressX) {
            width = pressX - me.getX();
            x = me.getX();
        }
        if (me.getY() < pressY) {
            height = pressY - me.getY();
            y = me.getY();
        }

        // sets stroke and fill colors based on values inputted in the text fields

        try {
            graphicsContext.setStroke(Color.rgb(Integer.parseInt(textStrokeR.getText()), Integer.parseInt(textStrokeG.getText()),
                    Integer.parseInt(textStrokeB.getText())));
            graphicsContext.setFill(Color.rgb(Integer.parseInt(textFillR.getText()),
                    Integer.parseInt(textFillG.getText()), Integer.parseInt(textFillB.getText())));
        } catch (IllegalArgumentException e) {
            // if invalid, warning message is displayed
            System.out.println("Illegal Argument Exception.");
            new Alert(Alert.AlertType.WARNING, "Invalid inputs").showAndWait();
        }

        // draws the object based on its shape

        if (shape == 0) {
            graphicsContext.strokeRect(x, y, width, height);
            graphicsContext.fillRect(x, y, width, height);
        } else if (shape == 1) {
            graphicsContext.strokeOval(x, y, width, height);
            graphicsContext.fillOval(x, y, width, height);
        }
    }

    /**
     * Method for when mouse press is released. It adds the new drawn shape to the list.
     * @param me parameter of type 'MouseEvent' that represents event generated when mouse is released
     */
    private void releaseHandler(MouseEvent me) {
        if (shape == 0) {
            list.add(new Rectangle(x, y, width, height, Color.rgb(Integer.parseInt(textFillR.getText()),
                    Integer.parseInt(textFillG.getText()), Integer.parseInt(textFillB.getText())),
                    Color.rgb(Integer.parseInt(textStrokeR.getText()), Integer.parseInt(textStrokeG.getText()),
                            Integer.parseInt(textStrokeB.getText()))));
        } else if (shape == 1) {
            list.add(new Oval(x, y, width, height, Color.rgb(Integer.parseInt(textFillR.getText()),
                    Integer.parseInt(textFillG.getText()), Integer.parseInt(textFillB.getText())),
                    Color.rgb(Integer.parseInt(textStrokeR.getText()), Integer.parseInt(textStrokeG.getText()),
                            Integer.parseInt(textStrokeB.getText()))));
        }
        System.out.println(list);
    }

    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1000, 500); // set the size here
        stage.setTitle("Paint App"); // set the window title here
        stage.setScene(scene);

        // 1. Create the model

        // 2. Create the GUI components

        // Create canvases and graphics contexts

        canvas = new Canvas(1000, 440);
        options = new Canvas(1000, 440);
        graphicsContext = canvas.getGraphicsContext2D();
        optionsGraphicsContext = options.getGraphicsContext2D();

        // Fill the canvas with white color

        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, 1000, 450);

        // Create buttons and labels

        Button rectangleButton = new Button("Rectangle");
        Button ovalButton = new Button("Oval");
        Button clearButton = new Button("Clear Canvas");
        Label fill = new Label("Fill");
        Label stroke = new Label("Stroke");
        Label r = new Label("R");
        Label g = new Label("G");
        Label b = new Label("B");
        Label r2 = new Label("R");
        Label g2 = new Label("G");
        Label b2 = new Label("B");


        // Create text fields

        textStrokeR = new TextField();
        textStrokeG = new TextField();
        textStrokeB = new TextField();
        textFillR = new TextField();
        textFillG = new TextField();
        textFillB = new TextField();

        // Set the default values

        textStrokeR.setText("0");
        textStrokeG.setText("0");
        textStrokeB.setText("0");
        textFillR.setText("0");
        textFillG.setText("0");
        textFillB.setText("0");

        // 3. Add components to the root

        root.getChildren().addAll(canvas, textFillR, textFillG, textFillB, textStrokeR, textStrokeG, textStrokeB, rectangleButton, ovalButton, clearButton, fill, r, g, b, r2, b2, g2, stroke);
        root.getChildren().add(options);

        // 4. Configure the components (colors, fonts, size, location)

        // configure the Rectangle button

        rectangleButton.setPrefWidth(120);
        rectangleButton.relocate(50, 450);

        // configure the Oval button

        ovalButton.setPrefWidth(120);
        ovalButton.relocate(180, 450);

        // Configure the Fill inputs

        fill.relocate(317, 453);
        textFillR.setPrefWidth(40);
        textFillR.relocate(345, 450);
        textFillG.setPrefWidth(40);
        textFillG.relocate(395, 450);
        textFillB.setPrefWidth(40);
        textFillB.relocate(445, 450);
        r.relocate(360, 475);
        g.relocate(410, 475);
        b.relocate(460, 475);

        // configure the Stroke inputs

        stroke.relocate(510, 453);
        textStrokeR.setPrefWidth(40);
        textStrokeR.relocate(550, 450);
        textStrokeG.setPrefWidth(40);
        textStrokeG.relocate(600, 450);
        textStrokeB.setPrefWidth(40);
        textStrokeB.relocate(650, 450);
        r2.relocate(565, 475);
        g2.relocate(615, 475);
        b2.relocate(665, 475);

        // configure the clear button
        clearButton.setPrefWidth(150);
        clearButton.relocate(750, 450);

        // 5. Add Event Handlers and do final setup

        // Add event handlers to the options canvas

        options.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
        options.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
        options.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);

        // Add actions to the shape buttons and clear button

        ovalButton.setOnAction(this::ovalButton);
        rectangleButton.setOnAction(this::rectangleButton);
        clearButton.setOnAction(this::clearButton);

        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
