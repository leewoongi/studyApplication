package com.woon.wisestudytest1.user.user.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.UserStudies;
import com.woon.wisestudytest1.user.user.contract.UserAdapterContract;

import java.util.ArrayList;
import java.util.List;

public class UserStudiesAdapter extends RecyclerView.Adapter<UserStudiesAdapter.MyViewHolder> implements UserAdapterContract.adapterView, UserAdapterContract.adapterModel{

    private List<UserStudies> data = new ArrayList();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_recyclerviewitem, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        holder.userRecyclerViewStudyName.setText(data.get(position).getTitle());
        holder.userRecyclerViewStudyId.setText(Integer.toString(data.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void refresh() {

    }

    @Override
    public void addItems(List<UserStudies> studies) {
        data = studies;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView userRecyclerViewStudyName;
        private TextView userRecyclerViewStudyId;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userRecyclerViewStudyName = itemView.findViewById(R.id.userRecyclerViewStudyName);
            userRecyclerViewStudyId = itemView.findViewById(R.id.userRecyclerViewStudyId);
        }
    }
}
