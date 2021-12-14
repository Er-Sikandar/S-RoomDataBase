package com.example.roomdbapp.DB.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomdbapp.DB.Entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    // insert user data
    @Insert
    void insertUserData(UserEntity userEntity);

    // select all data
    @Query("select * from user")
    List<UserEntity> getAllData();


}
