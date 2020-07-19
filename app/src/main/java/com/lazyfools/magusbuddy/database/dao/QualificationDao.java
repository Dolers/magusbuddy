package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.List;

@Dao
public abstract class QualificationDao implements IBaseDao<QualificationEntity> {
    @Query("SELECT * FROM qualifications")
    public abstract LiveData<List<QualificationEntity>> getLiveAll();

    @Query("SELECT id, name FROM qualifications WHERE type = :type")
    public abstract LiveData<List<NameEntity>> getLiveAllNamesOfType(QualificationEntity.TypeEnum type);

    @Query("SELECT DISTINCT type FROM qualifications")
    public abstract LiveData<List<QualificationType>> getTypes();

    @Query("SELECT id, name FROM qualifications")
    public abstract LiveData<List<NameEntity>> getAllNames();

    @Query("DELETE FROM qualifications")
    public abstract void deleteAll();

    @Query("SELECT * FROM qualifications WHERE name LIKE :name LIMIT 1")
    public abstract LiveData<QualificationEntity> getOneByName(String name);

    @Query("SELECT * FROM qualifications WHERE id=:id LIMIT 1")
    public abstract LiveData<QualificationEntity> getOneByID(Integer id);
}
