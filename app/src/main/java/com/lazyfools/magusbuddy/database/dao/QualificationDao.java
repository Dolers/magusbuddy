package com.lazyfools.magusbuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationName;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.List;

@Dao
public abstract class QualificationDao {
    @Query("SELECT * FROM qualifications")
    public abstract LiveData<List<QualificationEntity>> getLiveAll();

    @Query("SELECT * FROM qualifications WHERE type = :type")
    public abstract LiveData<List<QualificationEntity>> getLiveAllOfType(QualificationEntity.QualificationTypeEnum type);

    @Query("SELECT DISTINCT type FROM qualifications")
    public abstract LiveData<List<QualificationType>> getTypes();

    @Query("SELECT name FROM qualifications")
    public abstract LiveData<List<QualificationName>> getAllNames();

    @Query("SELECT name FROM qualifications WHERE name LIKE  '%' || :name || '%'")
    public abstract LiveData<List<QualificationName>> getNamesByFilter(String name);

    @Insert
    public abstract void insert(QualificationEntity qualification);

    @Insert
    public abstract void insertAll(QualificationEntity... qualification);

    @Delete
    public abstract void delete(QualificationEntity qualification);

    @Query("DELETE FROM qualifications")
    public abstract void deleteAll();

    @Query("SELECT * FROM qualifications WHERE name LIKE :name LIMIT 1")
    public abstract LiveData<QualificationEntity> getOneByName(String name);

    @Query("SELECT * FROM qualifications WHERE id=:id LIMIT 1")
    public abstract LiveData<QualificationEntity> getOneByID(Integer id);
}
