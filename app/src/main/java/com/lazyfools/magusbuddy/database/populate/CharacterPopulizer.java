package com.lazyfools.magusbuddy.database.populate;

import android.os.AsyncTask;

import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.CharacterDao;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;

public class CharacterPopulizer implements Populizer{
    private final CharacterDao _characterDao;

    CharacterPopulizer(AppDatabase db){
        _characterDao = db.characterDao();
    }

    public void populate(){
        new DbPopulateAsync(_characterDao).execute();
    }

    private static class DbPopulateAsync extends AsyncTask<Void, Void, Void> {
        private final CharacterDao _characterDao;

        DbPopulateAsync(CharacterDao characterDao) {
            _characterDao = characterDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _characterDao.deleteAll();

            _characterDao.insertAll(
                    new CharacterEntity(0,"Quince"),
                    new CharacterEntity(1,"Jurac"),
                    new CharacterEntity(2,"Sam")
            );
            return null;
        }
    }
}
