package com.lazyfools.magusbuddy.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.dao.FireMagicDao;
import com.lazyfools.magusbuddy.database.entity.FireMagicEntity;
import com.lazyfools.magusbuddy.database.entity.FireMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

public class FireMagicRepository extends AbstractRepository<FireMagicDao, FireMagicEntity> {
    public FireMagicRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _dao = db.fireMagicDao();
    }

    public LiveData<List<FireMagicEntity>> getAll() {
        return _dao.getLiveAll();
    }

    public LiveData<List<NameEntity>> getAllNames() {
        return _dao.getAllNames();
    }

    public LiveData<FireMagicEntity> getOneByName(String name) {
        return _dao.getOneByName(name);
    }

    public LiveData<FireMagicEntity> getOneByID(Integer id) {
        return _dao.getOneByID(id);
    }

    public LiveData<List<NameEntity>> getAllFireMagicNamesOfType(FireMagicEntity.TypeEnum type) {
        return _dao.getLiveAllNamesOfType(type);
    }

    public LiveData<List<FireMagicType>> getAllTypes() {
        return _dao.getTypes();
    }
}
