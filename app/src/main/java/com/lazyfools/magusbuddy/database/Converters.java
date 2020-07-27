package com.lazyfools.magusbuddy.database;

import android.arch.persistence.room.TypeConverter;

import com.lazyfools.magusbuddy.database.entity.BardMagicEntity;
import com.lazyfools.magusbuddy.database.entity.CodexEntity;
import com.lazyfools.magusbuddy.database.entity.FireMagicEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;
import com.lazyfools.magusbuddy.database.entity.PsziMagicEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.SacralMagicEntity;
import com.lazyfools.magusbuddy.database.entity.WarlockMagicEntity;
import com.lazyfools.magusbuddy.database.entity.WitchMagicEntity;

import java.util.ArrayList;
import java.util.Arrays;

public class Converters {
    @TypeConverter
    public static String fromCodexType(CodexEntity.TypeEnum value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public static CodexEntity.TypeEnum CodexTypeToInt(String value) {
        return CodexEntity.TypeEnum.valueOf(value);
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
    public static String fromSacralMagicType(SacralMagicEntity.TypeEnum value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public static SacralMagicEntity.TypeEnum SacralMagicTypeToInt(String value) {
        return SacralMagicEntity.TypeEnum.valueOf(value);
    }
    
    @TypeConverter
    public static String fromBardMagicType(BardMagicEntity.TypeEnum value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public static BardMagicEntity.TypeEnum BardMagicTypeToInt(String value) {
        return BardMagicEntity.TypeEnum.valueOf(value);
    }

    @TypeConverter
    public static String fromPsziMagicType(PsziMagicEntity.TypeEnum value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public static PsziMagicEntity.TypeEnum PsziMagicTypeToInt(String value) {
        return PsziMagicEntity.TypeEnum.valueOf(value);
    }

    @TypeConverter
    public static String fromFireMagicType(FireMagicEntity.TypeEnum value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public static FireMagicEntity.TypeEnum FireMagicTypeToInt(String value) {
        return FireMagicEntity.TypeEnum.valueOf(value);
    }

    @TypeConverter
    public static String fromWitchMagicType(WitchMagicEntity.TypeEnum value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public static WitchMagicEntity.TypeEnum WitchMagicTypeToInt(String value) {
        return WitchMagicEntity.TypeEnum.valueOf(value);
    }

    @TypeConverter
    public static String fromWarlockMagicType(WarlockMagicEntity.TypeEnum value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public static WarlockMagicEntity.TypeEnum WarlockMagicTypeToInt(String value) {
        return WarlockMagicEntity.TypeEnum.valueOf(value);
    }

    @TypeConverter
    public static String fromWarlockMagicSubType(WarlockMagicEntity.SubTypeEnum value) {
        return value == null ? null : value.name();
    }

    @TypeConverter
    public static WarlockMagicEntity.SubTypeEnum WarlockMagicSubTypeToInt(String value) {
        return WarlockMagicEntity.SubTypeEnum.valueOf(value);
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
