package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

public class BardMagicType {
    @ColumnInfo(name = "type")
    public BardMagicEntity.TypeEnum type;

    public BardMagicEntity.TypeEnum getType() {
        return type;
    }
}
