package com.lazyfools.magusbuddy.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.HighMagicDao;
import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

public class HighMagicRepository extends AbstractRepository<HighMagicDao, HighMagicEntity> {
    public HighMagicRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _dao = db.highMagicDao();
    }

    public LiveData<List<HighMagicEntity>> getAll() {
        return _dao.getLiveAll();
    }

    public LiveData<List<NameEntity>> getAllNames() {
        return _dao.getAllNames();
    }

    public LiveData<HighMagicEntity> getOneByName(String name) {
        return _dao.getOneByName(name);
    }

    public LiveData<HighMagicEntity> getOneByID(Integer id) {
        return _dao.getOneByID(id);
    }

    public LiveData<List<NameEntity>> getAllHighMagicNamesOfType(HighMagicEntity.TypeEnum type) {
        return _dao.getLiveAllNamesOfType(type);
    }

    public LiveData<List<HighMagicType>> getAllTypes() {
        return _dao.getTypes();
    }
}
