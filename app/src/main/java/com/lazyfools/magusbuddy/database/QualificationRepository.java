package com.lazyfools.magusbuddy.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.lazyfools.magusbuddy.database.dao.QualificationDao;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.List;

public class QualificationRepository {
    enum Operation {INSERT, DELETE, DELETE_ALL}
    private QualificationDao mQualificationDao;

    public QualificationRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mQualificationDao = db.qualificationDao();
    }
    public LiveData<List<QualificationEntity>> getAllQualifications() {
        return mQualificationDao.getLiveAll();
    }
    public LiveData<List<QualificationEntity>> getAllQualificationsOfType(QualificationEntity.QualificationTypeEnum type) {
        return mQualificationDao.getLiveAllOfType(type);
    }
    public LiveData<List<QualificationType>> getAllTypes() {
        return mQualificationDao.getTypes();
    }

    public void deleteAllQualification(){
        new QualificationAsyncTask(mQualificationDao,Operation.DELETE_ALL).execute();
    }

    private void insert(QualificationEntity Qualification) {
        new QualificationAsyncTask(mQualificationDao,Operation.INSERT).execute(Qualification);
    }

    private static class QualificationAsyncTask extends AsyncTask<QualificationEntity, Void, Void> {

        private QualificationDao mAsyncTaskDao;
        private Operation mOp;

        QualificationAsyncTask(QualificationDao dao, QualificationRepository.Operation op) {
            mAsyncTaskDao = dao;
            mOp = op;
        }
        @Override
        protected Void doInBackground(final QualificationEntity... params) {
            switch (mOp){
                case INSERT:
                    mAsyncTaskDao.insert(params[0]);
                    break;
                case DELETE:
                    mAsyncTaskDao.delete(params[0]);
                    break;
                case DELETE_ALL:
                    mAsyncTaskDao.deleteAll();
                    break;
            }
            return null;
        }
    }
 
}
