package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "witchmagic")
public class WitchMagicEntity {
    public enum TypeEnum{
        ALAP("Alapvető varázslatok"),
        MISZTIKUS("Misztikus képességek"),
        TUZ("Tűzmágia"),
        ANYAG("Anyagi Mágia"),
        ASZTRAL("Asztrális praktikák"),
        ERZELEMKIOLT("Érzelemkioltó varázslatok"),
        ERZELEMKELT("Érzelemkeltő varázslatok"),
        ATOK("Átkok"),
        JELLEM("Jellemtorzító Átkok"),
        MENTAL("Mentális praktikák"),
        LELEK("Lélekvarázs"),
        ALOM("Álommágia"),
        SZEX("Szexuálmágia"),
        GYERTA("Gyertyamágia"),
        SZIMPATIKUS("Szimpatikus Mágia"),
        VIASZBAB("Viaszbábok Mágiája"),
        TER("Térmágia"),
        FAMILIARIS("Familiáris Mágia"),
        BAJITAL("Bájitalok");

        private final String name;

        private TypeEnum(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) { return name.equals(otherName); }
        public String toString() { return this.name; }

       static public TypeEnum enumOf(String value){
            switch(value){
                case "Alapvető varázslatok": return ALAP;
                case "Misztikus képességek": return MISZTIKUS;
                case "Tűzmágia": return TUZ;
                case "Anyagi Mágia": return ANYAG;
                case "Asztrális praktikák": return ASZTRAL;
                case "Érzelemkioltó varázslatok": return ERZELEMKIOLT;
                case "Érzelemkeltő varázslatok": return ERZELEMKELT;
                case "Átkok": return ATOK;
                case "Jellemtorzító Átkok": return JELLEM;
                case "Mentális praktikák": return MENTAL;
                case "Lélekvarázs": return LELEK;
                case "Álommágia": return ALOM;
                case "Szexuálmágia": return SZEX;
                case "Gyertyamágia": return GYERTA;
                case "Szimpatikus Mágia": return SZIMPATIKUS;
                case "Viaszbábok Mágiája": return VIASZBAB;
                case "Térmágia": return TER;
                case "Familiáris Mágia": return FAMILIARIS;
                case "Bájitalok": return BAJITAL;
            }
            return ALAP;
        }
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "name")
    private String _name;

    @ColumnInfo(name = "type")
    private TypeEnum _type;

    @ColumnInfo(name = "mp")
    private Integer _mp;

    @ColumnInfo(name = "emp")
    private Integer _emp;

    @ColumnInfo(name = "durationtime")
    private String _durationTime;

    @ColumnInfo(name = "range")
    private String _range;

    @ColumnInfo(name = "casttime")
    private String _castTime;

    @ColumnInfo(name = "description")
    private String _description;

    @ColumnInfo(name = "desctables")
    private ArrayList<String> _descTables;

    @ColumnInfo(name = "special")
    private String _special;

    public WitchMagicEntity(String name, TypeEnum type, Integer mp, Integer emp, String durationTime, String range, String castTime, String description, ArrayList<String> descTables, String special) {
        _name = name;
        _type = type;
        _mp = mp;
        _emp = emp;
        _durationTime = durationTime;
        _range = range;
        _castTime = castTime;
        _description = description;
        _descTables = descTables;
        _special = special;
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public TypeEnum getType() {
        return _type;
    }

    public void setType(TypeEnum type) {
        _type = type;
    }

    public Integer getMp() {
        return _mp;
    }

    public void setMp(Integer mp) {
        _mp = mp;
    }

    public Integer getEmp() { return _emp; }

    public void setEmp(Integer eMp) {_emp = eMp; }

    public String getDurationTime() {
        return _durationTime;
    }

    public void setDurationTime(String durationTime) {
        _durationTime = durationTime;
    }

    public String getRange() {
        return _range;
    }

    public void setRange(String range) {
        _range = range;
    }

    public String getCastTime() {
        return _castTime;
    }

    public void setCastTime(String castTime) {
        _castTime = castTime;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public ArrayList<String> getDescTables() {
        return _descTables;
    }

    public void setDescTables(ArrayList<String> descTables) {
        _descTables = descTables;
    }

    public String getSpecial() {
        return _special;
    }

    public void setSpecial(String special) {
        _special = special;
    }
}
