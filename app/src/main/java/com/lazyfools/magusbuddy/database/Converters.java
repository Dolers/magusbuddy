package com.lazyfools.magusbuddy.database;

import android.arch.persistence.room.TypeConverter;

import com.lazyfools.magusbuddy.database.entity.QualificationEntity;

import java.util.ArrayList;
import java.util.Arrays;

public class Converters {
    @TypeConverter
    public static String fromQualificationType(QualificationEntity.QualificationTypeEnum value) {
        return value == null ? null : value.toString();
    }

    @TypeConverter
    public static QualificationEntity.QualificationTypeEnum QualificationTypeToInt(String value) {
        return QualificationEntity.QualificationTypeEnum.valueOf(value);
    }

    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        if (value == null)
            return null;

        return new ArrayList<>(Arrays.asList(value.split("__end__")));
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        if (list == null)
            return null;

        StringBuilder sb = new StringBuilder();
        for(String s : list){
            sb.append(s);
            sb.append("__end__");
        }
        return sb.toString();
    }
}
