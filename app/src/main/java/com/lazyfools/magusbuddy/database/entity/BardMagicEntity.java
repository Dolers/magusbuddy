package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "bardmagic")
public class BardMagicEntity {
    public enum TypeEnum{
        DAL("Dalmágia"),
        FENY("Fénymágia"),
        HANG("Hangmágia"),
        EGYEB("Egyéb bárdmágia");

        private final String name;

        private TypeEnum(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) { return name.equals(otherName); }
        public String toString() { return this.name; }

        static public TypeEnum enumOf(String value){
            switch(value){
                case "Dalmágia": return DAL;
                case "Fénymágia": return FENY;
                case "Hangmágia": return HANG;
                case "Egyéb bárdmágia": return EGYEB;
            }
            return EGYEB;
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

    public BardMagicEntity(String name, TypeEnum type, Integer mp, Integer emp, String magicResistance, String durationTime, String range, String castTime, String description) {
        _name = name;
        _type = type;
        _mp = mp;
        _emp = emp;
        _magicResistance = magicResistance;
        _durationTime = durationTime;
        _range = range;
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

    public Integer getEmp() { return _emp; }

    public void setEmp(Integer eMp) {_emp = eMp; }

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
}
