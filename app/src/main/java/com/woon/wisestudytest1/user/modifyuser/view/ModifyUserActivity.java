package com.woon.wisestudytest1.user.modifyuser.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.modifyuser.contract.ModifyUserContract;
import com.woon.wisestudytest1.user.modifyuser.presenter.ModifyUserPresenter;
import com.woon.wisestudytest1.util.UiHelper;

public class ModifyUserActivity extends AppCompatActivity implements ModifyUserContract.view, View.OnClickListener {

    private ModifyUserContract.presenter presenter;
    private String userKey = "";

    //개인정보보
   private TextInputEditText userModifyUserName;
    private TextInputEditText userModifyUserAge;
    private TextInputEditText userModifyUserPhone;
    private TextInputEditText userModifyUserDescription;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_modify);

        initialized();

        presenter = new ModifyUserPresenter(ModifyUserActivity.this);
        userKey = presenter.getJwt(mContext);
        presenter.requestModifyUser(userKey);

        //이미지 뷰 클릭
        findViewById(R.id.userModifyImageView).setOnClickListener(this);
    }

    private void initialized() {
        UiHelper.toolBarInitialize(this, findViewById(R.id.userModifyToolbar));
        UiHelper.hideWindow(this);

        mContext = this;
        userModifyUserName = findViewById(R.id.userModifyUserName);
        userModifyUserAge = findViewById(R.id.userModifyUserAge);
        userModifyUserPhone = findViewById(R.id.userModifyUserPhone);
        userModifyUserDescription = findViewById(R.id.userModifyUserDescription);

    }


    @Override
    public void showInformation() {

    }

    @Override
    public void showImage() {

    }


    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.userModifyImageView :
                presenter.changeImage();

        }

    }
}
