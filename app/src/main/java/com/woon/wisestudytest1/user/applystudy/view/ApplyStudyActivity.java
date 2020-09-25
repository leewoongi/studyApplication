package com.woon.wisestudytest1.user.applystudy.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.applystudy.contract.ApplyStudyContract;
import com.woon.wisestudytest1.user.applystudy.presenter.ApplyStudyPresenter;
import com.woon.wisestudytest1.user.searchStudy.view.SearchStudyActivity;
import com.woon.wisestudytest1.util.TokenManager;
import com.woon.wisestudytest1.util.UiHelper;

public class ApplyStudyActivity extends AppCompatActivity implements ApplyStudyContract.view, View.OnClickListener {

    private ApplyStudyPresenter presenter;
    private ShapeableImageView applyStudyImageView;
    private MaterialTextView applyStudyName;
    private MaterialTextView applyStudyDescription;
    private MaterialTextView applyStudyNowMember;
    private MaterialButton applyStudyButton;

    private int studyId;
    private String userKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_study);

        UiHelper.toolBarInitialize(this, findViewById(R.id.applyStudyToolBar));
        UiHelper.hideWindow(this);

        initialized();

        studyId = getIntent().getIntExtra("STUDY_ID",0);
        userKey = TokenManager.read(this);

        presenter = new ApplyStudyPresenter(this);
        presenter.retrieveStudyInformation(studyId);
        applyStudyButton.setOnClickListener(this);

    }

    private void initialized() {
        applyStudyImageView = findViewById(R.id.applyStudyImageView);
        applyStudyName = findViewById(R.id.applyStudyName);
        applyStudyDescription = findViewById(R.id.applyStudyDescription);
        applyStudyNowMember = findViewById(R.id.applyStudyNowMember);
        applyStudyButton = findViewById(R.id.applyStudyButton);
    }

    @Override
    public void showInformation(Uri uri, String name, String des, int member) {
        Glide.with(this).load(uri).into(applyStudyImageView);
        applyStudyName.setText(name);
        applyStudyDescription.setText(des);
        applyStudyNowMember.setText(Integer.toString(member));
    }

    @Override
    public void nextActivity() {
        Intent intent = new Intent(getApplicationContext(), SearchStudyActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        boolean flag = UiHelper.dialogStart(applyStudyButton.getContext(),"신청 하시겠습니까?");
        if(flag == true){
            presenter.registerApplyMember(studyId, userKey);
        }
    }
}
