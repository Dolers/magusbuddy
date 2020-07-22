package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

public class WarlockMagicType {
    @ColumnInfo(name = "type")
    public WarlockMagicEntity.TypeEnum type;

    public WarlockMagicEntity.TypeEnum getType() {
        return type;
    }
}
