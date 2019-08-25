package com.lazyfools.magusbuddy.database;

import android.arch.persistence.room.TypeConverter;

import com.lazyfools.magusbuddy.database.entity.CodexEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;

import java.util.ArrayList;
import java.util.Arrays;

public class Converters {
    @TypeConverter
    public static String fromCodexTable(CodexEntity.TableEnum value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public static CodexEntity.TableEnum CodexTableToInt(String value) {
        return CodexEntity.TableEnum.valueOf(value);
    }

    @TypeConverter
    public static String fromQualificationType(QualificationEntity.TypeEnum value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public static QualificationEntity.TypeEnum QualificationTypeToInt(String value) {
        return QualificationEntity.TypeEnum.valueOf(value);
    }

    @TypeConverter
    public static String fromHighMagicType(HighMagicEntity.TypeEnum value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public static HighMagicEntity.TypeEnum HighMagicTypeToInt(String value) {
        return HighMagicEntity.TypeEnum.valueOf(value);
    }

    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        if (value == null)
            return new ArrayList<>();

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
