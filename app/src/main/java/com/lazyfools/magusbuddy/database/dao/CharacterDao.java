package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.CharacterEntity;

import java.util.List;

@Dao
public abstract class CharacterDao {
    //@Query("SELECT * FROM characters")
    //List<CharacterEntity> getAll();

    @Query("SELECT * FROM characters")
    public abstract LiveData<List<CharacterEntity>> getLiveAll();

    @Query("SELECT c.id, c.name FROM characters c, apikeys a WHERE a.mine = 1")
    public abstract List<CharacterEntity> getMine();

    @Query("SELECT c.id, c.name FROM characters c, apikeys a WHERE a.mine = 0")
    public abstract List<CharacterEntity> getShared();

    @Query("SELECT * FROM characters WHERE id IN (:characterIds)")
    public abstract List<CharacterEntity> loadAllByIds(int[] characterIds);

    @Query("SELECT * FROM characters WHERE name LIKE :name LIMIT 1")
    public abstract CharacterEntity findByName(String name);

    @Insert
    public abstract void insert(CharacterEntity character);

    @Insert
    public abstract void insertAll(CharacterEntity... character);

    @Delete
    public abstract void delete(CharacterEntity character);

    @Query("DELETE FROM characters")
    public abstract void deleteAll();
}
