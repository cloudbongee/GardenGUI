package com.gardengui.gardengui;

public class CommandParser {

    private Garden activeGarden;
    public CommandParser(int rows, int cols) {
        activeGarden = new Garden(rows, cols);
    }

    public int[] toCoordinates(String parenthesisString){
        int[] coords = new int[2];
        // Removing parentheses and splitting by comma
        String[] toList = parenthesisString.replaceAll("[()]", "").split(",");
        coords[0] = Integer.parseInt(toList[0].trim());
        coords[1] = Integer.parseInt(toList[1].trim());
        return coords;
    }

    public void parse(String command){
        String[] currentCommand = command.split(",");

        // return the plant command to the garden
        if(currentCommand[0].toUpperCase().equals("PLANT")) ;
            //TO DO: Pass the plant coordinates to the garden plant command

        // return the grow command to the garden
        else if(currentCommand[0].toUpperCase().equals("GROW")){
            //TO DO: Parse the grow string and then add the
        }
        else if(currentCommand[0].toUpperCase().equals("HARVEST")){

        }
    }
}
