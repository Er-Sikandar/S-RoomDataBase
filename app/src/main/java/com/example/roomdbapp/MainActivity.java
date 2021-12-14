package com.example.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.roomdbapp.DB.DataBaseClass;
import com.example.roomdbapp.DB.Entity.UserEntity;
import com.example.roomdbapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
 private ActivityMainBinding binding;
 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_main);


       binding.btnSave.setOnClickListener(view -> {
           if (binding.name.getText().toString().trim().isEmpty()){
               Toast.makeText(this, "Please enter value.", Toast.LENGTH_SHORT).show();
           }else if (binding.phone.getText().toString().trim().isEmpty()) {
               Toast.makeText(this, "Please enter value.", Toast.LENGTH_SHORT).show();
           }else if ( binding.email.getText().toString().trim().isEmpty()) {
               Toast.makeText(this, "Please enter value.", Toast.LENGTH_SHORT).show();
           }else if (binding.address.getText().toString().trim().isEmpty()) {
               Toast.makeText(this, "Please enter value.", Toast.LENGTH_SHORT).show();
           }else {
               UserEntity userEntity=new UserEntity();
               userEntity.setName(binding.name.getText().toString().trim());
               userEntity.setPhone(binding.phone.getText().toString().trim());
               userEntity.setEmail(binding.email.getText().toString().trim());
               userEntity.setAddress(binding.address.getText().toString().trim());
               DataBaseClass.getDataBase(MainActivity.this).getUserDao().insertUserData(userEntity);
               Toast.makeText(this, "Your Data Insert Successful.", Toast.LENGTH_SHORT).show();
           }
       });
       binding.btnGetData.setOnClickListener(view -> {
           startActivity(new Intent(MainActivity.this,UserListActivity.class));
       });

    }
}