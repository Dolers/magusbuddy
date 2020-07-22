package com.lazyfools.magusbuddy.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.WarlockMagicDao;
import com.lazyfools.magusbuddy.database.entity.WarlockMagicEntity;
import com.lazyfools.magusbuddy.database.entity.WarlockMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

public class WarlockMagicRepository extends AbstractRepository<WarlockMagicDao, WarlockMagicEntity> {
    public WarlockMagicRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _dao = db.warlockMagicDao();
    }

    public LiveData<List<WarlockMagicEntity>> getAll() {
        return _dao.getLiveAll();
    }

    public LiveData<List<NameEntity>> getAllNames() {
        return _dao.getAllNames();
    }

    public LiveData<WarlockMagicEntity> getOneByName(String name) {
        return _dao.getOneByName(name);
    }

    public LiveData<WarlockMagicEntity> getOneByID(Integer id) {
        return _dao.getOneByID(id);
    }

    public LiveData<List<NameEntity>> getAllWarlockMagicNamesOfType(WarlockMagicEntity.TypeEnum type) {
        return _dao.getLiveAllNamesOfType(type);
    }

    public LiveData<List<WarlockMagicType>> getAllTypes() {
        return _dao.getTypes();
    }
}
