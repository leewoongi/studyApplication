package com.woon.wisestudytest1.user.modifyuser.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.textfield.TextInputEditText;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.modifyuser.contract.ModifyUserContract;
import com.woon.wisestudytest1.user.modifyuser.presenter.ModifyUserPresenter;
import com.woon.wisestudytest1.util.UiHelper;

public class ModifyUserActivity extends AppCompatActivity implements ModifyUserContract.view, View.OnClickListener {

    private ModifyUserContract.presenter presenter;
    private final static int SELECT_IMAGE = 1;
    private Uri uri;
    private String userKey = "";

    //개인정보
    private TextInputEditText userModifyUserName;
    private TextInputEditText userModifyUserAge;
    private TextInputEditText userModifyUserPhone;
    private TextInputEditText userModifyUserDescription;
    private ImageView userModifyImageView;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_modify);

        initialized();

        presenter = new ModifyUserPresenter(ModifyUserActivity.this);
        userKey = presenter.getJwt(mContext);
        presenter.updateUserInformation(userKey);

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
        userModifyImageView = findViewById(R.id.userModifyImageView);

    }


    @Override
    public void showInformation(UserVo item) {

        if(item.isImg_flag() == false){
            uri = Uri.parse(item.getKakao_profile_img());
        }else{
            uri = Uri.parse(item.getS3_profile_img());
        }

        Glide.with(this).load(uri).into(userModifyImageView);
        if (item.getAge() != null) {
            userModifyUserName.setText(item.getName());
            userModifyUserAge.setText((item.getAge()).toString());
            userModifyUserPhone.setText(item.getCellphone());
            userModifyUserDescription.setText(item.getDescription());
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.userModifyImageView :
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Glide.with(this).load(data.getData()).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(userModifyImageView);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.push_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.ok){
            // 개인정보 put


        }
        return super.onOptionsItemSelected(item);
    }
}
