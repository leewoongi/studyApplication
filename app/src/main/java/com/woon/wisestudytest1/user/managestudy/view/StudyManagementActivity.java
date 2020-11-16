package com.woon.wisestudytest1.user.managestudy.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.applyuser.view.ApplyUserActivity;
import com.woon.wisestudytest1.util.UiHelper;

public class StudyManagementActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton managementCreateStudy;
    private MaterialButton manageCurrentApply;
    private MaterialButton managementGroupMember;
    private MaterialButton managementDeleteGroup;
    private int study_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_study);

        UiHelper.toolBarInitialize(this,findViewById(R.id.managementToolbar));
        UiHelper.hideWindow(this);

        initialized();

        study_id = getIntent().getIntExtra("STUDY_ID", 0);

        managementCreateStudy.setOnClickListener(this);
        manageCurrentApply.setOnClickListener(this);
        managementGroupMember.setOnClickListener(this);
        managementDeleteGroup.setOnClickListener(this);
    }

    private void initialized() {
        managementCreateStudy = findViewById(R.id.managementCreateStudy);
        manageCurrentApply = findViewById(R.id.manageCurrentApply);
        managementGroupMember = findViewById(R.id.managementGroupMember);
        managementDeleteGroup = findViewById(R.id.managementDeleteGroup);
    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()){
            case R.id.managementCreateStudy:

            case R.id.manageCurrentApply:
                intent = new Intent(getApplicationContext(), ApplyUserActivity.class);
                intent.putExtra("STUDY_ID",study_id);
                startActivity(intent);
            case R.id.managementGroupMember:
                /**
                 * 아직 미 구성
                 */
            case R.id.managementDeleteGroup :
                /**
                 * 아직 미 구성
                 */
            default:
                break;
        }

    }
}
