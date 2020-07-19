package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.PsziMagicEntity;
import com.lazyfools.magusbuddy.database.entity.PsziMagicType;

import java.util.List;

@Dao
public abstract class PsziMagicDao implements IBaseDao<PsziMagicEntity> {
    @Query("SELECT * FROM pszimagic")
    public abstract LiveData<List<PsziMagicEntity>> getLiveAll();

    @Query("SELECT id, name FROM pszimagic WHERE type = :type")
    public abstract LiveData<List<NameEntity>> getLiveAllNamesOfType(PsziMagicEntity.TypeEnum type);

    @Query("SELECT id, name FROM pszimagic")
    public abstract LiveData<List<NameEntity>> getAllNames();

    @Query("SELECT DISTINCT type FROM pszimagic")
    public abstract LiveData<List<PsziMagicType>> getTypes();

    @Query("DELETE FROM pszimagic")
    public abstract void deleteAll();

    @Query("SELECT * FROM pszimagic WHERE name LIKE :name LIMIT 1")
    public abstract LiveData<PsziMagicEntity> getOneByName(String name);

    @Query("SELECT * FROM pszimagic WHERE id=:id LIMIT 1")
    public abstract LiveData<PsziMagicEntity> getOneByID(Integer id);
}
