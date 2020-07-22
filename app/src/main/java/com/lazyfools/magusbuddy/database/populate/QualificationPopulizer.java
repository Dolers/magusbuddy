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

import static com.lazyfools.magusbuddy.utility.JSONUtility.parseDescriptionTables;
import static com.lazyfools.magusbuddy.utility.JSONUtility.parseStringWithDefault;

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

        QualificationEntity[] entities = null;
        try {
            JSONArray qualificationsJson = new JSONArray(qualifications_text);
            entities = new QualificationEntity[qualificationsJson.length()];
            for (int i = 0; i< qualificationsJson.length();i++) {
                JSONObject qJson = (JSONObject)qualificationsJson.get(i);
                Log.i("AppDatabase", "képzettség: "+i+" név: "+qJson.getString("nev"));
                entities[i] = new QualificationEntity(
                        qJson.getString("nev"),
                        QualificationEntity.TypeEnum.valueOf(qJson.getString("tipus").toUpperCase()),
                        qJson.getInt("nehezseg"),
                        qJson.getBoolean("ero"),
                        qJson.getBoolean("gyorsasag"),
                        qJson.getBoolean("ugyesseg"),
                        qJson.getBoolean("allokepesseg"),
                        qJson.getBoolean("egeszseg"),
                        qJson.getBoolean("karizma"),
                        qJson.getBoolean("intelligencia"),
                        qJson.getBoolean("akaratero"),
                        qJson.getBoolean("asztral"),
                        qJson.getBoolean("erzekeles"),
                        qJson.getString("leiras"),
                        parseStringWithDefault(qJson,"1_fok"),
                        parseStringWithDefault(qJson,"2_fok"),
                        parseStringWithDefault(qJson,"3_fok"),
                        parseStringWithDefault(qJson,"4_fok"),
                        parseStringWithDefault(qJson,"5_fok"),
                        parseStringWithDefault(qJson,"specialis"),
                        parseDescriptionTables(qJson)
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entities;
    }
}
