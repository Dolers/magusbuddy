package com.lazyfools.magusbuddy.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.dao.QualificationDao;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationName;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.List;

public class QualificationRepository extends AbstractRepository<QualificationDao,QualificationEntity> {

    public QualificationRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _dao = db.qualificationDao();
    }

    public LiveData<List<QualificationEntity>> getAll() {
        return _dao.getLiveAll();
    }

    public LiveData<List<QualificationName>> getAllNames() {
        return _dao.getAllNames();
    }

    public LiveData<QualificationEntity> getOneByName(String name) {
        return _dao.getOneByName(name);
    }

    public LiveData<QualificationEntity> getOneByID(Integer id) {
        return _dao.getOneByID(id);
    }

    public LiveData<List<QualificationName>> getNamesOfFilter(String name) {
        return _dao.getNamesByFilter(name);
    }

    public LiveData<List<QualificationName>> getAllQualificationNamesOfType(QualificationEntity.TypeEnum type) {
        return _dao.getLiveAllNamesOfType(type);
    }

    public LiveData<List<QualificationType>> getAllTypes() {
        return _dao.getTypes();
    }
}
