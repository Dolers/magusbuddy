package com.lazyfools.magusbuddy.database.populate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.WitchMagicDao;
import com.lazyfools.magusbuddy.database.entity.WitchMagicEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.lazyfools.magusbuddy.utility.JSONUtility.parseDescriptionTables;
import static com.lazyfools.magusbuddy.utility.JSONUtility.parseStringWithDefault;

public class WitchMagicPopulizer implements Populizer{
    private final WitchMagicDao _dao;
    private final Context _context;

    WitchMagicPopulizer(final Context context, AppDatabase db){
        _context = context;
        _dao = db.witchMagicDao();
    }

    public void populate(){
        new DbPopulateAsync(_dao).execute();
    }

    private class DbPopulateAsync extends AsyncTask<Void, Void, Void> {
        private final WitchMagicDao _dao;

        DbPopulateAsync(WitchMagicDao QualificationDao) {
            _dao = QualificationDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _dao.deleteAll();
            _dao.insertAll(
                    parseJson(_context.getResources().openRawResource(R.raw.witchmagic))
            );
            return null;
        }
    }

    private WitchMagicEntity[] parseJson(InputStream text) {
        Scanner scanner = new Scanner(text);
        String qualifications_text = scanner.useDelimiter("\\A").next();
        scanner.close();

        WitchMagicEntity[] entities = null;
        try {
            JSONArray qualificationsJson = new JSONArray(qualifications_text);
            entities = new WitchMagicEntity[qualificationsJson.length()];
            for (int i = 0; i< qualificationsJson.length();i++) {
                JSONObject qJson = (JSONObject)qualificationsJson.get(i);
                Log.i("AppDatabase", "boszorkanymágia: "+i+" név: "+qJson.getString("nev"));
                entities[i] = new WitchMagicEntity(
                        qJson.getString("nev"),
                        WitchMagicEntity.TypeEnum.enumOf(qJson.getString("tipus")),
                        qJson.getInt("mp"),
                        qJson.getInt("emp"),
                        parseStringWithDefault(qJson,"idotartam"),
                        parseStringWithDefault(qJson,"hatotav"),
                        parseStringWithDefault(qJson,"idoigeny"),
                        qJson.getString("leiras"),
                        parseDescriptionTables(qJson),
                        parseStringWithDefault(qJson, "specialis")
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entities;
    }
}
