package com.lazyfools.magusbuddy.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lazyfools.magusbuddy.database.dao.APIKeyDao;
import com.lazyfools.magusbuddy.database.dao.CharacterAPIRelationDao;
import com.lazyfools.magusbuddy.database.dao.CharacterDao;
import com.lazyfools.magusbuddy.database.dao.CodexDao;
import com.lazyfools.magusbuddy.database.dao.HighMagicDao;
import com.lazyfools.magusbuddy.database.dao.QualificationDao;
import com.lazyfools.magusbuddy.database.entity.APIKeyEntity;
import com.lazyfools.magusbuddy.database.entity.CharacterAPIRelationEntity;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;
import com.lazyfools.magusbuddy.database.entity.CodexEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.populate.DbPopulizer;

@Database(
        entities = {
            CharacterEntity.class,
            APIKeyEntity.class,
            CharacterAPIRelationEntity.class,
            QualificationEntity.class,
            HighMagicEntity.class,
            CodexEntity.class
        },
        version = 6,
        exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
    public abstract APIKeyDao apiKeyDao();
    public abstract CharacterAPIRelationDao characterAPIKeyRelationDao();

    public abstract QualificationDao qualificationDao();
    public abstract HighMagicDao highMagicDao();
    public abstract CodexDao codexDao();


    private static AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "Dreonar_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(new RoomDatabase.Callback() {
                                     @Override
                                     public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                         super.onCreate(db);

                                         new DbPopulizer(context, INSTANCE).populate();
                                     }
                                 }
                            )
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
}