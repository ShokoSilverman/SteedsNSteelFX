package com.example.steedsnsteelfx.Models;

import javafx.scene.layout.GridPane;

import java.util.HashMap;

public class BattleManager {

    private final int gridWidth;
    private final int gridHeight;
    public BattleManager(GridPane gridPane) {
        this.gridWidth = gridPane.getColumnCount();
        this.gridHeight = gridPane.getRowCount();
    }

    public eTileType checkTile(int dir, int[] cords, GridPane gridPane){
        eTileType type = eTileType.UNAVAILABLE;

        switch (dir){
            case 0: // North
                cords[1] -= 1;
                break;
            case 1: // East
                cords[0] += 1;
                break;
            case 2: // South
                cords[1] += 1;
                break;
            case 3: // West
                cords[0] -= 1;
                break;
            default:
                System.out.println("You Shouldn't be here... dir="+dir+" | Unit_Normal.checkTile.switch(dir)");
                break;
        }

        if (((cords[0] >= 0) && (cords[0] <= gridWidth) && ((cords[1] >= 0) && (cords[1] <= gridHeight)))){
            //Use cords to get info from corresponding tile
            if (false/*nothing from tile*/){
                //default to eTileType.AVAILABLE
            }
        }

        return type;
    }
}
