package com.example.steedsnsteelfx.Models;

public class Unit_Normal {
    private int _HP;
    private int _Atk;
    private int _Def;
    private String _Name; //Made for debug and tracking units, but we could keep it for a stretch goal.

    public Unit_Normal(int _HP, int _Atk, int _Def) {
        this._HP = _HP;
        this._Atk = _Atk;
        this._Def = _Def;
    }

    public Unit_Normal(int _HP, int _Atk, int _Def, String _Name) {
        this._HP = _HP;
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

    public String get_Name() {
        return _Name;
    }

    public void set_Name(String _Name) {
        this._Name = _Name;
    }
}
