package com.lazyfools.magusbuddy.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.dao.SacralMagicDao;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.SacralMagicEntity;
import com.lazyfools.magusbuddy.database.entity.SacralMagicType;

import java.util.List;

public class SacralMagicRepository extends AbstractRepository<SacralMagicDao, SacralMagicEntity> {
    public SacralMagicRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _dao = db.sacralMagicDao();
    }

    public LiveData<List<SacralMagicEntity>> getAll() {
        return _dao.getLiveAll();
    }

    public LiveData<List<NameEntity>> getAllNames() {
        return _dao.getAllNames();
    }

    public LiveData<SacralMagicEntity> getOneByName(String name) {
        return _dao.getOneByName(name);
    }

    public LiveData<SacralMagicEntity> getOneByID(Integer id) {
        return _dao.getOneByID(id);
    }

    public LiveData<List<NameEntity>> getNamesOfFilter(String name) {
        return _dao.getNamesByFilter(name);
    }

    public LiveData<List<NameEntity>> getAllSacralMagicNamesOfType(SacralMagicEntity.TypeEnum type) {
        return _dao.getLiveAllNamesOfType(type);
    }

    public LiveData<List<SacralMagicType>> getAllTypes() {
        return _dao.getTypes();
    }
}
