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

    // temporary constants for starter code
    private final static int SIZE_ACROSS = 800;
    private final static int SIZE_DOWN = 400;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        // TODO: change SIZE_ACROSS to something like
        // num_cols * plot_size * CELLSIZE and SIZE_DOWN
        // to something like num_rows * plot_size * CELLSIZE
        TextArea command = new TextArea();
        GraphicsContext gc = setupStage(primaryStage, SIZE_ACROSS, SIZE_DOWN,
                command);

        primaryStage.show();
        simulateGarden(gc, command);
    }

    /*
    private void runManualCommands(Counter achievedCount, TextArea command) {

        StringBuilder current = new StringBuilder();

        for (CharSequence paragraph : command.getParagraphs()) {
            String stringedParagraph = paragraph.toString();
            current.append(stringedParagraph);
        }

        // compare both strings aren't equal after iteration
        if(!(current.compareTo(achieved) == 0) ){
            // split string based on linebreak
            String[] commands = current.toString().split("\n");

            for(int i = achievedCount.count; i < commands.length; i++) {
                // pass the command to update
                achievedCount.count++;
            }
        }
    }
    */

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



        // Counter sequenceCommands = new Counter(0);
        // Counter achievedCommands = new Counter(0); Necessary in the case of manual input
        // StringBuilder currentCommands = new StringBuilder(); Necessary in the case of manual input

        // Hardcode the testing for the file
        File newFile = new File("/home/cloudbong/IdeaProjects/GardenGUI/src/main/java/com/gardengui/gardengui/test.in");
        Scanner scanFile = new Scanner(newFile);

        // Scan the initial values
        rows = Integer.parseInt(scanFile.nextLine().split(" ")[1]);
        cols = Integer.parseInt(scanFile.nextLine().split(" ")[1]);
        // added the delay as an initial command too
        delay = Double.parseDouble(scanFile.nextLine().split(" ")[1]);

        // The command parser will update the screen and the text based on the commands giving
        // by initiating a Garden.java instance inside itself in which the commands are meant to run
        Color backgroundColor = Color.rgb(87,74,53,1);
        CommandParser newGardenCommands = new CommandParser(rows, cols, backgroundColor);

        PauseTransition wait = new PauseTransition(Duration.seconds(delay));
        wait.setOnFinished((e) -> {

            //==== Code that should be executed after each delay goes in here.
            // read in the next command
            // if there was a command left in the file:
            // * apply that command to the garden
            // * draw to the canvas by calling your gardenDraw(gc)
            // * append the command to the text field, command.appendText(...);
            // * call wait.playFromStart();
            // else:
            // * call wait.stop();

            if (scanFile.hasNextLine()) {
                // let the command parser take care of the commands.
                String currCommand = scanFile.nextLine();
                if(!currCommand.equals("")) {
                    newGardenCommands.parse(currCommand, gc, command);
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

    // Helper method to draw a rectangular tile and output info in given
    // text area.
    private void drawTileDebug(TextArea command, GraphicsContext gc,
                               String colorname, int x, int y, int size) {
        command.appendText("drawTileDebug: x=" + x + ", y = " + y + "\n");
        Color c = Color.valueOf(colorname); // sets the value of the color as an object
        gc.setFill(c); // sets the color for what is to be drawn
        gc.fillRect(x, y, size, size); // creates a filled rectangle by providing a specific coordinate, size
    }

}