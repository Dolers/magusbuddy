package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "sacralmagic")
public class SacralMagicEntity {
    public enum TypeEnum{
        KIS("Kis Arkánum"),
        NAGY("Nagy Arkánum"),
        AREL("Arel"),
        DARTON("Darton"),
        DOMVIK("Domvik"),
        DREINA("Dreina"),
        KRAD("Krad"),
        RANAGOL("Ranagol"),
        SOGRON("Sogron"),
        THARR("Tharr"),
        UWEL("Uwel");

        private final String name;

        private TypeEnum(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) { return name.equals(otherName); }
        public String toString() { return this.name; }

        static public TypeEnum enumOf(String value){
            switch(value){
                case "Kis Arkánum": return KIS;
                case "Nagy Arkánum": return NAGY;
                case "Arel": return AREL;
                case "Darton": return DARTON;
                case "Domvik": return DOMVIK;
                case "Dreina": return DREINA;
                case "Krad": return KRAD;
                case "Ranagol": return RANAGOL;
                case "Sogron": return SOGRON;
                case "Tharr": return THARR;
                case "Uwel": return UWEL;
            }
            return NAGY;
        }
    }

    public enum SphereEnum {ELET, HALAL, LELEK, TERMESZET}

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "name")
    private String _name;

    @ColumnInfo(name = "type")
    private TypeEnum _type;

    @ColumnInfo(name = "subType")
    private String _subType;

    @ColumnInfo(name = "kp")
    private Integer _kp;

    @ColumnInfo(name = "ekp")
    private Integer _ekp;

    @ColumnInfo(name = "ekptext")
    private String _ekpText;

    @ColumnInfo(name = "magicresistance")
    private String _magicResistance;

    @ColumnInfo(name = "sphere")
    private byte _sphere;

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

    public SacralMagicEntity(String name, TypeEnum type, String subType, Integer kp, Integer ekp, String ekpText, String magicResistance, byte sphere, String durationTime, String range, String castTime, String description, ArrayList<String> descTables) {
        _name = name;
        _type = type;
        _subType = subType;
        _kp = kp;
        _ekp = ekp;
        _ekpText = ekpText;
        _magicResistance = magicResistance;
        _sphere = sphere;
        _durationTime = durationTime;
        _range = range;
        _castTime = castTime;
        _description = description;
        _descTables = descTables;
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

    public String getSubType() {
        return _subType;
    }

    public void setSubType(String subType) {
        _subType = subType;
    }

    public Integer getKp() {
        return _kp;
    }

    public void setKp(Integer kp) {
        _kp = kp;
    }

    public Integer getEkp() {
        return _ekp;
    }

    public void setEkp(Integer ekp) {
        _ekp = ekp;
    }

    public String getEkpText() {
        return _ekpText;
    }

    public void setEkpText(String ekpText) {
        _ekpText = ekpText;
    }

    public String getMagicResistance() {
        return _magicResistance;
    }

    public void setMagicResistance(String magicResistance) {
        _magicResistance = magicResistance;
    }

    public byte getSphere() {
        return _sphere;
    }

    public void setSphere(byte sphere) {
        _sphere = sphere;
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
}
