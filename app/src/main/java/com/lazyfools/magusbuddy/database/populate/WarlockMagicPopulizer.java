package com.lazyfools.magusbuddy.database.populate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.WarlockMagicDao;
import com.lazyfools.magusbuddy.database.entity.WarlockMagicEntity;
import com.lazyfools.magusbuddy.utility.JSONUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.lazyfools.magusbuddy.utility.JSONUtility.parseIntegerWithDefault;
import static com.lazyfools.magusbuddy.utility.JSONUtility.parseStringWithDefault;

public class WarlockMagicPopulizer implements Populizer{
    private final WarlockMagicDao _dao;
    private final Context _context;

    WarlockMagicPopulizer(final Context context, AppDatabase db){
        _context = context;
        _dao = db.warlockMagicDao();
    }

    public void populate(){
        new DbPopulateAsync(_dao).execute();
    }

    private class DbPopulateAsync extends AsyncTask<Void, Void, Void> {
        private final WarlockMagicDao _dao;

        DbPopulateAsync(WarlockMagicDao QualificationDao) {
            _dao = QualificationDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _dao.deleteAll();
            _dao.insertAll(
                    parseJson(_context.getResources().openRawResource(R.raw.warlockmagic))
            );
            return null;
        }
    }

    private WarlockMagicEntity[] parseJson(InputStream text) {
        Scanner scanner = new Scanner(text);
        String qualifications_text = scanner.useDelimiter("\\A").next();
        scanner.close();

        WarlockMagicEntity[] entities = null;
        try {
            JSONArray qualificationsJson = new JSONArray(qualifications_text);
            entities = new WarlockMagicEntity[qualificationsJson.length()];
            for (int i = 0; i< qualificationsJson.length();i++) {
                JSONObject qJson = (JSONObject)qualificationsJson.get(i);
                Log.i("AppDatabase", "boszorkánymestermágia: "+i+" név: "+qJson.getString("nev"));
                entities[i] = new WarlockMagicEntity(
                        qJson.getString("nev"),
                        WarlockMagicEntity.TypeEnum.enumOf(qJson.getString("tipus")),
                        WarlockMagicEntity.SubTypeEnum.enumOf(parseStringWithDefault(qJson,"altipus")),
                        qJson.getInt("mp"),
                        qJson.getInt("emp"),
                        parseIntegerWithDefault(qJson, "alape",1),
                        parseStringWithDefault(qJson, "me"),
                        parseStringWithDefault(qJson,"idotartam"),
                        parseStringWithDefault(qJson,"hatotav"),
                        parseStringWithDefault(qJson,"idoigeny"),
                        qJson.getString("leiras"),
                        JSONUtility.parseDescriptionTables(qJson),
                        parseStringWithDefault(qJson, "specialis")
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entities;
    }
}
