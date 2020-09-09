package com.woon.wisestudytest1.user.modifyuser.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.textfield.TextInputEditText;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.UpdateUserVo;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.createstudy.view.CreateStudyActivity;
import com.woon.wisestudytest1.user.modifyuser.contract.ModifyUserContract;
import com.woon.wisestudytest1.user.modifyuser.presenter.ModifyUserPresenter;
import com.woon.wisestudytest1.user.user.view.UserActivity;
import com.woon.wisestudytest1.util.UiHelper;

public class ModifyUserActivity extends AppCompatActivity implements ModifyUserContract.view, View.OnClickListener {

    private ModifyUserContract.presenter presenter;
    public final static int SELECT_IMAGE = 1;
    private Uri UserImageUri;
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
        // 유저정보 받아오기
        presenter.bringUserInformation(userKey);

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
        if(item.isImg_flag() == true){
            UserImageUri = Uri.parse(item.getKakao_profile_img());
        }else{
            UserImageUri = Uri.parse(item.getS3_profile_img());
        }

        Glide.with(this).load(UserImageUri).into(userModifyImageView);
        if (item.getAge() != null) {
            userModifyUserName.setText(item.getName());
            userModifyUserAge.setText((item.getAge()).toString());
            userModifyUserPhone.setText(item.getCellphone());
            userModifyUserDescription.setText(item.getDescription());
        }
    }

    @Override
    public void nextActivity() {
        Intent intent = new Intent(getApplicationContext(), CreateStudyActivity.class);
        startActivity(intent);
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
        UserImageUri = data.getData();
        Glide.with(this).load(UserImageUri).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(userModifyImageView);
        presenter.upLoadImage(ModifyUserActivity.this, userKey, UserImageUri);
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
            UpdateUserVo userInformation = getUser();
            if(userInformation == null){
                Toast.makeText(getApplicationContext(), "모두 입력해주세요", Toast.LENGTH_SHORT).show();
            }
            else{
                // 개인정보 put
                presenter.updateUserInformation(userKey, userInformation);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private UpdateUserVo getUser() {

        String userName = userModifyUserName.getText().toString();
        String userAge = userModifyUserAge.getText().toString();
        String userPhone = userModifyUserPhone.getText().toString();
        String userDescription = userModifyUserDescription.getText().toString();

        if(userName.getBytes().length <= 0 || userAge.getBytes().length <= 0
                || userPhone.getBytes().length <= 0 || userDescription.getBytes().length <= 0) {
            return null;
        }
        else{
            UpdateUserVo user = new UpdateUserVo(userName, Integer.parseInt(userAge),
                    userPhone, userDescription);
            return user;
        }
    }
}
