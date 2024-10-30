package com.gardengui.gardengui;

/*
 * App.java introduces the importations and necessary configurations to run a user interface
 * for the complete Garden.java application.
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
import java.io.IOException;
import java.nio.channels.ConnectionPendingException;
import java.util.ArrayList;
import java.util.Scanner;

public class App extends Application {

    // variables that will be read in from file
    private static double delay;
    private static int rows;
    private static int cols;


    // constants for the program
    private final static int TEXT_SIZE = 120;
    private final static int RECT_SIZE = 20;
    private final static int PLOT_SIZE = RECT_SIZE * 5;

    // temporary constants for starter code
    private int SIZE_ACROSS;
    private int SIZE_DOWN;

    private static File newFile;
    private static Scanner scanFile;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        newFile = new File("/home/cloudbong/IdeaProjects/GardenGUI/src/main/java/com/gardengui/gardengui/growBonsai.in");
        scanFile = new Scanner(newFile);

        rows = Integer.parseInt(scanFile.nextLine().split(" ")[1]);
        cols = Integer.parseInt(scanFile.nextLine().split(" ")[1]);
        // added the delay as an initial command too
        delay = Double.parseDouble(scanFile.nextLine().split(" ")[1]);

        SIZE_ACROSS = cols * PLOT_SIZE;
        SIZE_DOWN = rows * PLOT_SIZE;

        TextArea command = new TextArea();
        GraphicsContext gc = setupStage(primaryStage, SIZE_ACROSS, SIZE_DOWN,
                command);

        primaryStage.show();
        simulateGarden(gc, command);
    }

    private ArrayList<String> readFile(String fileName, int rows, int cols, double delay) throws FileNotFoundException {
        File openFile = new File(fileName);
        Scanner scanFile = new Scanner(openFile);
        // initialize garden and columns by iterating first two lines
        rows = Integer.parseInt(scanFile.nextLine().split(" ")[1]);
        cols = Integer.parseInt(scanFile.nextLine().split(" ")[1]);
        // added the delay as an initial command too
        delay = Double.parseDouble(scanFile.nextLine().split(" ")[1]);

        ArrayList<String > result = new ArrayList<>();
        while(scanFile.hasNextLine()){
            result.add(scanFile.nextLine());
        }
        return result;
    }

    private void simulateGarden(GraphicsContext gc, TextArea command) throws FileNotFoundException {

        // The command parser will update the screen and the text based on the commands giving
        // by initiating a Garden.java instance inside itself in which the commands are meant to run
        Color backgroundColor = Color.rgb(87,74,53,1);
        CommandParser newGardenCommands = new CommandParser(rows, cols, backgroundColor, gc, PLOT_SIZE, RECT_SIZE);

        // Setup the loop for java fx to start running
        PauseTransition wait = new PauseTransition(Duration.seconds(delay));
        wait.setOnFinished((e) -> {

            if (scanFile.hasNextLine()) {
                // let the command parser take care of the commands.
                String currCommand = scanFile.nextLine();
                if(!currCommand.equals("")) {

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