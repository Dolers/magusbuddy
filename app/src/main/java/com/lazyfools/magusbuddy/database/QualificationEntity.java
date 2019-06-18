package com.lazyfools.magusbuddy.database;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "qualifications")
public class QualificationEntity {
    public enum QualificationTypeEnum{
        HARCI,
        VILAGI,
        SZOCIALIS,
        ALVILAGI,
        TUDOMANYOS,
        MISZTIKUS
    }

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "type")
    private QualificationTypeEnum mType;

    @ColumnInfo(name = "difficulty")
    private int mDifficulty;

    @ColumnInfo(name = "description")
    private String mDescription;

    public QualificationEntity(@NonNull int mId, String mName, QualificationTypeEnum mType, int mDifficulty, String mDescription) {
        this.mId = mId;
        this.mName = mName;
        this.mType = mType;
        this.mDifficulty = mDifficulty;
        this.mDescription = mDescription;
    }

    public int getId() { return mId;}
    public void setId(int mId) { this.mId = mId; }

    public String getName() { return mName; }
    public void setName(String mName) { this.mName = mName; }

    public QualificationTypeEnum getType() { return mType; }
    public void setType(QualificationTypeEnum mType) { this.mType = mType; }

    public int getDifficulty() { return mDifficulty; }
    public void setDifficulty(int mDifficulty) { this.mDifficulty = mDifficulty; }

    public String getDescription() { return mDescription; }
    public void setDescription(String mDescription) { this.mDescription = mDescription; }

}
