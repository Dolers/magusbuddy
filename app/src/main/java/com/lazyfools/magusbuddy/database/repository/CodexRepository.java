package com.lazyfools.magusbuddy.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.CodexDao;
import com.lazyfools.magusbuddy.database.entity.CodexEntity;

import java.util.List;

public class CodexRepository extends AbstractRepository<CodexDao, CodexEntity> {
    public CodexRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _dao = db.codexDao();
    }

    public LiveData<List<CodexEntity>> getLiveAll() {
        return _dao.getLiveAll();
    }
}
