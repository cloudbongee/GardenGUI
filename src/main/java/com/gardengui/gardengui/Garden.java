package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import org.w3c.dom.Text;

import java.util.GregorianCalendar;
import java.util.Map;

import static java.util.Map.entry;

public class Garden {
    protected enum SPECIES  {FLOWER , TREE, VEGETABLE, BONSAI}
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

    private int rows;
    private int cols;
    public Plant[][] garden;

    public Garden(int rows, int cols) {
        this.rows = rows;
        // while the current class contains rows and columns, the information of rows and columns will be pixels
        // for the superclass of plants. Thus making it easier to represent.
        this.cols = cols;
        this.garden = new Plant[rows][cols];
    }

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

    public void grow(int amount){
        for(Plant[] plantArray : garden){
            for(Plant plant : plantArray){
                if(plant != null) plant.grow(amount);
            }
        }
    }

    public void grow(int amount, int x, int y, TextArea textArea){
        if(0 <= x && x < rows && 0 <= cols && y < cols && garden[x][y] != null) garden[x][y].grow(amount);
        else { System.out.println("Can't grow there.\n"); textArea.appendText("Can't grow there.\n");}
    }

    public void grow(int amount, String plantName){
        for (Plant[] plants : this.garden) { for (Plant plant : plants) {
            if (plant != null && (plant.getSpecies().equalsIgnoreCase(plantName) || plant.getFamily().equalsIgnoreCase(plantName))) plant.grow(amount);
            }
        }
    }

    // Harvest method only cuts the vegetables from the screen
    public void harvest(){
        for (Plant[] plants : this.garden) { for (int i = 0; i < plants.length; i++) {
            if(plants[i] != null && plants[i].getFamily().equalsIgnoreCase("VEGETABLE")) plants[i] = null; }}}

    public void harvest(int x, int y, TextArea textArea){
        if(0 <= x && x < rows && 0 <= y && y < cols  && garden[x][y] != null && garden[x][y].getFamily().equalsIgnoreCase("VEGETABLE")) garden[x][y] = null;
        else { System.out.println("Can't harvest there.\n"); textArea.appendText("Can't harvest there.\n");}
    }

    public void harvest(String plantName){
        for (Plant[] plants : this.garden) { for (int i = 0; i < plants.length; i++) {
            if(plants[i] != null && plants[i].getSpecies().equalsIgnoreCase(plantName) && plants[i].getFamily().equalsIgnoreCase("VEGETABLE")) plants[i] = null; }}
    }

    public void pick(){
        for (Plant[] plants : this.garden) { for (int i = 0; i < plants.length; i++) {
            if(plants[i] != null && plants[i].getFamily().equalsIgnoreCase("FLOWER")) plants[i] = null; }}}

    public void pick(int x, int y, TextArea textArea){
        if(0 <= x && x < rows && 0 <= y && y < cols && garden[x][y] != null && garden[x][y].getFamily().equalsIgnoreCase("FLOWER")) garden[x][y] = null;
        else { System.out.println("Can't pick there.\n"); textArea.appendText("Can't pick there.\n");}
    }
    public void pick(String plantName){
        for (Plant[] plants : this.garden) { for (int i = 0; i < plants.length; i++) {
            if(plants[i] != null && plants[i].getSpecies().equalsIgnoreCase(plantName) && plants[i].getFamily().equalsIgnoreCase("FLOWER")) plants[i] = null; }}
    }
    public void cut(){
        for (Plant[] plants : this.garden) { for (int i = 0; i < plants.length; i++) {
            if(plants[i] != null && plants[i].getFamily().equalsIgnoreCase("TREE")) plants[i] = null; }}}

    public void cut(int x, int y, TextArea textArea){
        if(0 <= x && x < rows && 0 <= y && y < cols && garden[x][y].getSpecies().equalsIgnoreCase("TREE")) garden[x][y] = null;
        else { System.out.println("Can't cut there.\n"); textArea.appendText("Can't cut there.\n");}
    }
    public void cut(String plantName){
        for (Plant[] plants : this.garden) { for (int i = 0; i < plants.length; i++) {
            if(plants[i] != null && plants[i].getSpecies().equalsIgnoreCase(plantName) && plants[i].getFamily().equalsIgnoreCase("TREE")) plants[i] = null; }}
    }
    public void draw(GraphicsContext gc, int plotSize, int rectSize, Color bg) {
        for (int i =0; i < this.garden.length; i++) {
            for (int j = 0; j < this.garden[i].length; j++) {
                if(this.garden[i][j] != null) this.garden[i][j].draw(gc);
                else {
                    drawEmptyPlot(i,j,gc,plotSize,rectSize,bg);
                }}}}
    private void drawEmptyPlot(int x, int y, GraphicsContext gc, int plotSize, int rectSize, Color bg) {
        gc.clearRect(x * plotSize, y * plotSize, plotSize,plotSize);
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                gc.setFill(bg);
                gc.fillRect(x * plotSize + i * Math.floorDiv(plotSize,5), y * plotSize + j * Math.floorDiv(plotSize,5), 2,2);
            }
        }
    }
}

