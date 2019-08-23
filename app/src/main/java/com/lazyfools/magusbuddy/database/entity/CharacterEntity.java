package com.lazyfools.magusbuddy.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "characters")
public class CharacterEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "name")
    private String _name;

    //TODO extend with additional paramters
    public Integer getId() {return _id;}
    public void setId(Integer id) {this._id = id;}

    public String getName() { return _name;}
    public void setName(String name) {this._name = name;}

    public CharacterEntity(@NonNull Integer id, String name){
        _id = id;
        _name = name;
    }



    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}
