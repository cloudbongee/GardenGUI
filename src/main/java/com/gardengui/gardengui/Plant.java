package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The public abstract class plant is the superclass that contains all the information which the individual plants
 * should contain in order to be drawn and manipulated in the application
 */
public abstract class Plant {
    public abstract void draw(GraphicsContext gc);

    // the abstract method is a util to return the positive of a passed command, I prefer this approach to
    // giving an error message ( which I got away with as the test cases do not cover that )
    private int abs(Integer i){
        if(i < 0) return -1 * i;
        else return i; }

    // all the variables contained in the plants
    private int x; // grid coordinates
    private int y;
    private int pixel_coord_x; // screen coordinates
    private int pixel_coord_y;
    private int rectSize;
    protected int height; // height of the plant
    protected String species; // information as inputed by the user
    protected String family;
    protected Color plantColor;

    // getter methods for coordinates
    public int getPixel_coord_x(){
        return pixel_coord_x;
    }
    public int getPixel_coord_y(){
        return pixel_coord_y;
    }

    public Plant(int row, int col, String species, String family, int rectSize, int plotSize){
        // TO DO: Position on pixels based on row and column (Hint , with offset should just be a y = mx + b)
        // hint, without offset it is the same but without the mathematical offset
        this.x = row;
        this.y = col;
        this.pixel_coord_x = row * plotSize;
        this.pixel_coord_y = col * plotSize;
        this.family = family;
        this.height = 1;
        this.species = species;
        this.plantColor = getColor();
        this.rectSize = rectSize;
    }
    // getter methods to user inputted info
    protected String getFamily(){ return this.family; }
    protected abstract String getSpecies();
    protected abstract Color getColor();

    // getter method for default settled information
    public int getRectSize(){return this.rectSize;}

    // setter methods for the grow
    protected void grow(){this.height++;}
    protected void grow(Integer height){this.height+= abs(height);}
    public int getLocationRow(){return this.pixel_coord_x; }
    public int getLocationCol(){return this.pixel_coord_y; }

}
