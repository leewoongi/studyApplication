package com.woon.wisestudytest1.user.user.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.UserStudies;
import com.woon.wisestudytest1.user.user.contract.UserAdapterContract;

import java.util.List;

public class UserStudiesAdapter extends RecyclerView.Adapter<UserStudiesAdapter.MyViewHolder> implements UserAdapterContract.adapterView, UserAdapterContract.adapterModel{

    private List<UserStudies> data;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_recyclerviewitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
