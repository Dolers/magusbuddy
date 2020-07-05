package com.lazyfools.magusbuddy.database.populate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.FireMagicDao;
import com.lazyfools.magusbuddy.database.entity.FireMagicEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FireMagicPopulizer implements Populizer{
    private final FireMagicDao _dao;
    private final Context _context;

    FireMagicPopulizer(final Context context, AppDatabase db){
        _context = context;
        _dao = db.fireMagicDao();
    }

    public void populate(){
        new DbPopulateAsync(_dao).execute();
    }

    private class DbPopulateAsync extends AsyncTask<Void, Void, Void> {
        private final FireMagicDao _dao;

        DbPopulateAsync(FireMagicDao QualificationDao) {
            _dao = QualificationDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _dao.deleteAll();
            _dao.insertAll(
                    parseJson(_context.getResources().openRawResource(R.raw.firemagic))
            );
            return null;
        }
    }

    private FireMagicEntity[] parseJson(InputStream text) {
        Scanner scanner = new Scanner(text);
        String qualifications_text = scanner.useDelimiter("\\A").next();
        scanner.close();

        FireMagicEntity[] entities = null;
        try {
            JSONArray qualificationsJson = new JSONArray(qualifications_text);
            entities = new FireMagicEntity[qualificationsJson.length()];
            for (int i = 0; i< qualificationsJson.length();i++) {
                JSONObject qJson = (JSONObject)qualificationsJson.get(i);
                Log.i("AppDatabase", "Tűzvarázsló: "+i+" név: "+qJson.getString("nev"));
                FireMagicEntity entity = new FireMagicEntity(
                        qJson.getString("nev"),
                        FireMagicEntity.TypeEnum.enumOf(qJson.getString("tipus")),
                        qJson.getInt("mp"),
                        qJson.getString("idotartam"),
                        qJson.getString("idoigeny"),
                        qJson.getString("leiras")
                );

                parseDescriptionTables(qJson, entity);
                parseSpecialDescription(qJson, entity);

                entities[i] = entity;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entities;
    }

    private void parseSpecialDescription(JSONObject qJson, FireMagicEntity entity) throws JSONException {
        if (qJson.has("specialis")){
            entity.setSpecial(qJson.getString("specialis"));
        }
    }

    private void parseDescriptionTables(JSONObject qJson, FireMagicEntity entity) throws JSONException {
        if (qJson.has("tablazatok")){
            ArrayList<String> listdata = new ArrayList<String>();
            JSONArray jArray = qJson.getJSONArray("tablazatok");
            for (int j=0;j<jArray.length();j++){
                listdata.add(jArray.getString(j));
            }
            entity.setDescTables(listdata);
        }
    }
}
