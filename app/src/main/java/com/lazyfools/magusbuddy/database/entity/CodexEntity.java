package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "codextable")
public class CodexEntity {
    public enum TableEnum{
        QUALIFICATION,
        BARDMAGIC,
        WITCHMAGIC,
        WARLOCKMAGIC,
        HIGHMAGIC,
        PSZI,
        SACRALMAGIC,
        FIREMAGEMAGIC;
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "name")
    private String _name;

    @ColumnInfo(name = "table")
    private TableEnum _table;

    @ColumnInfo(name = "imagename")
    private String _imageName;

    public CodexEntity(String name, TableEnum table, String imageName) {
        _name = name;
        _table = table;
        _imageName = imageName;
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

    public TableEnum getTable() {
        return _table;
    }

    public void setTable(TableEnum table) {
        _table = table;
    }

    public String getImageName() {
        return _imageName;
    }

    public void setImageName(String imageName) {
        _imageName = imageName;
    }
}
