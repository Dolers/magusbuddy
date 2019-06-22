package com.lazyfools.magusbuddy.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.CharacterAPIRelationEntity;

import java.util.List;

@Dao
public abstract class CharacterAPIRelationDao {
    @Query("SELECT * FROM characterApiRelation")
    public abstract List<CharacterAPIRelationEntity> getAll();

    @Query("SELECT * FROM characterApiRelation WHERE apiKeyId LIKE :apiKey LIMIT 1")
    public abstract CharacterAPIRelationEntity findByApiKey(String apiKey);

    @Query("SELECT * FROM characterApiRelation WHERE characterId = :charID LIMIT 1")
    public abstract CharacterAPIRelationEntity findByCharacter(int charID);

    @Insert
    public abstract void insert(CharacterAPIRelationEntity character);

}
