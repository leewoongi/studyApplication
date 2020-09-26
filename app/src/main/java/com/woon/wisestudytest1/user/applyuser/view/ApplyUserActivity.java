package com.woon.wisestudytest1.user.applyuser.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.landing.view.LandingActivity;
import com.woon.wisestudytest1.user.applyuser.cotract.ApplyUserContract;
import com.woon.wisestudytest1.user.applyuser.presenter.ApplyUserPresenter;
import com.woon.wisestudytest1.util.TokenManager;
import com.woon.wisestudytest1.util.UiHelper;

public class ApplyUserActivity extends AppCompatActivity implements ApplyUserContract.view {

    private ApplyUserContract.presenter presenter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ApplyUserRecyclerViewAdapter adapter;

    private int study_id;
    private String userKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_user);

        UiHelper.hideWindow(this);
        UiHelper.toolBarInitialize(this, findViewById(R.id.presentApplicationToolbar));

        study_id = getIntent().getIntExtra("STUDY_ID",0);
        userKey = LandingActivity.userKey;

        initialized();
        presenter = new ApplyUserPresenter(this, userKey);

        recyclerInitialized();

        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);
        presenter.retrieveApply(study_id);

    }

    private void recyclerInitialized() {
        adapter = new ApplyUserRecyclerViewAdapter((ApplyUserPresenter) presenter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void initialized() {
        recyclerView = findViewById(R.id.presentApplicationRecyclerView);
    }

    @Override
    public void showApplyInformation() {

    }

    @Override
    public void showToast() {
        Toast.makeText(getApplicationContext(),"처리되었습니다",Toast.LENGTH_LONG).show();
    }
}
