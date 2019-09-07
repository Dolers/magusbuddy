package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.SacralMagicEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.SacralMagicEntity;
import com.lazyfools.magusbuddy.database.entity.SacralMagicType;

import java.util.List;

@Dao
public abstract class SacralMagicDao implements IBaseDao<SacralMagicEntity> {
    @Query("SELECT * FROM sacralmagic")
    public abstract LiveData<List<SacralMagicEntity>> getLiveAll();

    @Query("SELECT id, name FROM sacralmagic WHERE type = :type")
    public abstract LiveData<List<NameEntity>> getLiveAllNamesOfType(SacralMagicEntity.TypeEnum type);

    @Query("SELECT id, name FROM sacralmagic")
    public abstract LiveData<List<NameEntity>> getAllNames();

    @Query("SELECT DISTINCT type FROM sacralmagic")
    public abstract LiveData<List<SacralMagicType>> getTypes();

    @Query("SELECT id, name FROM sacralmagic WHERE name LIKE  '%' || :name || '%'")
    public abstract LiveData<List<NameEntity>> getNamesByFilter(String name);

    @Query("DELETE FROM sacralmagic")
    public abstract void deleteAll();

    @Query("SELECT * FROM sacralmagic WHERE name LIKE :name LIMIT 1")
    public abstract LiveData<SacralMagicEntity> getOneByName(String name);

    @Query("SELECT * FROM sacralmagic WHERE id=:id LIMIT 1")
    public abstract LiveData<SacralMagicEntity> getOneByID(Integer id);
}
