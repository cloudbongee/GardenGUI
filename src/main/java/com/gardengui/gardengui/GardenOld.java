package com.gardengui.gardengui;

import java.util.Arrays;

import java.util.Map;


import static java.util.Map.entry;


/*

The garden class is the class that encapsulates both the actions taken by the command, and the

information from the com.gardengui.gardengui.Plant sub instances. It compares from a hashmap to then create and implement objects

into a grid - then - afterwards, creting a grid for the representation of such objects.

 */

public class GardenOld {

    // the global mappings of Species to families.

    public Map<String, String> typeRelationship = Map.ofEntries(

            // flowers

            entry("ROSE", "Flower"),

            entry("LILY", "Flower"),

            entry("DAISY", "Flower"),

            entry("TULIP", "Flower"),

            entry("IRIS", "Flower"),

            entry("SUNFLOWER", "Flower"),

            // trees

            entry("OAK", "Tree"),

            entry("WILLOW", "Tree"),

            entry("BANANA", "Tree"),

            entry("COCONUT", "Tree"),

            entry("PINE", "Tree"),

            // vegetable entries

            entry("GARLIC", "Vegetable"),

            entry("ZUCCHINI", "Vegetable"),

            entry("TOMATO", "Vegetable"),

            entry("YAM", "Vegetable"),

            entry("LETTUCE", "Vegetable"),

            // bonsai entries. I know that it is biologically controversial, but I though it would be cool

            entry("JUNIPER", "Bonsai"),

            entry("GINSENG", "Bonsai"),

            entry("BOUGANVILLIA", "Bonsai"),

            entry("CHERRY", "Bonsai"),

            entry("ELM", "Bonsai"),

            entry("SPRUCE", "Bonsai"));


    private int rows; // row represents the amount of rows on the garden grid

    private int columns; // column follows the aforementioned principle

    public Plant[][] garden; // the collection of com.gardengui.gardengui.Plant objects

    public char[][] representation; // The representation as characters for each plants.


    public GardenOld(int rows, int columns) {

        // construction of the variables

        this.rows = rows;

        this.columns = columns;

        this.garden = new Plant[rows][columns];

        this.representation = new char[rows * 5][columns * 5]; // there is a 5x5 grid for every pot in a garden

        for(char[] c: this.representation) Arrays.fill(c, '.'); // Pass through each row and fill them with a dot

    }

    // the public void plant takes the location where the plant should be put in and stores it in the grid.

    public void plant(int x, int y, String plantType){

        // type exists within the possible mappings - rows columns are valid.

        if(typeRelationship.containsKey(plantType.toUpperCase()) && 0 <= x && 0 <= y && x < rows && y < columns) {

            // planting logic implementation, ensure existance through the mappings given

            if(typeRelationship.get(plantType.toUpperCase()).equals("Flower")){

                garden[x][y] = new Flower(plantType.toUpperCase(), x, y);

            }else if(typeRelationship.get(plantType.toUpperCase()).equals("Vegetable")){

                garden[x][y] = new Vegetable(plantType.toUpperCase(), x, y);

            }else if(typeRelationship.get(plantType.toUpperCase()).equals("Bonsai")){

                garden[x][y] = new Bonsai(plantType.toUpperCase(), x, y);

            }else if(typeRelationship.get(plantType.toUpperCase()).equals("Tree")){

                garden[x][y] = new Tree( plantType.toUpperCase(), x, y );

            }else System.out.println("Can't plant there.\n");

        }}


    // two overloads of the same function. This first one is meant to grow all regardless of location

    public void grow(int height) {

        // Print the given command

        System.out.println("> GROW " + height + "\n");

        // iterate and call grow for all the flowers

        for (Plant[] plants : this.garden) {

            for (Plant plant : plants) {

                if (plant != null) plant.growSelf(height);}}}


    // takes the height and takes the string type, checks for the type of plant instanced.

    public void grow(int height, String type) {

        // print the given command

        System.out.println("> GROW " + height + " " + type.toLowerCase().trim() + "\n");

        // if it is contained in the keys


        // iterate and call grow for all the flowers

        for (Plant[] plants : this.garden) {

            for (Plant plant : plants) {

                // if the plant is not null and the

                if (plant != null && (plant.getSpecies().equalsIgnoreCase(type) || plant.getFamily().equalsIgnoreCase(type))) plant.growSelf(height);

            }

        }}


    // grow per coordinates

    public void grow(int height, int coord_x, int coord_y) {

        System.out.println("> GROW " + height + " (" + coord_x + "," + coord_y + ")\n");

        // restrict ceratin coordinates, check the given coordinate is not null, otherwise grow regardless of type

        if(coord_x < 0 || rows <= coord_x  || coord_y < 0 || columns  <= coord_y || this.garden[coord_x][coord_y] == null){

            System.out.println("Can't grow there.\n");

        } else{

            this.garden[coord_x][coord_y].growSelf(height); }}


    // harvest all

    public void harvest() {

        System.out.println("> HARVEST\n");

        // check for valid harvests by checking it is a vegetable

        for (Plant[] plants : this.garden) {

            for (int i = 0; i < plants.length; i++) {

                if(plants[i] != null && plants[i].getSpecies().equals("Vegetable")) plants[i] = null; }}}


    // harvest per location

    public void harvest(int x, int y) {

        System.out.println("> HARVEST" + " ("+x+","+y+")\n");

        if( (0 <= x) && (x < this.rows) && (0 <= y) && (y < this.rows) && this.garden[x][y] != null && this.garden[x][y].getSpecies().equals("Vegetable")){

            this.garden[x][y] = null;

        }else System.out.println("Can't harvest there.\n");}


    // harvest per type

    public void harvest(String type) {

        System.out.println("> HARVEST "+type.toLowerCase().trim() + "\n");

        for (Plant[] plants : this.garden) {

            for (int i = 0; i < plants.length; i++) {

                if (plants[i] != null && plants[i].getFamily().equals(type.toUpperCase()) && plants[i].getSpecies().equals("Vegetable")) { plants[i] = null; }}}}


    // pick all

    public void pick() {

        System.out.println("> PICK\n");

        // pick the flower. if the species is equal to flower

        for (Plant[] plants : this.garden) {

            for (int i = 0; i < plants.length; i++) {

                if(plants[i] != null &&  plants[i].getSpecies().equals("Flower") && plants[i] != null ) plants[i] = null; }}}


    // harvest per location

    public void pick(int x, int y){

        System.out.println("> PICK" + " ("+x+","+y+")\n");

        if( (0 <= x) && (x < this.rows) && (0 <= y) && (y < this.rows) && this.garden[x][y] != null && this.garden[x][y].getSpecies().equals("Flower")){

            this.garden[x][y] = null;

        }else System.out.println("Can't pick there.\n");

    }


    // harvest per type

    public void pick(String type) {

        System.out.println("> PICK " + type.toLowerCase().trim()+"\n");

        for (Plant[] plants : this.garden) {

            for (int i = 0; i < plants.length; i++) {

                if(plants[i] != null && plants[i].getFamily().equals(type.toUpperCase()) && plants[i].getSpecies().equals("Flower")){

                    plants[i] = null; }}}}


    // pick all

    public void cut() {

        System.out.println("> CUT\n");

        // pick the flower. if the species is equal to flower

        for (Plant[] plants : this.garden) {

            for (int i = 0; i < plants.length; i++) {

                if(plants[i].getSpecies().equals("Tree")) plants[i] = null; }}}


    // harvest per location

    public void cut(int x, int y) {

        System.out.println("> CUT " + "("+x+","+y+")\n");

        if( (0 <= x) && (x < this.rows) && (0 <= y) && (y < this.rows) && this.garden[x][y] != null && this.garden[x][y].getSpecies().equals("Tree")){

            this.garden[x][y] = null;

        }else System.out.println("Can't cut there.\n");

    }


    // harvest per type

    public void cut(String type) {

        System.out.println("> CUT " + type.toLowerCase());

        for (Plant[] plants : this.garden) {

            for (int i = 0; i < plants.length; i++) {

                if(plants[i].getSpecies().equals("Tree") && plants[i].getFamily().equals(type.toUpperCase())){

                    plants[i] = null; }}}}


    // draw the garden

    public  void drawGarden() {

        // iterate through the rows

        for (int row = 0; row < rows; row++) {

            // iterate through the columns

            for (int column = 0; column < columns; column++) {

                Plant plant = garden[row][column];

                // check for null

                if (plant != null) {

                    char[][] draw = plant.draw();

                    // This was forced by the IDE, I did however check the documentation, copies array

                    for (int t = 0; t < 5; t++) { System.arraycopy(draw[t], 0, this.representation[row * 5 + t], column * 5, 5); }

                } else {

                    for (int t = 0; t < 5; t++) {

                        // this was for some reason something I was forced to do by the IDE, because it gave me an

                        // error anytime something different, still, it replaces a for loop to fill the array horizontally

                        Arrays.fill(this.representation[row * 5 + t], column * 5, column * 5 + 5, '.'); }}}}}


    // Prints the drawng on the terminal. It is separate from draw garden so updating is distinct.

    public void printDrawing(){

        System.out.println("> PRINT");

        for(char[] rep: this.representation){

            System.out.println(new String(rep));

        }

        System.out.println();

    }

}


