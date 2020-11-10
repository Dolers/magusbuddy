package com.lazyfools.magusbuddy.database.populate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.BattlesituationDao;
import com.lazyfools.magusbuddy.database.entity.BattlesituationEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

import static com.lazyfools.magusbuddy.utility.JSONUtility.parseDescriptionTables;
import static com.lazyfools.magusbuddy.utility.JSONUtility.parseStringWithDefault;

public class BattlesituationPopulizer implements Populizer{
    private final BattlesituationDao _battlesituationDao;
    private final Context _context;

    BattlesituationPopulizer(final Context context, AppDatabase db){
        _context = context;
        _battlesituationDao = db.battlesituationDao();
    }

    public void populate(){
        new DbPopulateAsync(_battlesituationDao).execute();
    }

    private class DbPopulateAsync extends AsyncTask<Void, Void, Void> {
        private final BattlesituationDao _battlesituationDao;

        DbPopulateAsync(BattlesituationDao BattlesituationDao) {
            _battlesituationDao = BattlesituationDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _battlesituationDao.deleteAll();
            _battlesituationDao.insertAll(
                parseBattlesituationJson(_context.getResources().openRawResource(R.raw.battlesituations))
            );
            return null;
        }
    }

    private BattlesituationEntity[] parseBattlesituationJson(InputStream battlesituations) {
        Scanner scanner = new Scanner(battlesituations);
        String battlesituations_text = scanner.useDelimiter("\\A").next();
        scanner.close();

        BattlesituationEntity[] entities = null;
        try {
            JSONArray battlesituationsJson = new JSONArray(battlesituations_text);
            entities = new BattlesituationEntity[battlesituationsJson.length()];
            for (int i = 0; i< battlesituationsJson.length();i++) {
                JSONObject qJson = (JSONObject)battlesituationsJson.get(i);
                Log.i("AppDatabase", "harci helyzet: "+i+" név: "+qJson.getString("nev"));
                entities[i] = new BattlesituationEntity(
                        qJson.getString("nev"),
                        parseStringWithDefault(qJson,"ke"),
                        parseStringWithDefault(qJson,"te"),
                        parseStringWithDefault(qJson,"ve"),
                        parseStringWithDefault(qJson,"ce"),
                        qJson.getString("leiras")
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entities;
    }
}
