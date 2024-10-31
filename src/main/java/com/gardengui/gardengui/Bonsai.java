package com.gardengui.gardengui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * Bonsai class is the custom class created for the collection of plants that can be created in the application
 * Within the traits that I added to it, is an interesting recursive function to draw it, and
 * the collection of special methods outside of the class
 */
public class Bonsai extends Plant {
    // the specific color for the Bonsai is determined by the function getColor, set at the constructor for the class
    private Color color;
    public Bonsai(int x, int y, String species, String family, int rectSize, int plotSize){
        super(x,y,species,family, rectSize, plotSize);
        this.color = this.getColor();
    }

    /**
     * @return this.species
     *      The name of the general group to which the bonsai belongs ( Bonsai )
     */
    @Override
    protected String getSpecies() {
        return this.species;
    }

    /**
     * Creates a unique color for each type of bonsai
     * @return Color.rgb()
     *      The constructed color from the ords of the name
     */
    @Override
    protected Color getColor() {
        // Utilizing the first three letters of the name of the plant, round their ords and make them integer, then pass them as RGB values, this creates
        // unique colors for each of the plant types, but the same one if they share the same name
        return Color.rgb(Math.round((int) this.species.charAt(0)), Math.round((int) this.species.charAt(1)), Math.round((int) this.species.charAt(2)), 1);
    }

    /**
     * The draw function is called to create a collection of squares based on the current height of the object instance
     * In this case, the shape assumed starts from the center, and spans on opposite horizontal directions.
     * @param gc
     *      Contains information for the stage of JavaFX
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color);
        this.drawHelper(gc, 0, 0);
    }

    /**
     * The drawHelper method contains the recursive call and opperations that are made to collect the
     * @param gc
     *      Contains information for the stage of javaFX
     * @param heightCounter
     *      keeps track of the limit for the horizontal spanning of the recursive call
     * @param YOffset
     *      creates a distinction on the position of Y which will be drawn over
     */
    private void drawHelper(GraphicsContext gc, int heightCounter, int YOffset) {
        if(heightCounter == this.height) gc.fillRect(this.getPixel_coord_x() + 2*getRectSize(), this.getPixel_coord_y()+2*getRectSize(),getRectSize(), getRectSize());
        else{
            for(int i = 0; i < heightCounter; i++){ // declare a limit on the horizontal growth
                if(i < 3){ // do not permit the bounds go out of the 5x5 square
                    if(YOffset > 2) YOffset -= 2; // limit the growth of the y coordinate outside of the 5x5 plot
                    // draw on both horizontal sides
                    gc.fillRect(this.getPixel_coord_x() + 2 * this.getRectSize() + i * getRectSize(), this.getPixel_coord_y() + (2 + YOffset) * getRectSize(),
                            this.getRectSize(), this.getRectSize());
                    gc.fillRect(this.getPixel_coord_x() + 2 * this.getRectSize() - i * getRectSize(), this.getPixel_coord_y() + (2 - YOffset) * getRectSize(),
                            this.getRectSize(), this.getRectSize());
                }
            }
            // pass the recursive call with offset on the vertical drawings now
            this.drawHelper(gc, heightCounter + 1, YOffset+1);
        }
    }

}
