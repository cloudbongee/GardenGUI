package com.gardengui.gardengui;

/*
 * App.java introduces the importations and necessary configurations to run a user interface
 * for the complete Garden.java application.
 * @Author: Jaime Meyer Beilis Michel
 * @Since: October 30 2024
 * Part of the code here was given by Adriana Picoral, for the class CSC210, at the University of Arizona.
 */
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

/*
The public class App, inheriting from JavaFX application class, is the main engine for the
Garden application, this code was provided by Adriana Picoral, for the class CSC210 at the university of Arizona,
and was modified by Jaime Meyer Beilis Michel based on the instructions given
 */
public class App extends Application {

    // variables that will be read in from file
    private static double delay;
    private static int rows;
    private static int cols;

    // constants for the program
    private final static int TEXT_SIZE = 120;
    private final static int RECT_SIZE = 20;
    private final static int PLOT_SIZE = RECT_SIZE * 5;
    private int SIZE_ACROSS;
    private int SIZE_DOWN;

    // globalize the file reader and the file scanner for it to be accessed class wide
    private static File newFile;
    private static Scanner scanFile;

    // starts the application
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * The start void is part of the main functionality of the application, it initiates the values
     * and gathers the setup functions described to run the simulation
     * @param primaryStage
     *      A JavaFX object that contains the top level of the classes referent to graphics
     * @throws FileNotFoundException
     *      Error for the inexistence of the given file path
     */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        newFile = new File("/home/cloudbong/IdeaProjects/GardenGUI/src/main/java/com/gardengui/gardengui/test.in");
        scanFile = new Scanner(newFile);
        // the first three lines of the file are meant to represent the information necessary
        rows = Integer.parseInt(scanFile.nextLine().split(" ")[1]);
        cols = Integer.parseInt(scanFile.nextLine().split(" ")[1]);
        // added the delay as an initial command too
        delay = Double.parseDouble(scanFile.nextLine().split(" ")[1]);
        // setup based on the size of the plot, and the size of a rectangle
        SIZE_ACROSS = cols * PLOT_SIZE;
        SIZE_DOWN = rows * PLOT_SIZE;

        TextArea command = new TextArea();
        GraphicsContext gc = setupStage(primaryStage, SIZE_ACROSS, SIZE_DOWN,
                command);

        primaryStage.show();
        simulateGarden(gc, command);
    }

    /**
     *
     * SimulateGarden function contains a lambda function loop created from the wait Pause Transition.
     * This permits running the main functionality of the application, passing the commands read and update based
     * on them
     * @param gc
     *      The window editor information for the javaFX library
     * @param command
     *      Contains the text widget information and tools as provided by the javaFX library
     */
    private void simulateGarden(GraphicsContext gc, TextArea command) {

        // The command parser will update the screen and the text based on the commands giving
        // by initiating a Garden.java instance inside itself in which the commands are meant to run
        Color backgroundColor = Color.rgb(87,74,53,1);
        CommandParser newGardenCommands = new CommandParser(rows, cols, backgroundColor, gc, PLOT_SIZE, RECT_SIZE);

        // Setup the loop for java fx to start running
        PauseTransition wait = new PauseTransition(Duration.seconds(delay));
        wait.setOnFinished((e) -> {

            // only scan if there is another line to be Scanned, otherwise stop
            if (scanFile.hasNextLine()) {
                String currCommand = scanFile.nextLine();
                if(!currCommand.isEmpty()) {
                    // let the command parser take care of the commands
                    newGardenCommands.parse(currCommand, gc, command, PLOT_SIZE, RECT_SIZE);
                }
                wait.playFromStart();
            }
            else wait.stop();

        });

        // Now that the PauseTransition thread is setup, get it going.
        wait.play();
    }

    /**
     * Sets up the whole application window and returns the GraphicsContext from
     * the canvas to enable later drawing. Also sets up the TextArea, which
     * should be originally be passed in empty.
     * Notes: You shouldn't need to modify this method.
     *
     * @param primaryStage
     *            Reference to the stage passed to start().
     * @param canvas_width
     *            Width to draw the canvas.
     * @param canvas_height
     *            Height to draw the canvas.
     * @param command
     *            Reference to a TextArea that will be setup.
     * @return Reference to a GraphicsContext for drawing on.
     */
    public GraphicsContext setupStage(Stage primaryStage, int canvas_width,
                                      int canvas_height, TextArea command) {
        // Border pane will contain canvas for drawing and text area underneath
        BorderPane p = new BorderPane();

        // Canvas(pixels across, pixels down)
        Canvas canvas = new Canvas(SIZE_ACROSS, SIZE_DOWN);

        // Command TextArea will hold the commands from the file
        command.setPrefHeight(TEXT_SIZE);
        command.setEditable(false);

        // Place the canvas and command output areas in pane.
        p.setCenter(canvas);
        p.setBottom(command);

        // title the stage and place the pane into the scene into the stage
        // change this to have your name on it (required for when recording
        // the demonstation video)
        primaryStage.setTitle("Garden.java - Jaime Meyer Beilis Michel ");
        primaryStage.setScene(new Scene(p));

        return canvas.getGraphicsContext2D();
    }

}