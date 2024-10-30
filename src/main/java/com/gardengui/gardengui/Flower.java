package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Flower extends Plant {
    private Color color;
    public Flower(int x, int y, String species, String family, int rectSize, int plotSize){
        super(x,y,species,family, rectSize, plotSize);
        this.color = this.getColor();
    }

    @Override
    protected String getSpecies() {
        return this.species;
    }

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
