package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The flower class is a subclass of the plant class, containing the information for one of the types of plant available
 * in the garden application. As characteristic trait, the printing of the flower is done spanning in all directions from
 * the center of the plot
 */
public class Flower extends Plant {
    // the color is set by rounding the integer created from the characters of the plant type
    private Color color;

    /**
     * Flower constructor
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
    public Flower(int x, int y, String species, String family, int rectSize, int plotSize){
        super(x,y,species,family, rectSize, plotSize);
        this.color = this.getColor();
    }


    // override functions are described in the superclass
    // return the species of the flower
    @Override
    protected String getSpecies() {
        return this.species;
    }
    // return the color of the flower
    @Override
    protected Color getColor() {
        // just a function to vary the color based on the ord value of the first few letters.
        return Color.rgb(Math.round((int) this.species.charAt(0)), Math.round((int) this.species.charAt(1)), Math.round((int) this.species.charAt(2)), 1);
    }
    // gc.fillRect(this.getPixel_coord_x(), this.getPixel_coord_y(), getRectSize() * 5, getRectSize() * 5);
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        this.drawHelper(gc);
    }
    // contains the information of the cases with which the flower should be drawn
    private void drawHelper(GraphicsContext gc) {

            if(this.height == 1) {
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(), this.getPixel_coord_y() + 2 * getRectSize(), this.getRectSize(), this.getRectSize());
            }
            else if(this.height == 2) {
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(), this.getPixel_coord_y() + 2 * getRectSize(), this.getRectSize(), this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 3 * getRectSize(), this.getPixel_coord_y() + 2 * getRectSize(), this.getRectSize(), this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + getRectSize(), this.getPixel_coord_y() + 2 * getRectSize(), this.getRectSize(), this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(), this.getPixel_coord_y() + 3 * getRectSize(), this.getRectSize(), this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(), this.getPixel_coord_y() + getRectSize(), this.getRectSize(), this.getRectSize());
            } else if (this.height == 3) {
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(),this.getPixel_coord_y()+ 2 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 3 * getRectSize(),this.getPixel_coord_y()+ 2 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + getRectSize(),this.getPixel_coord_y()+ 2 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(),this.getPixel_coord_y()+ 3 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(),this.getPixel_coord_y()+ getRectSize(),this.getRectSize(),this.getRectSize());

                gc.fillRect(this.getPixel_coord_x() + 3 * getRectSize(),this.getPixel_coord_y()+ 3 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + getRectSize(),this.getPixel_coord_y()+ 3 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 3 * getRectSize(),this.getPixel_coord_y()+ getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + getRectSize(),this.getPixel_coord_y()+ getRectSize(),this.getRectSize(),this.getRectSize());

                gc.fillRect(this.getPixel_coord_x() + 4 * getRectSize(),this.getPixel_coord_y()+ 2 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x(),this.getPixel_coord_y()+ 2 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(),this.getPixel_coord_y()+ 4 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(),this.getPixel_coord_y(),this.getRectSize(),this.getRectSize());
            }
            else if(this.height == 4) {
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(),this.getPixel_coord_y()+ 2 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 3 * getRectSize(),this.getPixel_coord_y()+ 2 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + getRectSize(),this.getPixel_coord_y()+ 2 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(),this.getPixel_coord_y()+ 3 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(),this.getPixel_coord_y()+ getRectSize(),this.getRectSize(),this.getRectSize());

                gc.fillRect(this.getPixel_coord_x() + 3 * getRectSize(),this.getPixel_coord_y()+ 3 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + getRectSize(),this.getPixel_coord_y()+ 3 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 3 * getRectSize(),this.getPixel_coord_y()+ getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + getRectSize(),this.getPixel_coord_y()+ getRectSize(),this.getRectSize(),this.getRectSize());

                gc.fillRect(this.getPixel_coord_x() + 4 * getRectSize(),this.getPixel_coord_y()+ 2 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x(),this.getPixel_coord_y()+ 2 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(),this.getPixel_coord_y()+ 4 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 2 * getRectSize(),this.getPixel_coord_y(),this.getRectSize(),this.getRectSize());

                gc.fillRect(this.getPixel_coord_x() + 4 * getRectSize(),this.getPixel_coord_y()+ 3 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x(),this.getPixel_coord_y() + 3 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 4 * getRectSize(),this.getPixel_coord_y()+ getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x(),this.getPixel_coord_y()+ getRectSize(),this.getRectSize(),this.getRectSize());

                gc.fillRect(this.getPixel_coord_x() + 3 * getRectSize(),this.getPixel_coord_y()+ 4 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + 3 * getRectSize(),this.getPixel_coord_y(), this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + getRectSize(),this.getPixel_coord_y()+ 4 * getRectSize(),this.getRectSize(),this.getRectSize());
                gc.fillRect(this.getPixel_coord_x() + getRectSize(),this.getPixel_coord_y(),this.getRectSize(),this.getRectSize());
            }

            else{
                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++){
                        gc.fillRect(this.getPixel_coord_x() + i * getRectSize(), this.getPixel_coord_y() + j * getRectSize(), this.getRectSize(), this.getRectSize());
                    }
                }
        }
    }
}
