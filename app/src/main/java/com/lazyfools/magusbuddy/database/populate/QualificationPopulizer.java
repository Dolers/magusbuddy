package com.lazyfools.magusbuddy.database.populate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.QualificationDao;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class QualificationPopulizer implements Populizer{
    private final QualificationDao _qualificationDao;
    private final Context _context;

    QualificationPopulizer(final Context context, AppDatabase db){
        _context = context;
        _qualificationDao = db.qualificationDao();
    }

    public void populate(){
        new DbPopulateAsync(_qualificationDao).execute();
    }

    private class DbPopulateAsync extends AsyncTask<Void, Void, Void> {
        private final QualificationDao _qualificationDao;

        DbPopulateAsync(QualificationDao QualificationDao) {
            _qualificationDao = QualificationDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _qualificationDao.deleteAll();
            _qualificationDao.insertAll(
                    parseQualificationJson(_context.getResources().openRawResource(R.raw.qualifications))
            );
            return null;
        }
    }

    private QualificationEntity[] parseQualificationJson(InputStream qualifications) {
        Scanner scanner = new Scanner(qualifications);
        String qualifications_text = scanner.useDelimiter("\\A").next();
        scanner.close();

        QualificationEntity[] qualificationEntities = null;
        try {
            JSONArray qualificationsJson = new JSONArray(qualifications_text);
            qualificationEntities = new QualificationEntity[qualificationsJson.length()];
            for (int i = 0; i< qualificationsJson.length();i++) {
                JSONObject qJson = (JSONObject)qualificationsJson.get(i);
                Log.i("AppDatabase", "képzettség: "+i+" név: "+qJson.getString("képzettség"));
                QualificationEntity entity = new QualificationEntity(
                        qJson.getString("képzettség"),
                        QualificationEntity.TypeEnum.valueOf(qJson.getString("tipus")),
                        qJson.getInt("nehézség"),
                        qJson.getBoolean("erő"),
                        qJson.getBoolean("gyorsasag"),
                        qJson.getBoolean("ügyesség"),
                        qJson.getBoolean("állóképesség"),
                        qJson.getBoolean("egészség"),
                        qJson.getBoolean("karizma"),
                        qJson.getBoolean("intelligencia"),
                        qJson.getBoolean("akaraterő"),
                        qJson.getBoolean("asztrál"),
                        qJson.getBoolean("érzékelés"),
                        qJson.getString("leírás")
                );

                parseDescriptionLevels(qJson, entity);
                parseDescriptionTables(qJson, entity);
                parseSpecialDescription(qJson, entity);

                qualificationEntities[i] = entity;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return qualificationEntities;
    }

    private static void parseSpecialDescription(JSONObject qJson, QualificationEntity entity) throws JSONException {
        if (qJson.has("specialis")){
            entity.setSpecialDesc(qJson.getString("specialis"));
        }
    }

    private static void parseDescriptionLevels(JSONObject qJson, QualificationEntity entity) throws JSONException {
        if (qJson.has("1_fok")){
            entity.setFirstLevelDesc(qJson.getString("1_fok"));
            entity.setSecondLevelDesc(qJson.getString("2_fok"));
            entity.setThirdLevelDesc(qJson.getString("3_fok"));
            entity.setFourthLevelDesc(qJson.getString("4_fok"));
            entity.setFifthLevelDesc(qJson.getString("5_fok"));
        }
    }

    private static void parseDescriptionTables(JSONObject qJson, QualificationEntity entity) throws JSONException {
        if (qJson.has("tablazatok")){
            ArrayList<String> listdata = new ArrayList<String>();
            JSONArray jArray = qJson.getJSONArray("tablazatok");
            for (int j=0;j<jArray.length();j++){
                listdata.add(jArray.getString(j));
            }
            entity.setDescriptionTables(listdata);
        }
    }
}
