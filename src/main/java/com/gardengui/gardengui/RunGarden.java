package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;

import java.io.File;

import java.io.FileNotFoundException;

import java.util.Scanner;


/*

The class run garden is the main class for the garden project,

it includes all utils for reading files, and for processing the input

of it to then send it to the garden class.

 */

public class RunGarden{

    // The main method was changed to just a run void that permits gathering the file input
    public static void run(String filename, int row, int columns, double delay, GraphicsContext gc, TextArea commands) throws FileNotFoundException {
        fileInput(filename, row, columns, delay. gc, commands);
        System.out.println();
    }


    // fileInput is a method which process the input and passes it as commands
    public static void fileInput(String fileName, int row, int column, double delay, GraphicsContext gc, TextArea commands) throws FileNotFoundException{
        // read the file by creating a scanner class for it.
        File openFile = new File(fileName);
        Scanner readFile = new Scanner(openFile);

        // initialize garden and columns by iterating first two lines
        row = Integer.parseInt(readFile.nextLine().split(" ")[1]);
        column = Integer.parseInt(readFile.nextLine().split(" ")[1]);
        // added the delay as an initial command too
        delay = Integer.parseInt(readFile.nextLine().split(" ")[1]);

        if(columns > 16){
            System.out.println("Too many plot columns."); // more than 80 dots of width has exceeded
            return;
        }

        // The new garden object is the main object of the application. Containing the info
        GardenOld newGardenOld = new GardenOld(rows, columns);

        // iterate through the rest of the file
        while(readFile.hasNextLine()){
            // file reading
            String line = readFile.nextLine();
            parseStringAndChoose(line, newGardenOld);


        }

    }

    // ParseCoords is a utility to convert a string into an array of (x,y) coordinates

    public static int[] parseCoords(String parsed) {

        int[] coords = new int[2];

        // Removing parentheses and splitting by comma

        String[] toList = parsed.replaceAll("[()]", "").split(",");

        coords[0] = Integer.parseInt(toList[0].trim());

        coords[1] = Integer.parseInt(toList[1].trim());

        return coords;

    }

    // parseStingAndChoose Processes the rest of the input of the file.

    // it contains the if statements necessary to process the command options available

    public static void parseStringAndChoose(String line, GardenOld gardenOld){

        // print the content of the line

        String[] splitLine = line.split(" ");

        String firstCommand = splitLine[0].toUpperCase();


        // command logic

        if(firstCommand.equals("PLANT")){

            String[]  coordinates = splitLine[1].replace("(","").replace(")", "").split(",");

            // retrieve the coordinates

            int[] coords = parseCoords(splitLine[1]);

            String plantType = splitLine[2].trim().toUpperCase();


            gardenOld.plant(coords[0], coords[1], plantType);


        }else if(firstCommand.equals("PRINT")){ // if the command is to pring, then it is only necessary to call the print function on the garden

            gardenOld.drawGarden();

            gardenOld.printDrawing();


        }else if(firstCommand.equals("GROW")){ // if the command is grow, then there should be parsing for height, coordinates or type

            if(splitLine.length == 2) gardenOld.grow(Integer.parseInt(splitLine[1]));

                // the command includes a parenthesis, then it is a coordinate

            else if(splitLine[2].contains("(")){

                int[] coords = parseCoords(splitLine[2]);

                gardenOld.grow(Integer.parseInt(splitLine[1]), coords[0], coords[1]);

            }

            // the command includes type

            else gardenOld.grow(Integer.parseInt(splitLine[1]), splitLine[2].toUpperCase());


        }else if(firstCommand.equals("HARVEST")){ // if the command is harvest, then it is necessary to check for coordinates

            if(splitLine.length == 1) gardenOld.harvest();

                // the command includes a parenthesis, then it is a coordinate

            else if(splitLine[1].contains("(")){

                int[] coords = parseCoords(splitLine[1]);

                gardenOld.harvest(coords[0], coords[1]);

            }

            // the command includes type

            else gardenOld.harvest(splitLine[1].toUpperCase());


        }else if(firstCommand.equals("PICK")){ // check for coordinates

            if(splitLine.length == 1) gardenOld.pick();

                // the command includes a parenthesis, then it is a coordinate

            else if(splitLine[1].contains("(")){

                int[] coords = parseCoords(splitLine[1]);

                gardenOld.pick(coords[0], coords[1]);

            }

            // the command includes type

            else gardenOld.pick(splitLine[1].toUpperCase());


        }else if(firstCommand.equals("CUT")){ // check for coordinates

            if(splitLine.length == 1) gardenOld.cut();

                // the command includes a parenthesis, then it is a coordinate

            else if(splitLine[1].contains("(")){

                int[] coords = parseCoords(splitLine[1]);

                gardenOld.cut(coords[0], coords[1]);

            }

            // the command includes type

            else gardenOld.cut(splitLine[1].toUpperCase());


        }

    }

}
