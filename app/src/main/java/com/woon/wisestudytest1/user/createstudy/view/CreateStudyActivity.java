package com.woon.wisestudytest1.user.createstudy.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.CreateStudyVo;
import com.woon.wisestudytest1.user.createstudy.contract.CreateStudyContract;
import com.woon.wisestudytest1.user.createstudy.presenter.CreateStudyPresenter;
import com.woon.wisestudytest1.user.modifyuser.view.ModifyUserActivity;
import com.woon.wisestudytest1.util.UiHelper;

import java.io.File;

import okhttp3.MultipartBody;

import static com.woon.wisestudytest1.user.modifyuser.view.ModifyUserActivity.SELECT_IMAGE;


public class CreateStudyActivity extends AppCompatActivity implements CreateStudyContract.view, View.OnClickListener {

    private CreateStudyContract.presenter presenter;
    private Uri UserStudyImageUri;
    private String userKey = "";
    private Context mContext;
    private MultipartBody.Part studyImage;

    private ShapeableImageView userMakeStudy_studyImage;
    private TextInputEditText userMakeStudy_studyName;
    private TextInputEditText userMakeStudy_studyDescription;
    private TextInputEditText userMakeStudy_studyNeedMember;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_createstudy);
        UiHelper.hideWindow(this);
        UiHelper.toolBarInitialize(this, findViewById(R.id.userMakeStudy_toolbar));
        initialized();

        userKey = presenter.getJwt(mContext);
        userMakeStudy_studyImage.setOnClickListener(this);
    }

    private void initialized() {
        presenter = new CreateStudyPresenter(CreateStudyActivity.this);
        mContext = this;

        userMakeStudy_studyImage = findViewById(R.id.userMakeStudy_studyImage);
        userMakeStudy_studyName = findViewById(R.id.userMakeStudy_studyName);
        userMakeStudy_studyDescription = findViewById(R.id.userMakeStudy_studyDescription);
        userMakeStudy_studyNeedMember = findViewById(R.id.userMakeStudy_studyNeedMember);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.userMakeStudy_studyImage :
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        UserStudyImageUri = data.getData();
        Glide.with(this).load(UserStudyImageUri).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(userMakeStudy_studyImage);
        studyImage = presenter.registerImage(CreateStudyActivity.this, userKey, UserStudyImageUri);
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
            upLoadInformation();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void upLoadInformation() {
        CreateStudyVo studyInformation = getCreateStudy();
        presenter.registerStudyInformation(userKey, studyInformation);
    }

    private CreateStudyVo getCreateStudy() {

        MultipartBody.Part image = studyImage;
        String studyName = userMakeStudy_studyName.getText().toString();
        String studyDescription = userMakeStudy_studyDescription.getText().toString();
        Integer studyNeedMember = Integer.valueOf(userMakeStudy_studyNeedMember.getText().toString());
        String studyField = "안드로이드";

        CreateStudyVo createStudyVo = new CreateStudyVo(studyField, studyName, studyNeedMember, studyDescription, image);
        return createStudyVo;
    }

    @Override
    public void nextActivity() {
        Intent intent = new Intent(getApplicationContext(), ModifyUserActivity.class);
        startActivity(intent);
    }
}
