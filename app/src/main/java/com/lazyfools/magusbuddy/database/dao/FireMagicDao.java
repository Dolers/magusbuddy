package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.FireMagicEntity;
import com.lazyfools.magusbuddy.database.entity.FireMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

@Dao
public abstract class FireMagicDao implements IBaseDao<FireMagicEntity> {
    @Query("SELECT * FROM firemagic")
    public abstract LiveData<List<FireMagicEntity>> getLiveAll();

    @Query("SELECT id, name FROM firemagic WHERE type = :type")
    public abstract LiveData<List<NameEntity>> getLiveAllNamesOfType(FireMagicEntity.TypeEnum type);

    @Query("SELECT id, name FROM firemagic")
    public abstract LiveData<List<NameEntity>> getAllNames();

    @Query("SELECT DISTINCT type FROM firemagic")
    public abstract LiveData<List<FireMagicType>> getTypes();

    @Query("DELETE FROM firemagic")
    public abstract void deleteAll();

    @Query("SELECT * FROM firemagic WHERE name LIKE :name LIMIT 1")
    public abstract LiveData<FireMagicEntity> getOneByName(String name);

    @Query("SELECT * FROM firemagic WHERE id=:id LIMIT 1")
    public abstract LiveData<FireMagicEntity> getOneByID(Integer id);
}
