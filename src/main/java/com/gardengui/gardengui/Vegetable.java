package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * The Vegetable  class is one of the subclasses of plant
 */
public class Vegetable extends Plant {
    private Color color;
    /**
     * Vegetable constructor
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
    public Vegetable(int x, int y, String species, String family, int rectSize, int plotSize){
        super(x,y,species,family, rectSize, plotSize);
        this.color = this.getColor();
    }

    // overwritten functions from the superclass, get species returns the type of vegetable
    @Override
    protected String getSpecies() {
        return this.species;
    }

    // the unique color for the vegetable is calculated from the first three letters of the vegetable name
    @Override
    protected Color getColor() {
        // just a function to vary the color based on the ord value of the first few letters.
        return Color.rgb(Math.round((int) this.species.charAt(0)), Math.round((int) this.species.charAt(1)), Math.round((int) this.species.charAt(2)), 1);
    }

    /**
     * Draw function for the vegetable class, it places the initial height at the center horizontally, top vertically,
     * and spans downwards vertically as the height grows :-)
     * @param gc
     *      JavaFX object containing graphical information for the active window
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        this.drawHelper(gc, 0);
    }
    // the recursive call for the draw function
    private void drawHelper(GraphicsContext gc, int heightCount) {
        if(heightCount > 4 || heightCount == this.height) return;
        else{
            // keep the pixel coordinates horizontally static, change the others based on height count
            gc.fillRect(this.getPixel_coord_x() + 3 * this.getRectSize(), this.getPixel_coord_y() + (heightCount) * this.getRectSize(), this.getRectSize(), this.getRectSize());
            this.drawHelper(gc, heightCount + 1);
        }
    }
}
