package com.lazyfools.magusbuddy.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

@Dao
public abstract class QualificationDao {
    @Query("SELECT * FROM qualifications")
    abstract LiveData<List<QualificationEntity>> getLiveAll();

    @Query("SELECT * FROM qualifications WHERE name LIKE :name LIMIT 1")
    abstract QualificationEntity findByName(String name);

    @Insert
    abstract void insert(QualificationEntity qualification);

    @Insert
    abstract void insertAll(QualificationEntity... qualification);

    @Delete
    abstract void delete(QualificationEntity qualification);

    @Query("DELETE FROM qualifications")
    abstract void deleteAll();
}
