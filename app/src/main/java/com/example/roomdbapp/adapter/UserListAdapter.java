package com.example.roomdbapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdbapp.DB.Entity.UserEntity;
import com.example.roomdbapp.R;
import com.example.roomdbapp.databinding.RecUserListBinding;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
   private Context context;
   private List<UserEntity> dataSet;

    public UserListAdapter(Context context, List<UserEntity> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecUserListBinding binding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.rec_user_list,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvId.setText("S.No. "+dataSet.get(position).getKey());
holder.binding.tvName.setText(dataSet.get(position).getName());
holder.binding.tvPhone.setText(dataSet.get(position).getPhone());
holder.binding.tvEmail.setText(dataSet.get(position).getEmail());
holder.binding.tvAddress.setText(dataSet.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return dataSet==null ? 0 : dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RecUserListBinding binding;
        public ViewHolder(@NonNull RecUserListBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
