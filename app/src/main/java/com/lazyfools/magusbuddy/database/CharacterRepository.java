package com.lazyfools.magusbuddy.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.dao.APIKeyDao;
import com.lazyfools.magusbuddy.database.dao.CharacterAPIRelationDao;
import com.lazyfools.magusbuddy.database.dao.CharacterDao;
import com.lazyfools.magusbuddy.database.entity.APIKeyEntity;
import com.lazyfools.magusbuddy.database.entity.CharacterAPIRelationEntity;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;

import java.util.List;

public class CharacterRepository {
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
    public LiveData<List<APIKeyEntity>> getAllKeys() {
        return _allKeys;
    }
    public boolean hasCharacter() {return _allCharacter.getValue().isEmpty();}

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insertCharacter(int APIKeyId, int id, String name){
        CharacterEntity character = new CharacterEntity(id, name);
        insert(character);
        insertCharacterAPIRelation(character,APIKeyId);
    }
    private void insert(CharacterEntity character) {new CharacterAsyncTask(_characterDao, AbstractRepository.Operation.INSERT).execute(character);}
    private void insertCharacterAPIRelation(CharacterEntity character, int APIKeyId) {
        new CharacterAPIRelationAsyncTask(_characterAPIRelationDao, AbstractRepository.Operation.INSERT).execute(new CharacterAPIRelationEntity(character.getId(),APIKeyId));
    }

    public void insertAPIKey(String hash, boolean mine){ insert( new APIKeyEntity(hash,mine));}
    private void insert(APIKeyEntity apiKey) { new APIKeyAsyncTask(_aPIKeyDao, AbstractRepository.Operation.INSERT).execute(apiKey);}

    public void deleteAllCharacter(){new CharacterAsyncTask(_characterDao, AbstractRepository.Operation.DELETE_ALL).execute();}

    private static class CharacterAPIRelationAsyncTask extends AbstractRepository.RepositoryAsyncTask<CharacterAPIRelationDao,CharacterAPIRelationEntity> {
        CharacterAPIRelationAsyncTask(CharacterAPIRelationDao dao, AbstractRepository.Operation op) {
            super(dao, op);
        }
    }

    private static class CharacterAsyncTask extends AbstractRepository.RepositoryAsyncTask<CharacterDao,CharacterEntity> {
        CharacterAsyncTask(CharacterDao dao, AbstractRepository.Operation op) {
            super(dao, op);
        }
    }

    private static class APIKeyAsyncTask extends AbstractRepository.RepositoryAsyncTask<APIKeyDao,APIKeyEntity> {
        APIKeyAsyncTask(APIKeyDao dao, AbstractRepository.Operation op) {
            super(dao, op);
        }
    }
}
