package com.lazyfools.magusbuddy.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.dao.PsziMagicDao;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.PsziMagicEntity;
import com.lazyfools.magusbuddy.database.entity.PsziMagicType;

import java.util.List;

public class PsziMagicRepository extends AbstractRepository<PsziMagicDao, PsziMagicEntity> {
    public PsziMagicRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _dao = db.psziMagicDao();
    }

    public LiveData<List<PsziMagicEntity>> getAll() {
        return _dao.getLiveAll();
    }

    public LiveData<List<NameEntity>> getAllNames() {
        return _dao.getAllNames();
    }

    public LiveData<PsziMagicEntity> getOneByName(String name) {
        return _dao.getOneByName(name);
    }

    public LiveData<PsziMagicEntity> getOneByID(Integer id) {
        return _dao.getOneByID(id);
    }

    public LiveData<List<NameEntity>> getAllPsziMagicNamesOfType(PsziMagicEntity.TypeEnum type) {
        return _dao.getLiveAllNamesOfType(type);
    }

    public LiveData<List<PsziMagicType>> getAllTypes() {
        return _dao.getTypes();
    }
}
