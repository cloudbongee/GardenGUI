package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

public class CommandParser {

    /*
    IMPORTANT:
    Passing a reference to an object in java fx to become a memory slot of the instance
    will cause a copy to be passed down, and not the actual object, to change the object itself,
    to edit the graphics context and the text area information of the GUI
    it is necessary to pass it down as a parameter to the function , but not to instance it
    with the object!!
     */

    private Garden activeGarden;

    private final Color bg;
    public CommandParser(int rows, int cols, Color background, GraphicsContext gc, int plotSize, int rectSize) {
        this.activeGarden = new Garden(rows, cols);
        this.bg = background;
        activeGarden.draw(gc, plotSize, rectSize, background);
    }

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
    having an if statement also permits for direct control of what's occuring with the logic!
     */

    public void parse(String command, GraphicsContext gc, TextArea textArea, int plotSize, int rectSize){
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
            else if(currentCommand[1].contains("(")){
                int[] coordinates  = toCoordinates(currentCommand[2]);
                // grow on the specific coordinates for the garden class
                activeGarden.grow(Integer.parseInt(currentCommand[1]), coordinates[0], coordinates[1], textArea);
            }else activeGarden.grow(Integer.parseInt(currentCommand[1]), currentCommand[2]); // pass the name of the plant to grow
        }
        else if(currentCommand[0].equals("HARVEST")){
            if(currentCommand.length == 1) {activeGarden.harvest();}// take all the vegetables off
            else if(currentCommand[1].contains("(")){
                int[] coordinates  = toCoordinates(currentCommand[1]);
                activeGarden.harvest(coordinates[0], coordinates[1], textArea);
            }else activeGarden.harvest(currentCommand[1]); }

        else if(currentCommand[0].equals("PICK")){
            if(currentCommand.length == 1) activeGarden.pick(); // pick all flowers
            else if(currentCommand[1].contains("(")){
                int[] coordinates = toCoordinates(currentCommand[1]);
                activeGarden.pick(coordinates[0], coordinates[1], textArea); // pick on coordinates
            }else activeGarden.pick(currentCommand[1]);
        }

        else if(currentCommand[0].equals("CUT")){
            if(currentCommand.length == 1) activeGarden.cut(); // cut for all trees
            else if(currentCommand[1].contains("(")){
                int[] coordinates = toCoordinates(currentCommand[1]);
                activeGarden.cut(coordinates[0], coordinates[1],textArea);
            }else activeGarden.cut(currentCommand[1]);
        }
        // append the command text to the paragraph, this will be changed later but it will probably help me debug
        textArea.appendText(command + "\n");
        this.drawActiveGarden(gc, textArea, rectSize, plotSize);
    }

    public void drawActiveGarden(GraphicsContext gc, TextArea textArea, int rectSize, int plotSize) {
        activeGarden.draw(gc, plotSize, rectSize, bg);
    }
}
