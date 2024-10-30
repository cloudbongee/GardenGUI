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

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        gc.fillRect(this.getPixel_coord_x(), this.getPixel_coord_y(), getRectSize() * 5, getRectSize() * 5);
    }
}
