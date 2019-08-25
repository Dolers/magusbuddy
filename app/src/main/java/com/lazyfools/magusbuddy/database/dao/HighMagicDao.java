package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

@Dao
public abstract class HighMagicDao implements IBaseDao<HighMagicEntity> {
    @Query("SELECT * FROM highmagic")
    public abstract LiveData<List<HighMagicEntity>> getLiveAll();

    @Query("SELECT id, name FROM highmagic WHERE type = :type")
    public abstract LiveData<List<NameEntity>> getLiveAllNamesOfType(HighMagicEntity.TypeEnum type);

    @Query("SELECT id, name FROM highmagic")
    public abstract LiveData<List<NameEntity>> getAllNames();

    @Query("SELECT DISTINCT type FROM highmagic")
    public abstract LiveData<List<HighMagicType>> getTypes();

    @Query("SELECT id, name FROM highmagic WHERE name LIKE  '%' || :name || '%'")
    public abstract LiveData<List<NameEntity>> getNamesByFilter(String name);

    @Query("DELETE FROM highmagic")
    public abstract void deleteAll();

    @Query("SELECT * FROM highmagic WHERE name LIKE :name LIMIT 1")
    public abstract LiveData<HighMagicEntity> getOneByName(String name);

    @Query("SELECT * FROM highmagic WHERE id=:id LIMIT 1")
    public abstract LiveData<HighMagicEntity> getOneByID(Integer id);
}
