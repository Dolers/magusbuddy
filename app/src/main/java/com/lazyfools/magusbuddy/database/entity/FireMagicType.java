package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

public class FireMagicType {
    @ColumnInfo(name = "type")
    public FireMagicEntity.TypeEnum type;

    public FireMagicEntity.TypeEnum getType() {
        return type;
    }
}
