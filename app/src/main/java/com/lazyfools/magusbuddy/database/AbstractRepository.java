package com.lazyfools.magusbuddy.database;

import android.os.AsyncTask;
import com.lazyfools.magusbuddy.database.dao.IBaseDao;


public abstract class AbstractRepository<Dao extends IBaseDao<Entity>,Entity> {
    enum Operation {INSERT, INSERT_ALL, DELETE, DELETE_ALL}
    protected Dao _dao;

    public void insert(Entity entity) {
        new RepositoryAsyncTask(_dao,Operation.INSERT).execute(entity);
    }

    public void insertAll(Entity... entity) {
        new RepositoryAsyncTask(_dao,Operation.INSERT_ALL).execute(entity);
    }

    public void delete(Entity entity){
        new RepositoryAsyncTask(_dao,Operation.DELETE).execute(entity);
    }

    public void deleteAll(Entity... entity){
        new RepositoryAsyncTask(_dao,Operation.DELETE_ALL).execute(entity);
    }

    public static class RepositoryAsyncTask<Dao extends IBaseDao<Entity>,Entity> extends AsyncTask<Entity, Void, Void> {

        private Dao _asyncTaskDao;
        private Operation _op;

        RepositoryAsyncTask(Dao dao, Operation op) {
            _asyncTaskDao = dao;
            _op = op;
        }

        @Override
        protected Void doInBackground(final Entity... params) {
            switch (_op){
                case INSERT:
                    _asyncTaskDao.insert(params[0]);
                    break;
                case INSERT_ALL:
                    _asyncTaskDao.insertAll(params);
                    break;
                case DELETE:
                    _asyncTaskDao.delete(params[0]);
                    break;
                case DELETE_ALL:
                    _asyncTaskDao.deleteAll(params);
                    break;
            }
            return null;
        }
    }
}
