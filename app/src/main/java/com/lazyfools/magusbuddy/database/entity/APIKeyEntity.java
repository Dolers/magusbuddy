package com.lazyfools.magusbuddy.database.entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "apikeys")
public class APIKeyEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer _id;

    @ColumnInfo(name = "hash")
    private String _hash;

    @ColumnInfo(name = "mine")
    private boolean _mine;

    //TODO extend with additional paramters
    public Integer getId() {return _id;}
    public void setId(Integer id) {this._id = id;}

    public String getHash() { return _hash;}
    public void setHash(String name) {this._hash = name;}

    public boolean isMine() {return _mine;}
    public void setMine(boolean value) {this._mine = _mine;}

    public APIKeyEntity(String hash, boolean mine){
        _hash = hash;
        _mine = mine;
    }
}
