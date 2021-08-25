package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "warlockmagic")
public class WarlockMagicEntity {
    public enum TypeEnum{
        ALAP("Alap varázslat"),
        VILLAM("Villám mágia"),
        ANYAG("Anyagmágia"),
        NEKROMANCIA("Nekromancia"),
        NEKROGRAFIA("Necrográfia"),
        ELETERO("Életerő manipulálás"),
        ATOK("Átok"),
        BETEGSEG("Betegségmágia"),
        MEREG("Méregmágia"),
        TERMESZETI("Természeti mágia"),
        ASZTRALMENTAL("Asztrál-mentál mágia"),
        ASCENSMORGA("Ascens Morga"),
        HERGOLI("Hergoli Villámmesterek");        ;

        private final String name;

        private TypeEnum(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) { return name.equals(otherName); }
        public String toString() { return this.name; }

       static public TypeEnum enumOf(String value){
            switch(value){
                case "Alap varázslat": return ALAP;
                case "Villám mágia": return VILLAM;
                case "Anyagmágia": return ANYAG;
                case "Nekromancia": return NEKROMANCIA;
                case "Necrográfia": return NEKROGRAFIA;
                case "Életerő manipulálás": return ELETERO;
                case "Átok": return ATOK;
                case "Betegségmágia": return BETEGSEG;
                case "Méregmágia": return MEREG;
                case "Természeti mágia": return TERMESZETI;
                case "Asztrál-mentál mágia": return ASZTRALMENTAL;
                case "Ascens Morga": return ASCENSMORGA;
                case "Hergoli Villámmesterek": return HERGOLI;
            }
            return ALAP;
        }
    }

    public enum SubTypeEnum{
        IDEZES("Idézés"),
        FORMAZAS("Formázás"),
        FELRUHAZAS("Felruházás"),
        NULL("");

        private final String name;

        private SubTypeEnum(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) { return name.equals(otherName); }
        public String toString() { return this.name; }

        static public SubTypeEnum enumOf(String value){
            switch(value){
                case "Idézés": return IDEZES;
                case "Formázás": return FORMAZAS;
                case "Felruházás": return FELRUHAZAS;
            }
            return NULL;
        }
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "name")
    private String _name;

    @ColumnInfo(name = "type")
    private TypeEnum _type;

    @ColumnInfo(name = "subType")
    private SubTypeEnum _subType;

    @ColumnInfo(name = "mp")
    private Integer _mp;

    @ColumnInfo(name = "emp")
    private Integer _emp;

    @ColumnInfo(name = "basice")
    private Integer _basice;

    @ColumnInfo(name = "magicresistance")
    private String _magicResistance;

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

    public WarlockMagicEntity(String name, TypeEnum type, SubTypeEnum subType, Integer mp, Integer emp, Integer basice, String magicResistance, String durationTime, String range, String castTime, String description, ArrayList<String> descTables, String special) {
        _name = name;
        _type = type;
        _subType = subType;
        _mp = mp;
        _emp = emp;
        _basice = basice;
        _magicResistance = magicResistance;
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

    public SubTypeEnum getSubType() {
        return _subType;
    }

    public void setSubType(SubTypeEnum subType) {
        _subType = subType;
    }

    public Integer getMp() {
        return _mp;
    }

    public void setMp(Integer mp) {
        _mp = mp;
    }

    public Integer getEmp() { return _emp; }

    public void setEmp(Integer eMp) {_emp = eMp; }

    public Integer getBasice() {
        return _basice;
    }

    public void setBasice(Integer basice) {
        _basice = basice;
    }

    public String getMagicResistance() {
        return _magicResistance;
    }

    public void setMagicResistance(String magicResistance) {
        _magicResistance = magicResistance;
    }

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
