package com.lazyfools.magusbuddy.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.BardMagicDao;
import com.lazyfools.magusbuddy.database.entity.BardMagicEntity;
import com.lazyfools.magusbuddy.database.entity.BardMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

public class BardMagicRepository extends AbstractRepository<BardMagicDao, BardMagicEntity> {
    public BardMagicRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _dao = db.bardMagicDao();
    }

    public LiveData<List<BardMagicEntity>> getAll() {
        return _dao.getLiveAll();
    }

    public LiveData<List<NameEntity>> getAllNames() {
        return _dao.getAllNames();
    }

    public LiveData<BardMagicEntity> getOneByName(String name) {
        return _dao.getOneByName(name);
    }

    public LiveData<BardMagicEntity> getOneByID(Integer id) {
        return _dao.getOneByID(id);
    }

    public LiveData<List<NameEntity>> getAllBardMagicNamesOfType(BardMagicEntity.TypeEnum type) {
        return _dao.getLiveAllNamesOfType(type);
    }

    public LiveData<List<BardMagicType>> getAllTypes() {
        return _dao.getTypes();
    }

}
