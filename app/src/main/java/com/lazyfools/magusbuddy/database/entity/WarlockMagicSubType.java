package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

public class WarlockMagicSubType {
    @ColumnInfo(name = "type")
    public WarlockMagicEntity.SubTypeEnum type;

    public WarlockMagicEntity.SubTypeEnum getType() {
        return type;
    }
}
