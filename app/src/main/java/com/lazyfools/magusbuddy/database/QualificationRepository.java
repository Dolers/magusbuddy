package com.lazyfools.magusbuddy.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.lazyfools.magusbuddy.database.dao.QualificationDao;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationName;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.List;

public class QualificationRepository {
    enum Operation {INSERT, DELETE, DELETE_ALL}
    private QualificationDao _qualificationDao;

    public QualificationRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _qualificationDao = db.qualificationDao();
    }
    public LiveData<List<QualificationEntity>> getAllQualifications() {
        return _qualificationDao.getLiveAll();
    }

    public LiveData<List<QualificationName>> getAllNames() {
        return _qualificationDao.getAllNames();
    }

    public LiveData<QualificationEntity> getOneByName(String name) {
        return _qualificationDao.getOneByName(name);
    }

    public LiveData<QualificationEntity> getOneByID(Integer id) {
        return _qualificationDao.getOneByID(id);
    }

    public LiveData<List<QualificationName>> getNamesOfFilter(String name) {
        return _qualificationDao.getNamesByFilter(name);
    }

    public LiveData<List<QualificationName>> getAllQualificationNamesOfType(QualificationEntity.QualificationTypeEnum type) {
        return _qualificationDao.getLiveAllNamesOfType(type);
    }

    public LiveData<List<QualificationType>> getAllTypes() {
        return _qualificationDao.getTypes();
    }

    public void deleteAllQualification(){
        new QualificationAsyncTask(_qualificationDao,Operation.DELETE_ALL).execute();
    }

    private void insert(QualificationEntity Qualification) {
        new QualificationAsyncTask(_qualificationDao,Operation.INSERT).execute(Qualification);
    }

    private static class QualificationAsyncTask extends AsyncTask<QualificationEntity, Void, Void> {

        private QualificationDao _asyncTaskDao;
        private Operation _op;

        QualificationAsyncTask(QualificationDao dao, QualificationRepository.Operation op) {
            _asyncTaskDao = dao;
            _op = op;
        }
        @Override
        protected Void doInBackground(final QualificationEntity... params) {
            switch (_op){
                case INSERT:
                    _asyncTaskDao.insert(params[0]);
                    break;
                case DELETE:
                    _asyncTaskDao.delete(params[0]);
                    break;
                case DELETE_ALL:
                    _asyncTaskDao.deleteAll();
                    break;
            }
            return null;
        }
    }
 
}
