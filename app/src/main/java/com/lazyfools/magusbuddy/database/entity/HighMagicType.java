package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

public class HighMagicType {
    @ColumnInfo(name = "type")
    public HighMagicEntity.TypeEnum type;

    public HighMagicEntity.TypeEnum getType() {
        return type;
    }
}
