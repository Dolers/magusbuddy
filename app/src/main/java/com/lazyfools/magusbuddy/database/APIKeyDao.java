package com.lazyfools.magusbuddy.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public abstract class APIKeyDao {
    @Insert
    abstract void insert(APIKeyEntity apiKey);

    @Insert
    abstract void insertAll(APIKeyEntity... apiKey);

    @Delete
    abstract void delete(APIKeyEntity apiKey);

    @Query("DELETE FROM apikeys")
    abstract void deleteAll();

    @Query("SELECT * FROM apikeys")
    abstract LiveData<List<APIKeyEntity>> getLiveAll();
}
