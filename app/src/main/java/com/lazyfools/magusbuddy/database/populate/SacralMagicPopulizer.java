package com.lazyfools.magusbuddy.database.populate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.SacralMagicDao;
import com.lazyfools.magusbuddy.database.entity.PsziMagicEntity;
import com.lazyfools.magusbuddy.database.entity.SacralMagicEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.lazyfools.magusbuddy.utility.JSONUtility.parseDescriptionTables;
import static com.lazyfools.magusbuddy.utility.JSONUtility.parseStringWithDefault;

public class SacralMagicPopulizer implements Populizer{
    private final SacralMagicDao _dao;
    private final Context _context;

    SacralMagicPopulizer(final Context context, AppDatabase db){
        _context = context;
        _dao = db.sacralMagicDao();
    }

    public void populate(){
        new DbPopulateAsync(_dao).execute();
    }

    private class DbPopulateAsync extends AsyncTask<Void, Void, Void> {
        private final SacralMagicDao _dao;

        DbPopulateAsync(SacralMagicDao dao) {
            _dao = dao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _dao.deleteAll();
            _dao.insertAll(
                    parseJson(_context.getResources().openRawResource(R.raw.sacralmagic))
            );
            return null;
        }
    }

    private SacralMagicEntity[] parseJson(InputStream text) {
        Scanner scanner = new Scanner(text);
        String qualifications_text = scanner.useDelimiter("\\A").next();
        scanner.close();

        SacralMagicEntity[] entities = null;
        try {
            JSONArray qualificationsJson = new JSONArray(qualifications_text);
            entities = new SacralMagicEntity[qualificationsJson.length()];
            for (int i = 0; i< qualificationsJson.length();i++) {
                JSONObject qJson = (JSONObject)qualificationsJson.get(i);
                Log.i("AppDatabase", "szakrális magia: "+i+" név: "+qJson.getString("nev"));
                entities[i] = new SacralMagicEntity(
                        qJson.getString("nev"),
                        SacralMagicEntity.TypeEnum.enumOf(qJson.getString("tipus")),
                        qJson.getString("altipus"),
                        qJson.getInt("kegypont"),
                        qJson.getInt("erosites"),
                        parseStringWithDefault(qJson, "erosites szovege"),
                        parseStringWithDefault(qJson, "me"),
                        parseSphere(qJson),
                        qJson.getString("idotartam"),
                        qJson.getString("hatotav"),
                        qJson.getString("hossz"),
                        qJson.getString("leiras"),
                        parseDescriptionTables(qJson)
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entities;
    }

    private byte parseSphere(JSONObject qJson) {
        byte value = 0;
        if (qJson.has("elet")){
            value |= (1 << SacralMagicEntity.SphereEnum.ELET.ordinal());
        }
        if (qJson.has("halal")){
            value |= (1 << SacralMagicEntity.SphereEnum.HALAL.ordinal());
        }
        if (qJson.has("lelek")){
            value |= (1 << SacralMagicEntity.SphereEnum.LELEK.ordinal());
        }
        if (qJson.has("termeszet")){
            value |= (1 << SacralMagicEntity.SphereEnum.TERMESZET.ordinal());
        }
        return value;
    }
}
