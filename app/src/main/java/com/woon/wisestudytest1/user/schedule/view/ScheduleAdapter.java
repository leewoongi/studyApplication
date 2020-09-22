package com.woon.wisestudytest1.user.schedule.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.UserSchedules;
import com.woon.wisestudytest1.user.detailschedule.view.DetailScheduleActivity;
import com.woon.wisestudytest1.user.schedule.contract.ScheduleAdapterContract;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> implements ScheduleAdapterContract.adapterView, ScheduleAdapterContract.adapterModel {

    private List<UserSchedules> data = new ArrayList<>();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_schedule_recyclerviewitem, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleId.setText(data.get(position).getStudy_title());
        holder.dateId.setText(data.get(position).getDatetime());
        holder.locationId.setText(data.get(position).getPlace());
        holder.scheduleId.setText(Integer.toString(data.get(position).getSchedule_id()));
        holder.studyId.setText(Integer.toString(data.get(position).getStudy_id()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void addItems(List<UserSchedules> item) {
        data = item;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleId;
        private TextView dateId;
        private TextView locationId;
        private TextView studyId;
        private TextView scheduleId;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleId = itemView.findViewById(R.id.titleId);
            dateId = itemView.findViewById(R.id.dateId);
            locationId = itemView.findViewById(R.id.locationId);
            studyId = itemView.findViewById(R.id.studyId);
            scheduleId = itemView.findViewById(R.id.scheduleId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int study_id = Integer.parseInt(studyId.getText().toString());
                    int schedule_id = Integer.parseInt(scheduleId.getText().toString());

                    Intent intent = new Intent(itemView.getContext(), DetailScheduleActivity.class);
                    intent.putExtra("studyId", study_id);
                    intent.putExtra("scheduleId", schedule_id);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
