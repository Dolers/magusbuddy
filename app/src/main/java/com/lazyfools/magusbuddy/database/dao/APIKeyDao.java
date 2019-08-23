package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.APIKeyEntity;

import java.util.List;

@Dao
public abstract class APIKeyDao implements IBaseDao<APIKeyEntity> {
    @Query("DELETE FROM apikeys")
    public abstract void deleteAll();

    @Query("SELECT * FROM apikeys")
    public abstract LiveData<List<APIKeyEntity>> getLiveAll();
}
