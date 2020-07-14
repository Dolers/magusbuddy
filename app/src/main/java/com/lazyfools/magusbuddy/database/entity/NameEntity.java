package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

import ir.mirrajabi.searchdialog.core.Searchable;

public class NameEntity implements Searchable {
    @ColumnInfo(name = "id")
    public Integer _id;

    @ColumnInfo(name = "name")
    public String _name;

    public Integer getId() { return _id; }
    public String getName() {
        return _name;
    }
    public String getTitle()  {
        return _name;
    }

    @Override
    public String toString() {
        return _name;
    }
}
