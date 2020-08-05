package com.lazyfools.magusbuddy.database.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

public interface IBaseDao<Entity> {
    @Insert
    void insert(Entity entity);

    @Insert
    void insertAll(Entity... entity);

    @Delete
    void delete(Entity entity);
}
