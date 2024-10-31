package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import org.w3c.dom.Text;

import java.util.GregorianCalendar;
import java.util.Map;

import static java.util.Map.entry;

/**
 * The public class garden collects and processes the plant objects orderedly, and utilizes their commands to manipulate
 * them as instructed by a user
 */
public class Garden {
    // the type relationship map permits keeping control of the available family-species relationship in the class
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
            // bonsai entries. I know that it is biologically controversial, but I thought it would be cool
            entry("JUNIPER", "Bonsai"),
            entry("GINSENG", "Bonsai"),
            entry("BOUGANVILLIA", "Bonsai"),
            entry("CHERRY", "Bonsai"),
            entry("ELM", "Bonsai"),
            entry("SPRUCE", "Bonsai")
    );
    // information about row and columns available in the grid of plants
    private int rows;
    private int cols;
    public Plant[][] garden;

    /**
     * The garden constructor just sets up the rows and columns given to create the collection of plants
     * @param rows
     *      integer, represents row in the grid
     * @param cols
     *      integer, represents columns in the grid
     */
    public Garden(int rows, int cols) {
        this.rows = rows;
        // while the current class contains rows and columns, the information of rows and columns will be pixels
        // for the superclass of plants. Thus making it easier to represent.
        this.cols = cols;
        this.garden = new Plant[rows][cols];
    }

    // adds a plant to the grid by searching its validity through the map given above
    public void plant(int x, int y, String plantType, TextArea textArea, int rectSize, int plotSize) {
        if(typeRelationship.containsKey(plantType.toUpperCase()) && 0 <= x && 0 <= y && x < rows && y < cols) {
            // planting logic implementation, ensure existance through the mappings given
            if(typeRelationship.get(plantType.toUpperCase()).equals("Flower")){
                garden[x][y] = new Flower(x, y, plantType.toUpperCase(), typeRelationship.get(plantType.toUpperCase()), rectSize, plotSize);
            }else if(typeRelationship.get(plantType.toUpperCase()).equals("Vegetable")){
                garden[x][y] = new Vegetable(x, y, plantType.toUpperCase(), typeRelationship.get(plantType.toUpperCase()), rectSize, plotSize);
            }else if(typeRelationship.get(plantType.toUpperCase()).equals("Bonsai")){
                garden[x][y] = new Bonsai(x, y, plantType.toUpperCase(), typeRelationship.get(plantType.toUpperCase()), rectSize, plotSize);
            }else if(typeRelationship.get(plantType.toUpperCase()).equals("Tree")){
                garden[x][y] = new Tree(x, y, plantType.toUpperCase(), typeRelationship.get(plantType.toUpperCase()), rectSize, plotSize);
            }else{ System.out.println("Can't plant there.\n"); textArea.appendText("Can't plant there\n");}
        }else{ System.out.println("Can't plant there.\n"); textArea.appendText("Can't plant there.\n");}}

    /**
     * The public method grow with an amount, takes all the plants that are not null in the grid and sums the
     * number passed in the command to which it pertains
     * @param amount
     *  integer representing height added
     */
    public void grow(int amount){
        for(Plant[] plantArray : garden){
            for(Plant plant : plantArray){
                if(plant != null) plant.grow(amount);}}}

    /** * The grow function adds only one height to all plants */
    public void grow(){
        for(Plant[] plants: this.garden){
            for(Plant plant : plants){
                if(plant != null) plant.grow();}}}

    /** The grow function, when given a location, sums up the height passed down to it to the plant pointed to if its valid */
    public void grow(int amount, int x, int y, TextArea textArea){
        // change based on the coordinates, and that those coordinates are valid
        if(0 <= x && x < rows && 0 <= cols && y < cols && garden[x][y] != null) garden[x][y].grow(amount);
        else { System.out.println("Can't grow there.\n"); textArea.appendText("Can't grow there.\n");}
    }
    /** The grow function, when given a type, searches through the grid and grows all the matches*/
    public void grow(int amount, String plantName){
        for (Plant[] plants : this.garden) { for (Plant plant : plants) {
            // filter that the plant is not null, and that the species or family name match
            if (plant != null && (plant.getSpecies().equalsIgnoreCase(plantName) || plant.getFamily().equalsIgnoreCase(plantName))) plant.grow(amount);}}}

    // Harvest method, makes null all the vegetables in the grid
    public void harvest(){
        for (Plant[] plants : this.garden) { for (int i = 0; i < plants.length; i++) { // traverese the grid, check validity
            if(plants[i] != null && plants[i].getFamily().equalsIgnoreCase("VEGETABLE")) plants[i] = null; }}}
    // harvest method, makes null a specific place in the grid for as long as it is a valid vegetable
    public void harvest(int x, int y, TextArea textArea){
        if(0 <= x && x < rows && 0 <= y && y < cols  && garden[x][y] != null && garden[x][y].getFamily().equalsIgnoreCase("VEGETABLE")) garden[x][y] = null;
        else { System.out.println("Can't harvest there.\n"); textArea.appendText("Can't harvest there.\n");}}

    // harvest function with plant name, traverses the grid and eliminates all matching vegetables
    public void harvest(String plantName){
        for (int i = 0; i < garden.length; i++) { for (int j = 0; j < garden[i].length; j++) {
            if(garden[i][j] != null && garden[i][j].getSpecies().equalsIgnoreCase(plantName) && garden[i][j].getFamily().equalsIgnoreCase("VEGETABLE")) {
                garden[i][j] = null;} }}}

    // the pick function eliminates all flowers from the grid
    public void pick(){
        for (Plant[] plants : this.garden) { for (int i = 0; i < plants.length; i++) { // traverse the grid
            if(plants[i] != null && plants[i].getFamily().equalsIgnoreCase("FLOWER")) plants[i] = null; }}}

    // the pick functions with coordinates eliminates the flower specified as long as its valid
    public void pick(int x, int y, TextArea textArea){
        if(0 <= x && x < rows && 0 <= y && y < cols && garden[x][y] != null && garden[x][y].getFamily().equalsIgnoreCase("FLOWER")) garden[x][y] = null;
        else { System.out.println("Can't pick there.\n"); textArea.appendText("Can't pick there.\n");}}

    // the pick function with plant name traverses the grid, eliminates all matching flowers.
    public void pick(String plantName){
        for (int i = 0; i < garden.length; i++) { for (int j = 0; j < garden[i].length; j++) { // traverse the function, pass the logic gates
            if(garden[i][j] != null && garden[i][j].getSpecies().equalsIgnoreCase(plantName) && garden[i][j].getFamily().equalsIgnoreCase("FLOWER")) {
                garden[i][j] = null; } }}}

    // the cut function eliminates all trees in the grid
    public void cut(){
        for (Plant[] plants : this.garden) { for (int i = 0; i < plants.length; i++) {
            if(plants[i] != null && plants[i].getFamily().equalsIgnoreCase("TREE")) plants[i] = null; }}}

    // the cut function when passed coordinates eliminates the tree specified as long as its valid to do so
    public void cut(int x, int y, TextArea textArea){
        if(0 <= x && x < rows && 0 <= y && y < cols && garden[x][y] != null && garden[x][y].getFamily().equalsIgnoreCase("TREE")) garden[x][y] = null;
        else { System.out.println("Can't cut there.\n"); textArea.appendText("Can't cut there.\n");}}

    // the cut function with the plantName traverses the grid, eliminates all matching trees
    public void cut(String plantName){
        for (int i = 0; i < garden.length; i++) { for (int j = 0; j < garden[i].length; j++) {
            if(garden[i][j] != null && garden[i][j].getSpecies().equalsIgnoreCase(plantName) && garden[i][j].getFamily().equalsIgnoreCase("TREE")) {
                garden[i][j] = null;
            } }}}

    // the trim function eliminates all bonsais in the grid
    public void trim(){
        for (Plant[] plants : this.garden) { for (int i = 0; i < plants.length; i++) {
            if(plants[i] != null && plants[i].getFamily().equalsIgnoreCase("BONSAI")) plants[i] = null; }}}
    // the trim function with coordinates eliminates the specified bonsai, as long as its valid to do so
    public void trim(int x, int y, TextArea textArea){
        if(0 <= x && x < rows && 0 <= y && y < cols && garden[x][y] != null && garden[x][y].getFamily().equalsIgnoreCase("BONSAI")) garden[x][y] = null;
        else { System.out.println("Can't cut there.\n"); textArea.appendText("Can't cut there.\n");}}
    // the trim function with plantNames eliminates all matching bonsais
    public void trim(String plantName){
        for (int i = 0; i < garden.length; i++) { for (int j = 0; j < garden[i].length; j++) {
            if(garden[i][j] != null && garden[i][j].getSpecies().equalsIgnoreCase(plantName) && garden[i][j].getFamily().equalsIgnoreCase("BONSAI")) {
                garden[i][j] = null;} }}}

    // the draw function checks for the whole garden and utilizes the coordinates to represent it in the screen
    public void draw(GraphicsContext gc, int plotSize, int rectSize, Color bg) {
        for (int i =0; i < this.garden.length; i++) { // traverse the rows
            for (int j = 0; j < this.garden[i].length; j++) { // traverse the columns
                if(this.garden[i][j] != null) { // if is not null, draw the specified plant
                    this.drawEmptyPlot(i,j,gc,plotSize,rectSize,bg);
                    this.garden[i][j].draw(gc);
                } else { // else, draw the empty grid
                    this.drawEmptyPlot(i,j,gc,plotSize,rectSize,bg);
                }}}}
    // The draw empty plot creates centered dots where the squares are meant to belong, after clearing the painted position
    private void drawEmptyPlot(int x, int y, GraphicsContext gc, int plotSize, int rectSize, Color bg) {
        gc.clearRect(x * plotSize, y * plotSize, plotSize,plotSize);
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                gc.setFill(bg);
                gc.fillRect(x * plotSize + i * Math.floorDiv(plotSize,5) + Math.floorDiv(rectSize,2), y * plotSize + j * Math.floorDiv(plotSize,5) + Math.floorDiv(rectSize,2), 2,2);
            }}}}

