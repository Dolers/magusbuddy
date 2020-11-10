package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "codextable")
public class CodexEntity {
    public enum TypeEnum{
        QUALIFICATION,
        BATTLESITUATION,
        BARDMAGIC,
        WITCHMAGIC,
        WARLOCKMAGIC,
        HIGHMAGIC,
        PSZIMAGIC,
        SACRALMAGIC,
        FIREMAGIC
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "name")
    private String _name;

    @ColumnInfo(name = "type")
    private TypeEnum _type;

    public CodexEntity(String name, TypeEnum type) {
        _name = name;
        _type = type;
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
}
