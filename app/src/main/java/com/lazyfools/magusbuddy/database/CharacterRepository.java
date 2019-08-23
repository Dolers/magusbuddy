package com.lazyfools.magusbuddy.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.lazyfools.magusbuddy.database.dao.APIKeyDao;
import com.lazyfools.magusbuddy.database.dao.CharacterAPIRelationDao;
import com.lazyfools.magusbuddy.database.dao.CharacterDao;
import com.lazyfools.magusbuddy.database.entity.APIKeyEntity;
import com.lazyfools.magusbuddy.database.entity.CharacterAPIRelationEntity;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;

import java.util.List;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */

public class CharacterRepository {

    enum Operation {INSERT, DELETE, DELETE_ALL}
    private CharacterDao _characterDao;
    private LiveData<List<CharacterEntity>> _allCharacter;

    private APIKeyDao _aPIKeyDao;
    private LiveData<List<APIKeyEntity>> _allKeys;
    private CharacterAPIRelationDao _characterAPIRelationDao;

    // Note that in order to unit test the DatabaseRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public CharacterRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        _characterDao = db.characterDao();
        _allCharacter = _characterDao.getLiveAll();
        _aPIKeyDao = db.apiKeyDao();
        _allKeys = _aPIKeyDao.getLiveAll();
        _characterAPIRelationDao = db.characterAPIKeyRelationDao();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<CharacterEntity>> getAllCharacter() {
        return _allCharacter;
    }
    public boolean hasCharacter() {return _allCharacter.getValue().isEmpty();}
    public LiveData<List<APIKeyEntity>> getAllKeys() {
        return _allKeys;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insertCharacter(int APIKeyId, int id, String name){
        CharacterEntity character = new CharacterEntity(id, name);
        insert(character);
        insertCharacterAPIRelation(character,APIKeyId);
    }

    private void insertCharacterAPIRelation(CharacterEntity character, int APIKeyId) {
        new CharacterAPIRelationAsyncTask(_characterAPIRelationDao,Operation.INSERT).execute(new CharacterAPIRelationEntity(character.getId(),APIKeyId));
    }

    private static class CharacterAPIRelationAsyncTask extends AsyncTask<CharacterAPIRelationEntity, Void, Void> {

        private CharacterAPIRelationDao _asyncTaskDao;
        private Operation _op;

        CharacterAPIRelationAsyncTask(CharacterAPIRelationDao dao, CharacterRepository.Operation op) {
            _asyncTaskDao = dao;
            _op = op;
        }
        @Override
        protected Void doInBackground(final CharacterAPIRelationEntity... params) {
            switch (_op){
                case INSERT:
                    _asyncTaskDao.insert(params[0]);
                    break;
                case DELETE:
                    Log.i("CharacterAPIAsyncTask", "Unimplemented method delete");
                    //mAsyncTaskDao.delete(params[0]);
                    break;
                case DELETE_ALL:
                    Log.i("CharacterAPIAsyncTask", "Unimplemented method deleteAll");
                    //mAsyncTaskDao.deleteAll();
                    break;
            }
            return null;
        }
    }

    public void deleteAllCharacter(){new CharacterAsyncTask(_characterDao,Operation.DELETE_ALL).execute();}
    private void insert(CharacterEntity character) {new CharacterAsyncTask(_characterDao,Operation.INSERT).execute(character);}

    private static class CharacterAsyncTask extends AsyncTask<CharacterEntity, Void, Void> {

        private CharacterDao _asyncTaskDao;
        private Operation _op;

        CharacterAsyncTask(CharacterDao dao, CharacterRepository.Operation op) {
            _asyncTaskDao = dao;
            _op = op;
        }
        @Override
        protected Void doInBackground(final CharacterEntity... params) {
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

    public void insertAPIKey(String hash, boolean mine){ insert( new APIKeyEntity(hash,mine));}
    private void insert(APIKeyEntity apiKey) { new APIKeyAsyncTask(_aPIKeyDao,Operation.INSERT).execute(apiKey);}

    private static class APIKeyAsyncTask extends AsyncTask<APIKeyEntity, Void, Void> {
        private APIKeyDao _asyncTaskDao;
        private Operation _op;

        APIKeyAsyncTask(APIKeyDao dao,CharacterRepository.Operation op) {
            _asyncTaskDao = dao;
            _op = op;
        }

        @Override
        protected Void doInBackground(final APIKeyEntity... params) {
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
