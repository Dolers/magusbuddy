package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

public class QualificationType {
    @ColumnInfo(name = "type")
    public QualificationEntity.TypeEnum type;

    public QualificationEntity.TypeEnum getType() {
        return type;
    }
}
