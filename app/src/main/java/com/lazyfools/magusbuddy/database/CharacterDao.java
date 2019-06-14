package com.lazyfools.magusbuddy.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

@Dao
public abstract class CharacterDao {
    //@Query("SELECT * FROM characters")
    //List<CharacterEntity> getAll();

    @Query("SELECT * FROM characters")
    abstract LiveData<List<CharacterEntity>> getLiveAll();

    @Query("SELECT c.id, c.name FROM characters c, apikeys a WHERE a.mine = 1")
    abstract List<CharacterEntity> getMine();

    @Query("SELECT c.id, c.name FROM characters c, apikeys a WHERE a.mine = 0")
    abstract List<CharacterEntity> getShared();

    @Query("SELECT * FROM characters WHERE id IN (:characterIds)")
    abstract List<CharacterEntity> loadAllByIds(int[] characterIds);

    @Query("SELECT * FROM characters WHERE name LIKE :name LIMIT 1")
    abstract CharacterEntity findByName(String name);

    @Insert
    abstract void insert(CharacterEntity character);

    @Insert
    abstract void insertAll(CharacterEntity... character);

    @Delete
    abstract void delete(CharacterEntity character);

    @Query("DELETE FROM characters")
    abstract void deleteAll();
}
