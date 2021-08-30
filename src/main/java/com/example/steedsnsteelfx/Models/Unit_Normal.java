package com.example.steedsnsteelfx.Models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Unit_Normal {
    private eTileType _Type;
    private String _UnitID;
    private int _HP;
    private int _Atk;
    private int _Def;
    private int _MaxHealth;
    private String _Name; //Made for debug and tracking units, but we could keep it for a stretch goal.
    private int _Actions;
    private int _MaxActions;
    private boolean _SpecialAction;
    private boolean _Alive;
    private HashMap<Integer, eTileType> _AdjacentTiles;
    private List<String> _ImagePaths; //Path to the path file

    public Unit_Normal(eTileType _Type, int _MaxHealth, int _Atk, int _Def, String _Name
            , int _Actions, String _PathsTxt) {
        this._Type = _Type;
        this._HP = _MaxHealth;
        this._MaxHealth = _MaxHealth;
        this._Atk = _Atk;
        this._Def = _Def;
        this._Name = _Name;
        this._Actions = _Actions;
        this._MaxActions = _Actions;
        this._Alive = true;
        this._SpecialAction = true;

        System.out.println(_PathsTxt);
        String txtImagePaths = new File("").getAbsolutePath() + _PathsTxt;
        System.out.println(txtImagePaths);
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(txtImagePaths));
        } catch (IOException e) {
            e.printStackTrace();
        }
        _ImagePaths = lines;
    }

    public Unit_Normal(eTileType _Type, String _UnitID, int _HP, int _MaxHealth, int _Atk, int _Def, String _Name) {
        this._Type = _Type;
        this._UnitID = _UnitID;
        this._HP = _HP;
        this._MaxHealth = _MaxHealth;
        this._Atk = _Atk;
        this._Def = _Def;
        this._Name = _Name;
    }

    public eTileType get_Type() {
        return _Type;
    }

    public String get_UnitID() {
        return _UnitID;
    }

    public void set_UnitID(String _UnitID) {
        this._UnitID = _UnitID;
    }

    public int get_HP() {
        return _HP;
    }

    public void set_HP(int _HP) {
        if (_HP <= 0) { //Unit is dead
            //TODO Delete unit
            this._HP = 0;
            System.out.println("Unit_Normal dead. _HP=" + _HP);
        } else { //Unit is still alive and _HP > 0
            this._HP = _HP;
            System.out.println("Unit_Normal _HP=" + _HP);
        }
    }

    public int get_Atk() {
        return _Atk;
    }

    public void set_Atk(int _Atk) {
        if (_Atk < 0) {
            _Atk = 0;
        }
        this._Atk = _Atk;
    }

    public int get_Def() {
        return _Def;
    }

    public void set_Def(int _Def) {
        if (_Def < 0) {
            _Def = 0;
        }
        this._Def = _Def;
    }

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }

    public int get_MaxHealth() {
        return _MaxHealth;
    }

    public void set_MaxHealth(int _MaxHealth) {
        this._MaxHealth = _MaxHealth;
    }

    public int get_Actions() {
        return _Actions;
    }

    public void set_Actions(int _Actions) {
        this._Actions = _Actions;
    }

    public int get_MaxActions() {
        return _MaxActions;
    }

    public void set_MaxActions(int _MaxActions) {
        this._MaxActions = _MaxActions;
    }

    public String getImagePath(boolean _Selected) {
        String resultImage = "Data/TrafficCone.png";
        if (_Selected) {
            if (is_Expended()) {
                resultImage = _ImagePaths.get(3);
            } else {
                resultImage = _ImagePaths.get(1);
            }
        } else {
            if (is_Expended()) {
                resultImage = _ImagePaths.get(2);
            } else {
                resultImage = _ImagePaths.get(0);
            }
        }
        return resultImage;
    }

    public boolean is_Expended() {
        return (_Actions <= 0);
    }

    public boolean is_SpecialAction() {
        return _SpecialAction;
    }

    public void set_SpecialAction(boolean _SpecialAction) {
        this._SpecialAction = _SpecialAction;
    }

    public boolean is_Alive() {
        return _Alive;
    }

    public void set_Alive(boolean _Alive) {
        this._Alive = _Alive;
    }
}
