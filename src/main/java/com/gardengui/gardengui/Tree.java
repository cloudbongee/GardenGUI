package com.gardengui.gardengui;

import com.gardengui.gardengui.Plant;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tree extends Plant {
    private final Color color;
    public Tree(int x, int y, String species, String family, int rectSize, int plotSize){
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
        this.drawHelper(gc, 1);
        System.out.println(this.height);
    }
    private void drawHelper(GraphicsContext gc, int heightCount) {
        if(heightCount > 5 || heightCount == this.height + 1) return;
        else{
            gc.fillRect(this.getPixel_coord_x() + 2 * this.getRectSize(), this.getPixel_coord_y() + (5 - heightCount) * this.getRectSize(), this.getRectSize(), this.getRectSize());
            this.drawHelper(gc, heightCount + 1);
        }
    }
}
