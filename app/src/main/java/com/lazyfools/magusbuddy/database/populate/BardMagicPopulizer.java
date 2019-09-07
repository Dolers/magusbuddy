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
                BardMagicEntity entity = new BardMagicEntity(
                        qJson.getString("nev"),
                        stringToBardMagicTypeEnum(qJson.getString("tipus")),
                        qJson.getInt("mp"),
                        qJson.getInt("emp"),
                        qJson.getString("idotartam"),
                        qJson.getString("hatotav"),
                        qJson.getString("idoigeny"),
                        qJson.getString("leiras")
                );

                parseMagicResistance(qJson, entity);

                entities[i] = entity;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entities;
    }

    private void parseMagicResistance(JSONObject qJson, BardMagicEntity entity) throws JSONException {
        if (qJson.has("me")){
            entity.setMagicResistance(qJson.getString("me"));
        }
    }

    private BardMagicEntity.TypeEnum stringToBardMagicTypeEnum(String value){
        switch(value){
            case "Dalmágia": return BardMagicEntity.TypeEnum.DAL;
            case "Fénymágia": return BardMagicEntity.TypeEnum.FENY;
            case "Hangmágia": return BardMagicEntity.TypeEnum.HANG;
            case "Egyéb bárdmágia": return BardMagicEntity.TypeEnum.EGYEB;
        }
        return BardMagicEntity.TypeEnum.EGYEB;
    }
}
