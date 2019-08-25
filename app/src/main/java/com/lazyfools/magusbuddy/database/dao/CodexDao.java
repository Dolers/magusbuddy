package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.CodexEntity;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

@Dao
public abstract class CodexDao implements IBaseDao<CodexEntity> {
    @Query("SELECT * FROM codextable")
    public abstract LiveData<List<CodexEntity>> getLiveAll();

    @Query("DELETE FROM codextable")
    public abstract void deleteAll();
}
