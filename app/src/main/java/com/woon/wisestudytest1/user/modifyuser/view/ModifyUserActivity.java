package com.woon.wisestudytest1.user.modifyuser.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.user.contract.UserContract;
import com.woon.wisestudytest1.util.UiHelper;

public class ModifyUserActivity extends AppCompatActivity implements UserContract.view, View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_modify);

        initialized();
        findViewById(R.id.userModifyImageView).setOnClickListener(this);
    }

    private void initialized() {
        UiHelper.toolBarInitialize(this, findViewById(R.id.userModifyToolbar));
        UiHelper.hideWindow(this);
    }

    @Override
    public void showInformation() {

    }

    @Override
    public void showJoinedStudy() {

    }

    @Override
    public void showFavoriteField() {

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.userModifyImageView :
                

        }

    }
}
