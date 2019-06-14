package com.lazyfools.magusbuddy.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "characters")
public class CharacterEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    //TODO extend with additional paramters
    public int getId() {return mId;}
    public void setId(int id) {this.mId = id;}

    public String getName() { return mName;}
    public void setName(String name) {this.mName = name;}

    CharacterEntity(@NonNull int id, String name){
        mId = id;
        mName = name;
    }



    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}
