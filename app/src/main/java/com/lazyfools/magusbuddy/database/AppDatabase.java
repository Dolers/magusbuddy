package com.lazyfools.magusbuddy.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.dao.APIKeyDao;
import com.lazyfools.magusbuddy.database.dao.CharacterAPIRelationDao;
import com.lazyfools.magusbuddy.database.dao.CharacterDao;
import com.lazyfools.magusbuddy.database.dao.QualificationDao;
import com.lazyfools.magusbuddy.database.entity.APIKeyEntity;
import com.lazyfools.magusbuddy.database.entity.CharacterAPIRelationEntity;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

@Database(
        entities = {
            CharacterEntity.class,
            APIKeyEntity.class,
            CharacterAPIRelationEntity.class,
            QualificationEntity.class
        },
        version = 3,
        exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
    public abstract APIKeyDao apiKeyDao();
    public abstract CharacterAPIRelationDao characterAPIKeyRelationDao();

    public abstract QualificationDao qualificationDao();

    private static AppDatabase INSTANCE;
    private static Context context;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            AppDatabase.context = context;
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "Dreonar_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CharacterDao _characterDao;
        private final QualificationDao _qualificationDao;

        PopulateDbAsync(AppDatabase db) {
            _characterDao = db.characterDao();
            _qualificationDao = db.qualificationDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            _characterDao.deleteAll();
            _qualificationDao.deleteAll();

            _characterDao.insertAll(
                    new CharacterEntity(0,"Quince"),
                    new CharacterEntity(1,"Jurac"),
                    new CharacterEntity(2,"Sam")
            );

            _qualificationDao.insertAll(
                parseQualificationJson(context.getResources().openRawResource(R.raw.qualifications))
            );
            return null;
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
                            QualificationEntity.QualificationTypeEnum.valueOf(qJson.getString("tipus")),
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