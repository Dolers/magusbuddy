package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.WitchMagicEntity;
import com.lazyfools.magusbuddy.database.entity.WitchMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

@Dao
public abstract class WitchMagicDao implements IBaseDao<WitchMagicEntity> {
    @Query("SELECT * FROM witchmagic")
    public abstract LiveData<List<WitchMagicEntity>> getLiveAll();

    @Query("SELECT id, name FROM witchmagic WHERE type = :type")
    public abstract LiveData<List<NameEntity>> getLiveAllNamesOfType(WitchMagicEntity.TypeEnum type);

    @Query("SELECT id, name FROM witchmagic")
    public abstract LiveData<List<NameEntity>> getAllNames();

    @Query("SELECT DISTINCT type FROM witchmagic")
    public abstract LiveData<List<WitchMagicType>> getTypes();

    @Query("DELETE FROM witchmagic")
    public abstract void deleteAll();

    @Query("SELECT * FROM witchmagic WHERE name LIKE :name LIMIT 1")
    public abstract LiveData<WitchMagicEntity> getOneByName(String name);

    @Query("SELECT * FROM witchmagic WHERE id=:id LIMIT 1")
    public abstract LiveData<WitchMagicEntity> getOneByID(Integer id);
}
