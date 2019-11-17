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
                PsziMagicEntity entity = new PsziMagicEntity(
                        qJson.getString("nev"),
                        stringToPsziMagicTypeEnum(qJson.getString("tipus")),
                        qJson.getString("fatipus"),
                        qJson.getInt("fok"),
                        qJson.getInt("pszipont"),
                        qJson.getInt("erosites"),
                        qJson.getString("idotartam"),
                        qJson.getString("hossz"),
                        qJson.getString("leiras")
                );

                parseMagicResistance(qJson, entity);
                parseDescriptionTables(qJson, entity);
                parseSpecialDescription(qJson, entity);
                parseStrengtheningText(qJson, entity);

                entities[i] = entity;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entities;
    }

    private void parseMagicResistance(JSONObject qJson, PsziMagicEntity entity) throws JSONException  {
        if(qJson.has("ellenallas")){
            entity.setMagicResistance(qJson.getString("ellenallas"));
        }
    }

    private void parseDescriptionTables(JSONObject qJson, PsziMagicEntity entity) throws JSONException {
        if (qJson.has("tablazatok")){
            ArrayList<String> listdata = new ArrayList<>();
            JSONArray jArray = qJson.getJSONArray("tablazatok");
            for (int j=0;j<jArray.length();j++){
                listdata.add(jArray.getString(j));
            }
            entity.setDescTables(listdata);
        }
    }

    private void parseSpecialDescription(JSONObject qJson, PsziMagicEntity entity) throws JSONException {
        if (qJson.has("specialis")){
            entity.setSpecial(qJson.getString("specialis"));
        }
    }

    private void parseStrengtheningText(JSONObject qJson, PsziMagicEntity entity) throws JSONException {
        if (qJson.has("erosites szovege")){
            entity.setEmpText(qJson.getString("erosites szovege"));
        }
    }

    private PsziMagicEntity.TypeEnum stringToPsziMagicTypeEnum(String value){
        switch(value){
            case "Pyarroni módszer": return PsziMagicEntity.TypeEnum.PYARRONI;
            case "Godoni örökség": return PsziMagicEntity.TypeEnum.GODONI;
            case "Kyr Metódus": return PsziMagicEntity.TypeEnum.KYR;
            case "Slan útja": return PsziMagicEntity.TypeEnum.SLAN;
        }
        return PsziMagicEntity.TypeEnum.PYARRONI;
    }
}
