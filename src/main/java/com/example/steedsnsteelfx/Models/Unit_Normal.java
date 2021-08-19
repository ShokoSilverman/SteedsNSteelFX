package com.example.steedsnsteelfx.Models;

import java.util.HashMap;

public class Unit_Normal {
    private eTileType _Type;
    private int _HP;
    private int _Atk;
    private int _Def;
    private int _MaxHealth;
    private String _Name; //Made for debug and tracking units, but we could keep it for a stretch goal.
    private int[] _CurrentLocation;
    private HashMap<Integer, eTileType> _AdjacentTiles;

    public Unit_Normal(eTileType _Type, int _HP, int _Atk, int _Def) {
        this._Type = _Type;
        this._HP = _HP;
        this._Atk = _Atk;
        this._Def = _Def;
    }

    public Unit_Normal(eTileType _Type, int _HP, int _Atk, int _Def, int[] _CurrentLocation){
        this._Type = _Type;
        this._HP = _HP;
        this._Atk = _Atk;
        this._Def = _Def;
        this._CurrentLocation = _CurrentLocation;
    }

    public Unit_Normal(eTileType _Type, int _HP, int _MaxHealth, int _Atk, int _Def, String _Name) {
        this._Type = _Type;
        this._HP = _HP;
        this._MaxHealth = _MaxHealth;
        this._Atk = _Atk;
        this._Def = _Def;
        this._Name = _Name;
    }

    public int get_HP() {
        return _HP;
    }

    public void set_HP(int _HP) {
        if (_HP <= 0) { //Unit is dead
            //TODO Delete unit
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

    public int[] get_CurrentLocation() {
        return _CurrentLocation;
    }

    public void set_CurrentLocation(int[] _CurrentLocation) {
        this._CurrentLocation = _CurrentLocation;
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
}
