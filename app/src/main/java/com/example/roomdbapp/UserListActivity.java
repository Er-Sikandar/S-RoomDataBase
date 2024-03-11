package com.example.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.roomdbapp.DB.DataBaseClass;
import com.example.roomdbapp.DB.Entity.UserEntity;
import com.example.roomdbapp.adapter.UserListAdapter;
import com.example.roomdbapp.databinding.ActivityUserListBinding;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
private ActivityUserListBinding binding;
private UserListAdapter adapter;
private DataBaseClass dataBaseClass;
private List<UserEntity> dataList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_user_list);
        dataBaseClass=DataBaseClass.getDataBase(UserListActivity.this);
        if (dataBaseClass!=null) {
            dataList = dataBaseClass.getUserDao().getAllData();
            dataBaseClass.destroyInstance();
            if (dataList != null) {
                adapter = new UserListAdapter(UserListActivity.this, dataList);
                binding.recUserList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                binding.recUserList.setAdapter(adapter);
            } else {
                Toast.makeText(this, "No Data Found!", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "No Data Found!", Toast.LENGTH_SHORT).show();
        }
    }
}