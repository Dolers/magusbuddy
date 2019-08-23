package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;

import java.util.List;

@Dao
public abstract class HighMagicDao implements IBaseDao<HighMagicEntity> {
    @Query("SELECT * FROM magasmagia")
    public abstract LiveData<List<HighMagicEntity>> getLiveAll();

    @Query("DELETE FROM magasmagia")
    public abstract void deleteAll();
}
