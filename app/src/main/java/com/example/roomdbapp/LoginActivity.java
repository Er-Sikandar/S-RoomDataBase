package com.example.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.roomdbapp.DB.DataBaseClass;
import com.example.roomdbapp.DB.Entity.UserEntity;
import com.example.roomdbapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
private ActivityLoginBinding binding;
private UserEntity userEntity;
private DataBaseClass dataBaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        dataBaseClass=DataBaseClass.getDataBase(LoginActivity.this);
        binding.tvSignup.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        });
        binding.btnLogin.setOnClickListener(view -> {
            if (binding.email.getText().toString().trim().isEmpty()){
                Toast.makeText(this, "Enter email address", Toast.LENGTH_SHORT).show();
            }else {
                userEntity=new UserEntity();
                    userEntity = dataBaseClass.getUserDao().getUserProfile(binding.email.getText().toString().trim());
                    if (userEntity != null) {
                        binding.cardView.setVisibility(View.VISIBLE);
                        binding.tvName.setText("Name: " + userEntity.getName() + "(" + userEntity.getGender() + ")");
                        binding.tvPhone.setText("Mobile: " + userEntity.getPhone());
                        binding.tvEmail.setText("Email: " + userEntity.getEmail());
                        binding.tvAddress.setText("Address: " + userEntity.getAddress()+ "(" + userEntity.getPinCode() + ")");
                        Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        dataBaseClass.destroyInstance();
                        binding.email.setText("");

                    } else {
                        binding.cardView.setVisibility(View.GONE);
                        Toast.makeText(this, "Please enter valid email address ", Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }
}