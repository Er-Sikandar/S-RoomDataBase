package com.example.roomdbapp.DB.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roomdbapp.DB.Entity.UserEntity;

import java.util.List;

@Dao
public interface MainDao {
    // insert user data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserData(UserEntity userEntity);

    @Query("select exists(select * from user where email = :emailId)")
    boolean getIsExist(String emailId);

    // select all data
    @Query("select * from user")
    List<UserEntity> getAllData();


    @Query("select `key`,name,phone,gender,email,address from user where email= :emailId limit 1")
    UserEntity getUserProfile(String emailId);

    @Delete
    void delete(UserEntity entity);

}
