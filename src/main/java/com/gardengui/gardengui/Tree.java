package com.gardengui.gardengui;

import com.gardengui.gardengui.Plant;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The tree class is one of the subclasses of plant
 */
public class Tree extends Plant {
    // the color representation of a tree is based on its species
    private final Color color;

    /**
     * Tree constructor
     * @param x
     *  x position in the grid
     * @param y
     *  y position in the grid
     * @param species
     *      the species is the super-type of flower
     * @param family
     *      family is the subtype as inputted when planted
     * @param rectSize
     *      rectSize is just a storage of the rectangle size passed which the flower should represent
     * @param plotSize
     *      plotSize is just a storage of the rectangular plot passed which the flower should represent
     */
    public Tree(int x, int y, String species, String family, int rectSize, int plotSize){
        super(x,y,species,family, rectSize, plotSize);
        this.color = this.getColor();
    }

    /*
    Overwritten functions for the class, the get species would return the user inputted name of the tree
     */
    @Override
    protected String getSpecies() {
        return this.species;
    }

    // color calculates the specific color of a tree based on the first three letters of its name
    @Override
    protected Color getColor() {
        // just a function to vary the color based on the ord value of the first few letters.
        return Color.rgb(Math.round((int) this.species.charAt(0)), Math.round((int) this.species.charAt(1)), Math.round((int) this.species.charAt(2)), 1);
    }

    /**
     * Draw function for the tree class, it is a recursive function that will plot the initial height
     * at the horizontal center of the 5x5 plot, and will span vertically, upwards.
     * @param gc
     *      JavaFX object containing graphical information for the active window
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        this.drawHelper(gc, 1);
    }

    /**
     * The drawhelper function is the recursive call of the draw function
     * @param gc
     *      JavaFX object containing graphical information for the active window
     * @param heightCount
     *      keeps track of the drawn squares as they span vertically
     */
    private void drawHelper(GraphicsContext gc, int heightCount) {
        if(heightCount > 5 || heightCount == this.height + 1) return;
        else{
            // change only the upwards direction, horizontal is constant
            gc.fillRect(this.getPixel_coord_x() + 2 * this.getRectSize(), this.getPixel_coord_y() + (5 - heightCount) * this.getRectSize(), this.getRectSize(), this.getRectSize());
            this.drawHelper(gc, heightCount + 1); // pass the recursive call with height count growing
        }
    }
}
