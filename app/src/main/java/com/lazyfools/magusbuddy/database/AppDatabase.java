package com.lazyfools.magusbuddy.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import com.lazyfools.magusbuddy.database.dao.APIKeyDao;
import com.lazyfools.magusbuddy.database.dao.BardMagicDao;
import com.lazyfools.magusbuddy.database.dao.BattlesituationDao;
import com.lazyfools.magusbuddy.database.dao.CharacterAPIRelationDao;
import com.lazyfools.magusbuddy.database.dao.CharacterDao;
import com.lazyfools.magusbuddy.database.dao.CodexDao;
import com.lazyfools.magusbuddy.database.dao.FireMagicDao;
import com.lazyfools.magusbuddy.database.dao.HighMagicDao;
import com.lazyfools.magusbuddy.database.dao.PsziMagicDao;
import com.lazyfools.magusbuddy.database.dao.QualificationDao;
import com.lazyfools.magusbuddy.database.dao.SacralMagicDao;
import com.lazyfools.magusbuddy.database.dao.WarlockMagicDao;
import com.lazyfools.magusbuddy.database.dao.WitchMagicDao;
import com.lazyfools.magusbuddy.database.entity.APIKeyEntity;
import com.lazyfools.magusbuddy.database.entity.BardMagicEntity;
import com.lazyfools.magusbuddy.database.entity.BattlesituationEntity;
import com.lazyfools.magusbuddy.database.entity.CharacterAPIRelationEntity;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;
import com.lazyfools.magusbuddy.database.entity.CodexEntity;
import com.lazyfools.magusbuddy.database.entity.FireMagicEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;
import com.lazyfools.magusbuddy.database.entity.PsziMagicEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.SacralMagicEntity;
import com.lazyfools.magusbuddy.database.entity.WarlockMagicEntity;
import com.lazyfools.magusbuddy.database.entity.WitchMagicEntity;
import com.lazyfools.magusbuddy.database.populate.DbPopulizer;

@Database(
        entities = {
            CharacterEntity.class,
            APIKeyEntity.class,
            CharacterAPIRelationEntity.class,
            QualificationEntity.class,
            BattlesituationEntity.class,
            CodexEntity.class,
            HighMagicEntity.class,
            SacralMagicEntity.class,
            BardMagicEntity.class,
            PsziMagicEntity.class,
            FireMagicEntity.class,
            WitchMagicEntity.class,
            WarlockMagicEntity.class,
        },
        version = 14,
        exportSchema = false
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
    public abstract APIKeyDao apiKeyDao();
    public abstract CharacterAPIRelationDao characterAPIKeyRelationDao();

    public abstract QualificationDao qualificationDao();
    public abstract BattlesituationDao battlesituationDao();
    public abstract HighMagicDao highMagicDao();
    public abstract SacralMagicDao sacralMagicDao();
    public abstract BardMagicDao bardMagicDao();
    public abstract PsziMagicDao psziMagicDao();
    public abstract FireMagicDao fireMagicDao();
    public abstract WitchMagicDao witchMagicDao();
    public abstract WarlockMagicDao warlockMagicDao();

    public abstract CodexDao codexDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "Database")
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
}