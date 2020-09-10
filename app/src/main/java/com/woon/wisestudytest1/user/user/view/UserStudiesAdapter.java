package com.woon.wisestudytest1.user.user.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.woon.wisestudytest1.user.user.contract.AdapterContract;

public class UserStudiesAdapter extends RecyclerView.Adapter<UserStudiesAdapter.MyViewHolder>implements AdapterContract.view, AdapterContract.model {
    @Override
    public void notifyAdapter() {

    }

    @Override
    public void addItems() {

    }

    @Override
    public void clearItems() {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
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
