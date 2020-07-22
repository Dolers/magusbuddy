package com.lazyfools.magusbuddy.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.WitchMagicDao;
import com.lazyfools.magusbuddy.database.entity.WitchMagicEntity;
import com.lazyfools.magusbuddy.database.entity.WitchMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

public class WitchMagicRepository extends AbstractRepository<WitchMagicDao, WitchMagicEntity> {
    public WitchMagicRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _dao = db.witchMagicDao();
    }

    public LiveData<List<WitchMagicEntity>> getAll() {
        return _dao.getLiveAll();
    }

    public LiveData<List<NameEntity>> getAllNames() {
        return _dao.getAllNames();
    }

    public LiveData<WitchMagicEntity> getOneByName(String name) {
        return _dao.getOneByName(name);
    }

    public LiveData<WitchMagicEntity> getOneByID(Integer id) {
        return _dao.getOneByID(id);
    }

    public LiveData<List<NameEntity>> getAllWitchMagicNamesOfType(WitchMagicEntity.TypeEnum type) {
        return _dao.getLiveAllNamesOfType(type);
    }

    public LiveData<List<WitchMagicType>> getAllTypes() {
        return _dao.getTypes();
    }
}
