package com.lazyfools.magusbuddy.database;

import android.arch.persistence.room.TypeConverter;

import com.lazyfools.magusbuddy.database.entity.QualificationEntity;

public class Converters {
    @TypeConverter
    public static String fromQualificationType(QualificationEntity.QualificationTypeEnum value) {
        return value == null ? null : value.toString();
    }

    @TypeConverter
    public static QualificationEntity.QualificationTypeEnum QualificationTypeToInt(String value) {
        return QualificationEntity.QualificationTypeEnum.valueOf(value);
    }
}
