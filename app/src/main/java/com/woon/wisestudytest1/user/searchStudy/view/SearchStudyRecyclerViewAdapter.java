package com.woon.wisestudytest1.user.searchStudy.view;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.DetailStudyVo;
import com.woon.wisestudytest1.user.applystudy.view.ApplyStudyActivity;
import com.woon.wisestudytest1.user.searchStudy.contract.SearchStudyContract;

import java.util.ArrayList;
import java.util.List;

public class SearchStudyRecyclerViewAdapter extends RecyclerView.Adapter<SearchStudyRecyclerViewAdapter.myViewHolder> implements SearchStudyContract.adapterView, SearchStudyContract.adapterModel {

    private List<DetailStudyVo> list = new ArrayList<>();
    private View view;
    private int id;

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_study_recyclerviewitem, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Uri uri = Uri.parse(list.get(position).getStudy_image());
        Glide.with(view).load(uri).into(holder.studySearchImage);
        holder.studySearchStudyTitle.setText(list.get(position).getTitle());
        holder.studySearchStudyMemberCount.setText(Integer.toString(list.get(position).getLimit()));
        holder.studySearchStudyDescription.setText(list.get(position).getDescription());
        holder.studySearchStudyId.setText(Integer.toString(list.get(position).getStudy_id()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void changeItems() {
        notifyDataSetChanged();
    }

    @Override
    public void addItems(List<DetailStudyVo> data) {
        list = data;
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ShapeableImageView studySearchImage;
        private MaterialTextView studySearchStudyTitle;
        private MaterialTextView studySearchStudyId;
        private MaterialTextView studySearchStudyMemberCount;
        private MaterialTextView studySearchStudyDescription;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            studySearchImage = itemView.findViewById(R.id.studySearchImage);
            studySearchStudyTitle = itemView.findViewById(R.id.studySearchStudyTitle);
            studySearchStudyId = itemView.findViewById(R.id.studySearchStudyId);
            studySearchStudyMemberCount = itemView.findViewById(R.id.studySearchStudyMemberCount);
            studySearchStudyDescription = itemView.findViewById(R.id.studySearchStudyDescription);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            id = Integer.parseInt(studySearchStudyId.getText().toString());
            Intent intent = new Intent(itemView.getContext(), ApplyStudyActivity.class);
            intent.putExtra("STUDY_ID",id);
            v.getContext().startActivity(intent);
        }
    }
}
