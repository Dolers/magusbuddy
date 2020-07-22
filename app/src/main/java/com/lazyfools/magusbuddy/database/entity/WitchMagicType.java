package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

public class WitchMagicType {
    @ColumnInfo(name = "type")
    public WitchMagicEntity.TypeEnum type;

    public WitchMagicEntity.TypeEnum getType() {
        return type;
    }
}
