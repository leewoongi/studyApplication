package com.woon.wisestudytest1.user.applyuser.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.applyuser.cotract.ApplyUserContract;
import com.woon.wisestudytest1.user.applyuser.presenter.ApplyUserPresenter;
import com.woon.wisestudytest1.user.user.view.UserActivity;

import java.util.ArrayList;
import java.util.List;

public class ApplyUserRecyclerViewAdapter extends RecyclerView.Adapter<ApplyUserRecyclerViewAdapter.myViewHolder> implements ApplyUserContract.adapterView, ApplyUserContract.adapterModel {

    private ApplyUserPresenter presenter;
    private List<UserVo> list = new ArrayList<>();
    private View view;
    private int user_id;

    public ApplyUserRecyclerViewAdapter(ApplyUserPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_apply_user_recyclerviewitem, parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        user_id = list.get(position).getUser_id();
        holder.presentApplyUserId.setText(Integer.toString(user_id));
        holder.presentApplicationName.setText(list.get(position).getName() + " " + list.get(position).getAge());
        holder.presentApplicationPhone.setText(list.get(position).getCellphone());
        holder.presentApplicationDescription.setText(list.get(position).getDescription());
        holder.presentApplicationFieldTitle.setText("관심분야");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void addItems(List<UserVo> data) {
        list = data;
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView presentApplicationName;
        private TextView presentApplicationPhone;
        private TextView presentApplicationDescription;
        private TextView presentApplicationFieldTitle;
        private TextView presentApplyUserId;
        private MaterialButton presentApplicationAcceptButton;
        private MaterialButton presentApplicationRejectButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            presentApplicationName = itemView.findViewById(R.id.presentApplicationName);
            presentApplicationPhone = itemView.findViewById(R.id.presentApplicationPhone);
            presentApplicationDescription = itemView.findViewById(R.id.presentApplicationDescription);
            presentApplicationFieldTitle = itemView.findViewById(R.id.presentApplicationFieldTitle);
            presentApplyUserId = itemView.findViewById(R.id.presentApplyUserId);
            presentApplicationAcceptButton = itemView.findViewById(R.id.presentApplicationAcceptButton);
            presentApplicationRejectButton = itemView.findViewById(R.id.presentApplicationRejectButton);

            presentApplicationAcceptButton.setOnClickListener(this);
            presentApplicationRejectButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.presentApplicationAcceptButton :
                    presenter.confirmApply(user_id);
                    break;
                case R.id.presentApplicationRejectButton :
                    presenter.rejectApply(user_id);
                    break;
                default:
                    break;
            }
        }
    }
}
