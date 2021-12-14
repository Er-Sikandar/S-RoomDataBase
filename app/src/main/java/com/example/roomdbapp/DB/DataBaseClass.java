package com.example.roomdbapp.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdbapp.DB.Dao.UserDao;
import com.example.roomdbapp.DB.Entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)

public abstract class DataBaseClass extends RoomDatabase {
    public abstract UserDao getUserDao();

    private static DataBaseClass instance;

    public static DataBaseClass getDataBase(final Context context) {
        if (instance == null) {
            synchronized (DataBaseClass.class) {
                instance = Room.databaseBuilder(context, DataBaseClass.class, "DATABASE")
                        .allowMainThreadQueries().build();
            }
        }
        return instance;
    }


}
