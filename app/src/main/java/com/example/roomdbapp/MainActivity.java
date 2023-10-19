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
 private DataBaseClass dataBaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        dataBaseClass=DataBaseClass.getDataBase(MainActivity.this);
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
                if (dataBaseClass.getUserDao().getIsExist(binding.email.getText().toString().trim())){
                    Toast.makeText(this, "Already Exist Record.", Toast.LENGTH_SHORT).show();
                    binding.email.setText("");
                }else {
                    UserEntity userEntity=new UserEntity();
                    userEntity.setName(binding.name.getText().toString().trim());
                    userEntity.setPhone(binding.phone.getText().toString().trim());
                    userEntity.setEmail(binding.email.getText().toString().trim());
                    userEntity.setAddress(binding.address.getText().toString().trim());
                    userEntity.setUserType("user");
                    dataBaseClass.getUserDao().insertUserData(userEntity);
                    dataBaseClass.destroyInstance();
                    Toast.makeText(this, "Your Data Insert Successful.", Toast.LENGTH_SHORT).show();
                    finish();
                }
           }
       });
       binding.btnGetData.setOnClickListener(view -> {
           startActivity(new Intent(MainActivity.this,UserListActivity.class));
       });

    }
}