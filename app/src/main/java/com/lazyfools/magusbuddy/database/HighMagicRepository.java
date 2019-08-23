package com.lazyfools.magusbuddy.database;

import android.app.Application;

import com.lazyfools.magusbuddy.database.dao.HighMagicDao;
import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;

public class HighMagicRepository extends AbstractRepository<HighMagicDao, HighMagicEntity> {
    public HighMagicRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _dao = db.highMagicDao();
    }
}
