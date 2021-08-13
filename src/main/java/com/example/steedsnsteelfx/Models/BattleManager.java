package com.example.steedsnsteelfx.Models;

import java.util.HashMap;

public class BattleManager {
    public void checkAdjacentTiles(Unit_Normal unit){
        HashMap<Integer, eTileType> _AdjacentTiles = new HashMap<>();
        int[] adjacentCord = unit.get_CurrentLocation();

        for (int dir = 0; dir < 4; dir++){
            //_AdjacentTiles.put(dir, checkTile(dir, adjacentCord)); //Check tile to be declared in another method
        }



        //_AdjacentTiles.put(0, checkTile(adjacentCord[1] - 1)); //Check Tile Doesn't Exist yet.
        //_AdjacentTiles.put(1, checkTile(adjacentCord[0] + 1));
        //_AdjacentTiles.put(2, checkTile(adjacentCord[1] + 1)); //Not viable option
        //_AdjacentTiles.put(3, checkTile(adjacentCord[0] + 1));



        /*North: Pull from tile above going First Array(_CurrentLocation[0]) and Second Array(_CurrentLocation[1]-1)
        //Get Type
        //East: Pull from tile above going First Array(_CurrentLocation[0]+1) and Second Array(_CurrentLocation[1])
        //Get Type
        //South: Pull from tile above going First Array(_CurrentLocation[0]) and Second Array(_CurrentLocation[1]+1)
        //West: Pull from tile above going First Array(_CurrentLocation[0]-1) and Second Array(_CurrentLocation[1])*/

//        this._AdjacentTiles = _AdjacentTiles;
    }
}
