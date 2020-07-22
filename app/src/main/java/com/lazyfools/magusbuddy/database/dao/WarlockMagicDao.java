package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.WarlockMagicEntity;
import com.lazyfools.magusbuddy.database.entity.WarlockMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

@Dao
public abstract class WarlockMagicDao implements IBaseDao<WarlockMagicEntity> {
    @Query("SELECT * FROM warlockmagic")
    public abstract LiveData<List<WarlockMagicEntity>> getLiveAll();

    @Query("SELECT id, name FROM warlockmagic WHERE type = :type")
    public abstract LiveData<List<NameEntity>> getLiveAllNamesOfType(WarlockMagicEntity.TypeEnum type);

    @Query("SELECT id, name FROM warlockmagic")
    public abstract LiveData<List<NameEntity>> getAllNames();

    @Query("SELECT DISTINCT type FROM warlockmagic")
    public abstract LiveData<List<WarlockMagicType>> getTypes();

    @Query("DELETE FROM warlockmagic")
    public abstract void deleteAll();

    @Query("SELECT * FROM warlockmagic WHERE name LIKE :name LIMIT 1")
    public abstract LiveData<WarlockMagicEntity> getOneByName(String name);

    @Query("SELECT * FROM warlockmagic WHERE id=:id LIMIT 1")
    public abstract LiveData<WarlockMagicEntity> getOneByID(Integer id);
}
