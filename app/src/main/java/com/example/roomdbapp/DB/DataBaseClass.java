package com.example.roomdbapp.DB;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.databinding.adapters.Converters;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.roomdbapp.DB.Dao.MainDao;
import com.example.roomdbapp.DB.Entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 2,exportSchema = true,
        autoMigrations = {
                @AutoMigration (from = 1, to = 2,spec = DataBaseClass.MyAutoMigration.class)
        }
        )
/*@AutoMigration(from = 1, to = 2)*/


public abstract class DataBaseClass extends RoomDatabase {
    public abstract MainDao getUserDao();

    private static DataBaseClass instance;

    public static DataBaseClass getDataBase(final Context context) {
        if (instance == null) {
            synchronized (DataBaseClass.class) {
                instance=Room.databaseBuilder(context, DataBaseClass.class, context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "RoomDbApp")
                        .allowMainThreadQueries()
                        .build();


               /* instance = Room.databaseBuilder(context, DataBaseClass.class, "DATABASE")
                         .allowMainThreadQueries()
                       // .addMigrations(MIGRATION_1_2)  //manual migration
                       // .addMigrations(MIGRATION_2_3)  //manual migration
                        //.fallbackToDestructiveMigration() //when need to refresh record
                        .build();*/
            }
        }
        return instance;
    }

    static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull final SupportSQLiteDatabase database) {
            database.execSQL("alter table user add column pinCode TEXT default ''");
        }
    };
    static Migration MIGRATION_2_3= new Migration(2, 3) {
        @Override
        public void migrate(@NonNull final SupportSQLiteDatabase database) {
            database.execSQL("alter table user add column gender TEXT default ''");
        }
    };

    /**
     * Auto migrate
     */
    static class MyAutoMigration implements AutoMigrationSpec {
        @Override
        public void onPostMigrate(@NonNull SupportSQLiteDatabase db) {
            db.execSQL("alter table user add column pinCode TEXT default ''");

            AutoMigrationSpec.super.onPostMigrate(db);
        }
    }



    public void destroyInstance() {
        if (instance != null) {
            if (instance.isOpen()) {
                instance.close();
            }
        }
        instance = null;
    }

}
