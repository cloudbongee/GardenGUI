package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bonsai extends Plant {
    private Color color;
    public Bonsai(int x, int y, String species, String family, int rectSize, int plotSize){
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

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        this.drawHelper(gc, 0, 0);
    }
    private void drawHelper(GraphicsContext gc, int heightCounter, int YOffset) {
        if(heightCounter == this.height) gc.fillRect(this.getPixel_coord_x() + 2*getRectSize(), this.getPixel_coord_y()+2*getRectSize(),getRectSize(), getRectSize());
        else{
            for(int i = 0; i < heightCounter; i++){
                if(i < 3){
                    if(YOffset > 2) YOffset -= 2;
                    gc.fillRect(this.getPixel_coord_x() + 2 * this.getRectSize() + i * getRectSize(), this.getPixel_coord_y() + (2 + YOffset) * getRectSize(),
                            this.getRectSize(), this.getRectSize());
                    gc.fillRect(this.getPixel_coord_x() + 2 * this.getRectSize() - i * getRectSize(), this.getPixel_coord_y() + (2 - YOffset) * getRectSize(),
                            this.getRectSize(), this.getRectSize());
                }
            }
            this.drawHelper(gc, heightCounter + 1, YOffset+1);
        }
    }

}
