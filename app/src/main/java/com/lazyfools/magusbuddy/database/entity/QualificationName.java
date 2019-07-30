package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

import ir.mirrajabi.searchdialog.core.Searchable;

public class QualificationName  implements Searchable {
    @ColumnInfo(name = "id")
    public Integer mId;

    @ColumnInfo(name = "name")
    public String mName;

    public Integer getId() { return mId; }
    public String getName() {
        return mName;
    }
    public String getTitle()  {
        return mName;
    }
}
