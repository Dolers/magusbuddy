package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.BardMagicEntity;
import com.lazyfools.magusbuddy.database.entity.BardMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

@Dao
public abstract class BardMagicDao implements IBaseDao<BardMagicEntity> {
    @Query("SELECT * FROM bardmagic")
    public abstract LiveData<List<BardMagicEntity>> getLiveAll();

    @Query("SELECT id, name FROM bardmagic WHERE type = :type")
    public abstract LiveData<List<NameEntity>> getLiveAllNamesOfType(BardMagicEntity.TypeEnum type);

    @Query("SELECT id, name FROM bardmagic WHERE type = :type")
    public abstract List<NameEntity> getAllNamesOfType(BardMagicEntity.TypeEnum type);

    @Query("SELECT id, name FROM bardmagic")
    public abstract LiveData<List<NameEntity>> getAllNames();

    @Query("SELECT DISTINCT type FROM bardmagic")
    public abstract LiveData<List<BardMagicType>> getTypes();

    @Query("SELECT id, name FROM bardmagic WHERE name LIKE  '%' || :name || '%'")
    public abstract LiveData<List<NameEntity>> getNamesByFilter(String name);

    @Query("DELETE FROM bardmagic")
    public abstract void deleteAll();

    @Query("SELECT * FROM bardmagic WHERE name LIKE :name LIMIT 1")
    public abstract LiveData<BardMagicEntity> getOneByName(String name);

    @Query("SELECT * FROM bardmagic WHERE id=:id LIMIT 1")
    public abstract LiveData<BardMagicEntity> getOneByID(Integer id);
}
