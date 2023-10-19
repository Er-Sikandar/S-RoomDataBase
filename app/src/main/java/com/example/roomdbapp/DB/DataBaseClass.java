package com.example.roomdbapp.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomdbapp.DB.Dao.MainDao;
import com.example.roomdbapp.DB.Entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 2)

public abstract class DataBaseClass extends RoomDatabase {
    public abstract MainDao getUserDao();

    private static DataBaseClass instance;

    public static DataBaseClass getDataBase(final Context context) {
        if (instance == null) {
            synchronized (DataBaseClass.class) {
                instance = Room.databaseBuilder(context, DataBaseClass.class, "DATABASE")
                        .allowMainThreadQueries()
                        .addMigrations(MIGRATION_1_2)
                        // .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return instance;
    }

    static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull final SupportSQLiteDatabase database) {
            database.execSQL("alter table user add column user_type TEXT default ''");
        }
    };

    public void destroyInstance() {
        if (instance != null) {
            if (instance.isOpen()) {
                instance.close();
            }
        }
        instance = null;
    }

}
