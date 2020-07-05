package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "firemagic")
public class FireMagicEntity {
    public enum TypeEnum{
        ALAP("Alapvető tűzmágiák"),
        VEDO("Védőmágiák"),
        ISKOLA("Iskolaformák"),
        SZABAD("Szabad elemi formák"),
        MAGASISKOLA("Tűzmágia magasiskolája"),
        KOZONSEGES("Közönséges tüzek befolyásolása"),
        OSTUZ("Őstüzek befolyásolása"),
        TUZLENY("Tűzlények idézése");

        private final String name;

        private TypeEnum(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) { return name.equals(otherName); }
        public String toString() { return this.name; }

        static public TypeEnum enumOf(String value){
            switch(value){
                case "Alapvető tűzmágiák": return ALAP;
                case "Védőmágiák": return VEDO;
                case "Iskolaformák": return ISKOLA;
                case "Szabad elemi formák": return SZABAD;
                case "Tűzmágia magasiskolája": return MAGASISKOLA;
                case "Közönséges tüzek befolyásolása": return KOZONSEGES;
                case "Őstüzek befolyásolása": return OSTUZ;
                case "Tűzlények idézése": return TUZLENY;
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

    @ColumnInfo(name = "durationtime")
    private String _durationTime;

    @ColumnInfo(name = "casttime")
    private String _castTime;

    @ColumnInfo(name = "description")
    private String _description;

    @ColumnInfo(name = "desctables")
    private ArrayList<String> _descTables;

    @ColumnInfo(name = "special")
    private String _special;

    public FireMagicEntity(String name, TypeEnum type, Integer mp, String durationTime, String castTime, String description) {
        _name = name;
        _type = type;
        _mp = mp;
        _durationTime = durationTime;
        _castTime = castTime;
        _description = description;
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

    public String getDurationTime() {
        return _durationTime;
    }

    public void setDurationTime(String durationTime) {
        _durationTime = durationTime;
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
