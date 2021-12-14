package com.example.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.roomdbapp.DB.DataBaseClass;
import com.example.roomdbapp.adapter.UserListAdapter;
import com.example.roomdbapp.databinding.ActivityUserListBinding;

public class UserListActivity extends AppCompatActivity {
private ActivityUserListBinding binding;
private UserListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_user_list);

   adapter=new UserListAdapter(UserListActivity.this, DataBaseClass.getDataBase(UserListActivity.this).getUserDao().getAllData());
   binding.recUserList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
    binding.recUserList.setAdapter(adapter);
    }
}