package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.BattlesituationEntity;

import java.util.List;

@Dao
public abstract class BattlesituationDao implements IBaseDao<BattlesituationEntity> {
    @Query("SELECT * FROM battlesituations")
    public abstract LiveData<List<BattlesituationEntity>> getLiveAll();

    @Query("SELECT id, name FROM battlesituations")
    public abstract LiveData<List<NameEntity>> getAllNames();

    @Query("DELETE FROM battlesituations")
    public abstract void deleteAll();

    @Query("SELECT * FROM battlesituations WHERE name LIKE :name LIMIT 1")
    public abstract LiveData<BattlesituationEntity> getOneByName(String name);

    @Query("SELECT * FROM battlesituations WHERE id=:id LIMIT 1")
    public abstract LiveData<BattlesituationEntity> getOneByID(Integer id);
}
