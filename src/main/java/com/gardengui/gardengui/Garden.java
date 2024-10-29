package com.gardengui.gardengui;

import java.util.Map;

import static java.util.Map.entry;

public class Garden {
    protected enum SPECIES  {FLOWER , TREE, VEGETABLE, BONSAI}
    protected Map<String, SPECIES> mapTypes = Map.ofEntries( // flowers

            entry("ROSE", SPECIES.FLOWER),

            entry("LILY", SPECIES.FLOWER),

            entry("DAISY", SPECIES.FLOWER),

            entry("TULIP", SPECIES.FLOWER),

            entry("IRIS", SPECIES.FLOWER),

            entry("SUNFLOWER", SPECIES.FLOWER),

            // trees

            entry("OAK", SPECIES.TREE),

            entry("WILLOW", SPECIES.TREE),

            entry("BANANA", SPECIES.TREE),

            entry("COCONUT", SPECIES.TREE),

            entry("PINE", SPECIES.TREE),

            // vegetable entries

            entry("GARLIC", SPECIES.VEGETABLE),

            entry("ZUCCHINI", SPECIES.VEGETABLE),

            entry("TOMATO", SPECIES.VEGETABLE),

            entry("YAM", SPECIES.VEGETABLE),

            entry("LETTUCE", SPECIES.VEGETABLE),

            // bonsai entries. I know that it is biologically controversial, but I though it would be cool

            entry("JUNIPER", SPECIES.BONSAI),

            entry("GINSENG", SPECIES.BONSAI),

            entry("BOUGANVILLIA", SPECIES.BONSAI),

            entry("CHERRY", SPECIES.BONSAI),

            entry("ELM", SPECIES.BONSAI),

            entry("SPRUCE", SPECIES.BONSAI)
    );


    private int rows;
    private int cols;
    public Plant[][] garden;

    public Garden(int rows, int cols) {
        this.rows = rows;
        // while the current class contains rows and columns, the information of rows and columns will be pixels
        // for the superclass of plants. Thus making it easier to represent.
        this.cols = cols;
        this.garden = new Plant[rows][cols];

        // TO DO: Consider the possibility of a representation class that holds together the screen on five times the
        // initial size of a pot (Just to make sure that it represents itself correctly)
    }

    public static int[] parseCoords(String parsed) {
        int[] coords = new int[2];
        // Removing parentheses and splitting by comma
        String[] toList = parsed.replaceAll("[()]", "").split(",");
        coords[0] = Integer.parseInt(toList[0].trim());
        coords[1] = Integer.parseInt(toList[1].trim());
        return coords;
    }

    /*

     */
    public void passCommand(String command){

    }

    public void Plant(int x_position, int y_position, String plantType){

    }
}
