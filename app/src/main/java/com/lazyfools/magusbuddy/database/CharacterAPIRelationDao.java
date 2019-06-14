package com.lazyfools.magusbuddy.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

@Dao
public abstract class CharacterAPIRelationDao {
    @Query("SELECT * FROM characterApiRelation")
    abstract List<CharacterAPIRelationEntity> getAll();

    @Query("SELECT * FROM characterApiRelation WHERE apiKeyId LIKE :apiKey LIMIT 1")
    abstract CharacterAPIRelationEntity findByApiKey(String apiKey);

    @Query("SELECT * FROM characterApiRelation WHERE characterId = :charID LIMIT 1")
    abstract CharacterAPIRelationEntity findByCharacter(int charID);

    @Insert
    abstract void insert(CharacterAPIRelationEntity character);

}
