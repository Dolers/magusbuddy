package com.lazyfools.magusbuddy.database.populate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.HighMagicDao;
import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.lazyfools.magusbuddy.utility.JSONUtility.parseDescriptionTables;
import static com.lazyfools.magusbuddy.utility.JSONUtility.parseStringWithDefault;

public class HighMagicPopulizer implements Populizer{
    private final HighMagicDao _dao;
    private final Context _context;

    HighMagicPopulizer(final Context context, AppDatabase db){
        _context = context;
        _dao = db.highMagicDao();
    }

    public void populate(){
        new DbPopulateAsync(_dao).execute();
    }

    private class DbPopulateAsync extends AsyncTask<Void, Void, Void> {
        private final HighMagicDao _dao;

        DbPopulateAsync(HighMagicDao QualificationDao) {
            _dao = QualificationDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _dao.deleteAll();
            _dao.insertAll(
                    parseJson(_context.getResources().openRawResource(R.raw.highmagic))
            );
            return null;
        }
    }

    private HighMagicEntity[] parseJson(InputStream text) {
        Scanner scanner = new Scanner(text);
        String qualifications_text = scanner.useDelimiter("\\A").next();
        scanner.close();

        HighMagicEntity[] entities = null;
        try {
            JSONArray qualificationsJson = new JSONArray(qualifications_text);
            entities = new HighMagicEntity[qualificationsJson.length()];
            for (int i = 0; i< qualificationsJson.length();i++) {
                JSONObject qJson = (JSONObject)qualificationsJson.get(i);
                Log.i("AppDatabase", "magasmágia: "+i+" név: "+qJson.getString("Varázslat neve"));
                entities[i] = new HighMagicEntity(
                        qJson.getString("Varázslat neve"),
                        HighMagicEntity.TypeEnum.enumOf(qJson.getString("Típus")),
                        qJson.getInt("MP"),
                        qJson.getInt("E MP"),
                        qJson.getString("Időtartam"),
                        qJson.getString("Hatótáv"),
                        qJson.getString("Varázslás ideje"),
                        qJson.getString("Leírás"),
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
