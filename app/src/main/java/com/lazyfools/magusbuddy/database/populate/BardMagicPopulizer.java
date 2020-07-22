package com.lazyfools.magusbuddy.database.populate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.BardMagicDao;
import com.lazyfools.magusbuddy.database.entity.BardMagicEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.lazyfools.magusbuddy.database.Converters.BardMagicTypeToInt;
import static com.lazyfools.magusbuddy.utility.JSONUtility.parseStringWithDefault;

public class BardMagicPopulizer implements Populizer{
    private final BardMagicDao _dao;
    private final Context _context;

    BardMagicPopulizer(final Context context, AppDatabase db){
        _context = context;
        _dao = db.bardMagicDao();
    }

    public void populate(){
        new DbPopulateAsync(_dao).execute();
    }

    private class DbPopulateAsync extends AsyncTask<Void, Void, Void> {
        private final BardMagicDao _dao;

        DbPopulateAsync(BardMagicDao QualificationDao) {
            _dao = QualificationDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _dao.deleteAll();
            _dao.insertAll(
                    parseJson(_context.getResources().openRawResource(R.raw.bardmagic))
            );
            return null;
        }
    }

    private BardMagicEntity[] parseJson(InputStream text) {
        Scanner scanner = new Scanner(text);
        String qualifications_text = scanner.useDelimiter("\\A").next();
        scanner.close();

        BardMagicEntity[] entities = null;
        try {
            JSONArray qualificationsJson = new JSONArray(qualifications_text);
            entities = new BardMagicEntity[qualificationsJson.length()];
            for (int i = 0; i< qualificationsJson.length();i++) {
                JSONObject qJson = (JSONObject)qualificationsJson.get(i);
                Log.i("AppDatabase", "bárdmágia: "+i+" név: "+qJson.getString("nev"));
                entities[i] = new BardMagicEntity(
                        qJson.getString("nev"),
                        BardMagicEntity.TypeEnum.enumOf(qJson.getString("tipus")),
                        qJson.getInt("mp"),
                        qJson.getInt("emp"),
                        parseStringWithDefault(qJson, "me"),
                        qJson.getString("idotartam"),
                        qJson.getString("hatotav"),
                        qJson.getString("idoigeny"),
                        qJson.getString("leiras")
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entities;
    }
}
