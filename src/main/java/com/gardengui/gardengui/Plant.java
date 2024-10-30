package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Plant {
    public abstract void draw(GraphicsContext gc);

    private int abs(int i){
        if(i < 0) return -1 * i;
        else return i;
    }

    private int x;
    private int y;
    private int pixel_coord_x;
    private int pixel_coord_y;
    private int rectSize;
    private int height;
    protected String species;
    protected String family;
    protected Color plantColor;


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

    protected String getFamily(){ return this.family; }
    protected abstract String getSpecies();
    protected abstract Color getColor();
    protected void grow(){this.height++;}
    protected void grow(Integer height){this.height+= this.abs(height);}
    public int getLocationRow(){return this.pixel_coord_x; }
    public int getLocationCol(){return this.pixel_coord_y; }
    public int getRectSize(){return this.rectSize;}
    // before, the draw method made a 5x5 character array that was updated into the grid.
    // now, with the correct adaptation of the coordinates to screen coordinates, it is possible to draw the figures
    // directly into the garden, as individual representations of itself.
}
