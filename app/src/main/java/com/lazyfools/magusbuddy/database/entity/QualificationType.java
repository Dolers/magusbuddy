package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;

public class QualificationType {
    @ColumnInfo(name = "type")
    public QualificationEntity.QualificationTypeEnum type;

    public QualificationEntity.QualificationTypeEnum getType() {
        return type;
    }
}
