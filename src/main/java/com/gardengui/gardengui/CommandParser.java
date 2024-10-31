package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

/**
 * The CommandParser function is the spiritual successor of garden run, it takes the commands read in the main
 *  loop of the application, and ensures that these are filtered correctly
 */
public class CommandParser {

    /*
    IMPORTANT:
    Passing a reference to an object in java fx to become a memory slot of the instance
    will cause a copy to be passed down, and not the actual object, to change the object itself,
    to edit the graphics context and the text area information of the GUI
    it is necessary to pass it down as a parameter to the function , but not to instance it
    with the object!!
     */
    // contains the garden that will be affected by the commands
    private Garden activeGarden;

    // Some optional information I didn't really use much, but still is nice to have - permits editing the background
    private final Color bg;

    /**
     * Constructor call for the commandParser call, it is meant to be passed down the JavaFX objects to be able to modify them
     * through the objects in the garden,
     * @param rows
     *      amount of rows the garden will have
     * @param cols
     *      amount of columns the garden will have
     * @param background
     *      the background Color, from the JavaFX color library (It was annoying to see the automatic import was not javafx)
     * @param gc
     *      contains the information of the scene shown in the window
     * @param plotSize
     *      pixel size of a plot that will contain 5x5 squares
     * @param rectSize
     *      pixel size of the squares contained in the plot
     */
    public CommandParser(int rows, int cols, Color background, GraphicsContext gc, int plotSize, int rectSize) {
        // initiate variables
        this.activeGarden = new Garden(rows, cols);
        this.bg = background;
        // draw the empty garden, passing the nulls will make a collection of dots to represent the plotting space
        activeGarden.draw(gc, plotSize, rectSize, background);
    }

    /**
     * the toCoordinates method parses a string in the form '(n,n)' where n is an integer (written as a string)
     * @param parenthesisString
     *      String in the previously mentioned form
     * @return coords
     *      the coordinates as a pair of integers in a list
     */
    public int[] toCoordinates(String parenthesisString){
        int[] coords = new int[2];
        // Removing parentheses and splitting by comma
        String[] toList = parenthesisString.replaceAll("[()]", "").split(",");
        coords[0] = Integer.parseInt(toList[0].trim());
        coords[1] = Integer.parseInt(toList[1].trim());
        return coords;
    }


    /*
    Stylistical decision on the coding of this:
    Java is very weird about switch cases, some versions of java prefer to support and perform with different timings over
    different denominations of the same switch case. Technically, this could have been written in far less lines, however,
    having an if statement also permits for direct control of what's  with the logic!
     */

    /**
     *
     * @param command
     *      String that contains the line read from the GUI
     * @param gc
     *      Object from javafx that contains the information of the scene shown in the window
     * @param textArea
     *      Object from javafx that permits showing strings and text in a text field
     * @param plotSize
     *      int that retains the pixel size of a plot space in the window
     * @param rectSize
     *      int that retains the pixel size of a rectangle for a plant in the window
     */
    public void parse(String command, GraphicsContext gc, TextArea textArea, int plotSize, int rectSize){
        // append the command to the textField
        textArea.appendText(command.toLowerCase() + "\n");
        // parse the given string, trim the edges, convert to uppercase, split on space
        String[] currentCommand = command.trim().toUpperCase().split(" ");

        // return the plant command to the garden
        if(currentCommand[0].equals("PLANT")) {
            //TO DO: Pass the plant coordinates to the garden plant command
            int[] coordinates = toCoordinates(currentCommand[1]);
            String plantType = currentCommand[2].trim();
            // pass the coordinates to plant into the array of the garden
            activeGarden.plant(coordinates[0], coordinates[1], plantType, textArea, rectSize, plotSize); }
        // PRINT COMMAND HAS BEEN SHAVED OFF AS THE GUI IS ACTIVE ALWAYS!!
        else if(currentCommand[0].equals("GROW")){
            if(currentCommand.length == 1){
                activeGarden.grow();
            }
            else if(currentCommand.length == 2) {
                // Grow everything by the amount given
                activeGarden.grow(Integer.parseInt(currentCommand[1]));
            }
            else if(currentCommand[2].contains("(")){
                int[] coordinates  = toCoordinates(currentCommand[2]);
                // grow on the specific coordinates for the garden class
                activeGarden.grow(Integer.parseInt(currentCommand[1]), coordinates[0], coordinates[1], textArea);
            }else activeGarden.grow(Integer.parseInt(currentCommand[1]), currentCommand[2]); // pass the name of the plant to grow
        }
        else if(currentCommand[0].equalsIgnoreCase("HARVEST")){
            if(currentCommand.length == 1) {activeGarden.harvest();}// take all the vegetables off
            else if(currentCommand[1].contains("(")){
                int[] coordinates  = toCoordinates(currentCommand[1]);
                activeGarden.harvest(coordinates[0], coordinates[1], textArea);
            }else activeGarden.harvest(currentCommand[1]); }

        else if(currentCommand[0].equalsIgnoreCase("PICK")){
            if(currentCommand.length == 1) activeGarden.pick(); // pick all flowers
            else if(currentCommand[1].contains("(")){
                int[] coordinates = toCoordinates(currentCommand[1]);
                activeGarden.pick(coordinates[0], coordinates[1], textArea); // pick on coordinates
            }else activeGarden.pick(currentCommand[1]);
        }

        else if(currentCommand[0].equalsIgnoreCase("CUT")){
            if(currentCommand.length == 1) activeGarden.cut(); // cut for all trees
            else if(currentCommand[1].contains("(")){
                int[] coordinates = toCoordinates(currentCommand[1]);
                activeGarden.cut(coordinates[0], coordinates[1],textArea);
            }else activeGarden.cut(currentCommand[1]);
        }
        else if(currentCommand[0].equalsIgnoreCase("TRIM")){
            if(currentCommand.length == 1) activeGarden.trim(); // cut for all trees
            else if(currentCommand[1].contains("(")){
                int[] coordinates = toCoordinates(currentCommand[1]);
                activeGarden.trim(coordinates[0], coordinates[1],textArea);
            }else activeGarden.trim(currentCommand[1]);
        }
        this.drawActiveGarden(gc, textArea, rectSize, plotSize);
    }

    /**
     * The draw active garden method triggers the scan of the active garden to represent the updated garden in the GUI
     * @param gc
     *      JavaFX object, contains window graphical information
     * @param textArea
     *      Javafx object, contains text field information
     * @param rectSize
     *      Size in pixels of the rectangles contained
     * @param plotSize
     *      Size in pixels of the plot
     */
    public void drawActiveGarden(GraphicsContext gc, TextArea textArea, int rectSize, int plotSize) {
        activeGarden.draw(gc, plotSize, rectSize, bg);
    }
}
