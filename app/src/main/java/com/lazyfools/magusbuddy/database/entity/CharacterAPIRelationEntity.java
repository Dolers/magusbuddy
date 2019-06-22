package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "characterApiRelation",
        foreignKeys = {
            @ForeignKey(entity = CharacterEntity.class,
                        parentColumns = "id",
                        childColumns = "characterId",
                        onDelete = ForeignKey.CASCADE),  //delete all child when parent is deleted
            @ForeignKey(entity = APIKeyEntity.class,
                        parentColumns = "id",
                        childColumns = "apiKeyId",
                        onDelete = ForeignKey.CASCADE) //delete all child when parent is deleted
            },
        indices = {
            @Index("characterId"),
            @Index("apiKeyId")
            }
        )
public class CharacterAPIRelationEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "characterId")
    private int mCharacterId;

    @ColumnInfo(name = "apiKeyId")
    private int mApiKeyId;

    public int getId() {return mId;}
    public void setId(int mId) {this.mId = mId;}
    public int getCharacterId() {return mCharacterId;}
    public int getApiKeyId() {return mApiKeyId;}

    public CharacterAPIRelationEntity(int characterId, int apiKeyId){
        mCharacterId = characterId;
        mApiKeyId = apiKeyId;
    }
}
