package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

public class SacralMagicType {
    @ColumnInfo(name = "type")
    public SacralMagicEntity.TypeEnum type;

    public SacralMagicEntity.TypeEnum getType() {
        return type;
    }
}
