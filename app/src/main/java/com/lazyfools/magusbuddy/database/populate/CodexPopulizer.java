package com.lazyfools.magusbuddy.database.populate;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.AppDatabase;
import com.lazyfools.magusbuddy.database.dao.CodexDao;
import com.lazyfools.magusbuddy.database.entity.CodexEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static com.lazyfools.magusbuddy.database.Converters.CodexTableToInt;

public class CodexPopulizer implements Populizer{
    private final CodexDao _dao;
    private final Context _context;

    CodexPopulizer(final Context context, AppDatabase db){
        _context = context;
        _dao = db.codexDao();
    }

    public void populate(){
        new DbPopulateAsync(_dao).execute();
    }

    private class DbPopulateAsync extends AsyncTask<Void, Void, Void> {
        private final CodexDao _dao;

        DbPopulateAsync(CodexDao QualificationDao) {
            _dao = QualificationDao;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _dao.deleteAll();
            _dao.insertAll(
                    parseJson(_context.getResources().openRawResource(R.raw.codex))
            );
            return null;
        }
    }

    private CodexEntity[] parseJson(InputStream text) {
        Scanner scanner = new Scanner(text);
        String qualifications_text = scanner.useDelimiter("\\A").next();
        scanner.close();

        CodexEntity[] entities = null;
        try {
            JSONArray allEntityJson = new JSONArray(qualifications_text);
            entities = new CodexEntity[allEntityJson.length()];
            for (int i = 0; i< allEntityJson.length();i++) {
                JSONObject entityJson = (JSONObject)allEntityJson.get(i);
                Log.i("AppDatabase", "codex: "+i+" név: "+entityJson.getString("nev"));
                CodexEntity entity = new CodexEntity(
                        entityJson.getString("nev"),
                        CodexTableToInt(entityJson.getString("tablanev")),
                        entityJson.getString("kepnev")
                );

                entities[i] = entity;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return entities;
    }
}
