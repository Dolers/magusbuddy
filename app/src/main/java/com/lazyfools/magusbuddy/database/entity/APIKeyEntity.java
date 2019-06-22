package com.lazyfools.magusbuddy.database.entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "apikeys")
public class APIKeyEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer mId;

    @ColumnInfo(name = "hash")
    private String mHash;

    @ColumnInfo(name = "mine")
    private boolean mMine;

    //TODO extend with additional paramters
    public Integer getId() {return mId;}
    public void setId(Integer id) {this.mId = id;}

    public String getHash() { return mHash;}
    public void setHash(String name) {this.mHash = name;}

    public boolean isMine() {return mMine;}
    public void setMine(boolean value) {this.mMine = mMine;}

    public APIKeyEntity(String hash, boolean mine){
        mHash = hash;
        mMine = mine;
    }
}
