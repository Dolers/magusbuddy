package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "pszimagic")
public class PsziMagicEntity {
    public enum TypeEnum{
        PYARRONI("Pyarroni módszer"),
        GODONI("Godoni örökség"),
        KYR("Kyr Metódus"),
        SLAN("Slan útja");

        private final String name;

        private TypeEnum(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) { return name.equals(otherName); }
        public String toString() { return this.name; }
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "name")
    private String _name;

    @ColumnInfo(name = "type")
    private TypeEnum _type;

    @ColumnInfo(name = "subType")
    private String _subType;

    @ColumnInfo(name = "level")
    private Integer _level;

    @ColumnInfo(name = "mp")
    private Integer _mp;

    @ColumnInfo(name = "emp")
    private Integer _emp;

    @ColumnInfo(name = "emptext")
    private String _empText;

    @ColumnInfo(name = "magicresistance")
    private String _magicResistance;

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

    public PsziMagicEntity(String name, TypeEnum type, String subType, Integer level, Integer mp, Integer emp, String durationTime, String castTime, String description) {
        _name = name;
        _type = type;
        _subType = subType;
        _level = level;
        _mp = mp;
        _emp = emp;
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

    public String getSubType() {
        return _subType;
    }

    public void setSubType(String subType) {
        _subType = subType;
    }

    public Integer getLevel() {
        return _level;
    }

    public void setLevel(Integer level) {
        _level = level;
    }

    public Integer getMp() {
        return _mp;
    }

    public void setMp(Integer mp) {
        _mp = mp;
    }

    public Integer getEmp() {
        return _emp;
    }

    public void setEmp(Integer emp) {
        _emp = emp;
    }

    public String getEmpText() {
        return _empText;
    }

    public void setEmpText(String empText) {
        _empText = empText;
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
