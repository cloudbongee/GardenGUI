package com.gardengui.gardengui;

import javafx.scene.paint.Color;

public abstract class Plant {
    public enum FAMILY  {FLOWER , TREE, VEGETABLE, BONSAI}

    private int abs(int i){
        if(i < 0) return -1 * i;
        else return i;
    }

    private int pixel_coord_x;
    private int pixel_coord_y;
    private int height;
    protected String species;
    protected String family;
    protected Color plant_color;

    private int getPixel_coord_x(){
        return pixel_coord_x;
    }
    private int getPixel_coord_y(){
        return pixel_coord_y;
    }

    public Plant(int row, int col, String species, String family){
        // TO DO: Position on pixels based on row and column (Hint , with offset should just be a y = mx + b)
        // hint, without offset it is the same but without the mathematical offset

        this.pixel_coord_x = row;
        this.pixel_coord_y = col;
        this.family = family;
        this.height = 1;
        this.species = getSpecies();
        this.plant_color = getColor();
    }

    protected abstract String getSpecies();
    protected abstract Color getColor();
    protected void grow(){this.height++;}
    protected void grow(Integer height){this.height+= this.abs(height);}
    public int getLocationRow(){return this.pixel_coord_x; }
    public int getLocationCol(){return this.pixel_coord_y; }

    // before, the draw method made a 5x5 character array that was updated into the grid.
    // now, with the correct adaptation of the coordinates to screen coordinates, it is possible to draw the figures
    // directly into the garden, as individual representations of itself.
    public abstract void draw();
}
