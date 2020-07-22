package com.lazyfools.magusbuddy.database.populate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.PsziMagicDao;
import com.lazyfools.magusbuddy.database.entity.PsziMagicEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.lazyfools.magusbuddy.utility.JSONUtility.parseDescriptionTables;
import static com.lazyfools.magusbuddy.utility.JSONUtility.parseStringWithDefault;

public class PsziMagicPopulizer implements Populizer{
    private final PsziMagicDao _dao;
    private final Context _context;

    PsziMagicPopulizer(final Context context, AppDatabase db){
        _context = context;
        _dao = db.psziMagicDao();
    }

    public void populate(){
        new DbPopulateAsync(_dao).execute();
    }

    private class DbPopulateAsync extends AsyncTask<Void, Void, Void> {
        private final PsziMagicDao _dao;

        DbPopulateAsync(PsziMagicDao dao) {
            _dao = dao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _dao.deleteAll();
            _dao.insertAll(
                    parseJson(_context.getResources().openRawResource(R.raw.pszimagic))
            );
            return null;
        }
    }

    private PsziMagicEntity[] parseJson(InputStream text) {
        Scanner scanner = new Scanner(text);
        String qualifications_text = scanner.useDelimiter("\\A").next();
        scanner.close();

        PsziMagicEntity[] entities = null;
        try {
            JSONArray qualificationsJson = new JSONArray(qualifications_text);
            entities = new PsziMagicEntity[qualificationsJson.length()];
            for (int i = 0; i< qualificationsJson.length();i++) {
                JSONObject qJson = (JSONObject)qualificationsJson.get(i);
                Log.i("AppDatabase", "pszi diszciplina: "+i+" név: "+qJson.getString("nev"));
                entities[i] = new PsziMagicEntity(
                        qJson.getString("nev"),
                        PsziMagicEntity.TypeEnum.enumOf(qJson.getString("tipus")),
                        qJson.getString("fatipus"),
                        qJson.getInt("fok"),
                        qJson.getInt("pszipont"),
                        qJson.getInt("erosites"),
                        parseStringWithDefault(qJson,"erosites szovege"),
                        parseStringWithDefault(qJson,"ellenallas"),
                        qJson.getString("idotartam"),
                        qJson.getString("hossz"),
                        qJson.getString("leiras"),
                        parseDescriptionTables(qJson),
                        parseStringWithDefault(qJson,"specialis")
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entities;
    }

}
