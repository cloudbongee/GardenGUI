package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;

import java.awt.*;
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

        // TO DO: Consider the possibility of a representation class that holds together the screen on five times the
        // initial size of a pot (Just to make sure that it represents itself correctly)
    }

    public void plant(int x_position, int y_position, String plantType){

    }
    public void grow(int amount){}
    public void grow(int amount, int x, int y){}
    public void grow(int amount, String plantName){}
    public void harvest(){}
    public void harvest(int x_position, int y_position){}
    public void harvest(String plantName){}
    public void pick(){}
    public void pick(int x_position, int y_position){}
    public void pick(String plantName){}
    public void cut(){ // for(Plant[] plantArray : garden) for(Plant plant: plantArray)
    }
    public void cut(int x_position, int y_position){}
    public void cut(String plantName){}
    public void draw(GraphicsContext gc, TextArea textArea){}
}
