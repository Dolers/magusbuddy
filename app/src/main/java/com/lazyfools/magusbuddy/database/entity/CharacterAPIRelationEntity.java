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
    private int _id;

    @ColumnInfo(name = "characterId")
    private int _characterId;

    @ColumnInfo(name = "apiKeyId")
    private int _apiKeyId;

    public int getId() {return _id;}
    public void setId(int _id) {this._id = _id;}
    public int getCharacterId() {return _characterId;}
    public int getApiKeyId() {return _apiKeyId;}

    public CharacterAPIRelationEntity(int characterId, int apiKeyId){
        _characterId = characterId;
        _apiKeyId = apiKeyId;
    }
}
