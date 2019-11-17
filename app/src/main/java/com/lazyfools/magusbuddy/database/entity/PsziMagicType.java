package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

public class PsziMagicType {
    @ColumnInfo(name = "type")
    public PsziMagicEntity.TypeEnum type;

    public PsziMagicEntity.TypeEnum getType() {
        return type;
    }
}
