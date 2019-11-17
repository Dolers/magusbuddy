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
                HighMagicEntity entity = new HighMagicEntity(
                        qJson.getString("Varázslat neve"),
                        stringToHighMagicTypeEnum(qJson.getString("Típus")),
                        qJson.getInt("MP"),
                        qJson.getInt("E MP"),
                        qJson.getString("Időtartam"),
                        qJson.getString("Hatótáv"),
                        qJson.getString("Varázslás ideje"),
                        qJson.getString("Leírás")
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

    private void parseSpecialDescription(JSONObject qJson, HighMagicEntity entity) throws JSONException {
        if (qJson.has("specialis")){
            entity.setSpecial(qJson.getString("specialis"));
        }
    }

    private void parseDescriptionTables(JSONObject qJson, HighMagicEntity entity) throws JSONException {
        if (qJson.has("tablazatok")){
            ArrayList<String> listdata = new ArrayList<String>();
            JSONArray jArray = qJson.getJSONArray("tablazatok");
            for (int j=0;j<jArray.length();j++){
                listdata.add(jArray.getString(j));
            }
            entity.setDescTables(listdata);
        }
    }

    private HighMagicEntity.TypeEnum stringToHighMagicTypeEnum(String value){
        switch(value){
            case "Elemi mágia": return HighMagicEntity.TypeEnum.ELEMI;
            case "Természeti Anyagok Mágiája": return HighMagicEntity.TypeEnum.TERMÉSZETI;
            case "Térmágia": return HighMagicEntity.TypeEnum.TER;
            case "Asztrálmágia": return HighMagicEntity.TypeEnum.ASZTRAL;
            case "Mentálmágia": return HighMagicEntity.TypeEnum.MENTAL;
            case "Rúnamágia": return HighMagicEntity.TypeEnum.RUNA;
            case "Időmágia": return HighMagicEntity.TypeEnum.IDO;
            case "Nekromancia": return HighMagicEntity.TypeEnum.NEKROMANCIA;
            case "Démonológia": return HighMagicEntity.TypeEnum.DEMONOLOGIA;
            case "Szimpatikus mágia": return HighMagicEntity.TypeEnum.SZIMPATIKUS;
            case "Egyéb mágikus módszerek": return HighMagicEntity.TypeEnum.EGYEB;
        }
        return HighMagicEntity.TypeEnum.EGYEB;
    }
}
