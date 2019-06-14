package com.lazyfools.magusbuddy.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface APIKeyDao {
    @Insert
    void insert(APIKeyEntity apiKey);

    @Insert
    void insertAll(APIKeyEntity... apiKey);

    @Delete
    void delete(APIKeyEntity apiKey);

    @Query("DELETE FROM apikeys")
    void deleteAll();

    @Query("SELECT * FROM apikeys")
    LiveData<List<APIKeyEntity>> getLiveAll();
}
