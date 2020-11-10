package com.lazyfools.magusbuddy.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.BattlesituationDao;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.BattlesituationEntity;

import java.util.List;

public class BattlesituationRepository extends AbstractRepository<BattlesituationDao,BattlesituationEntity> {

    public BattlesituationRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _dao = db.battlesituationDao();
    }

    public LiveData<List<BattlesituationEntity>> getAll() {
        return _dao.getLiveAll();
    }

    public LiveData<List<NameEntity>> getAllNames() {
        return _dao.getAllNames();
    }

    public LiveData<BattlesituationEntity> getOneByName(String name) {
        return _dao.getOneByName(name);
    }

    public LiveData<BattlesituationEntity> getOneByID(Integer id) {
        return _dao.getOneByID(id);
    }
}
