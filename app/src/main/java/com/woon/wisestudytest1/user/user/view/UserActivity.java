package com.woon.wisestudytest1.user.user.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.modifyuser.view.ModifyUserActivity;
import com.woon.wisestudytest1.user.user.contract.UserContract;
import com.woon.wisestudytest1.user.user.presenter.UserPresenter;
import com.woon.wisestudytest1.util.TokenManager;
import com.woon.wisestudytest1.util.UiHelper;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends AppCompatActivity implements UserContract.view {

    private UserContract.presenter presenter;
    private Uri UserImageUri;
    private String userKey = "";

    private CircleImageView userImageView;
    private MaterialTextView userName;
    private MaterialTextView userAge;
    private MaterialTextView userPhoneNumber;

    private RecyclerView recyclerView;
    private UserStudiesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initialized();
        UiHelper.hideWindow(this);
        UiHelper.toolBarInitialize(this, findViewById(R.id.userToolbar));

        userKey = TokenManager.read(getApplicationContext());
        adapter = new UserStudiesAdapter();

        presenter = new UserPresenter(UserActivity.this, adapter);
        presenter.retrieveInformation(userKey);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void initialized() {
        recyclerView = findViewById(R.id.userApplyStudyRecyclerView);
        userImageView = findViewById(R.id.userImageView);
        userName = findViewById(R.id.userName);
        userAge = findViewById(R.id.userAge);
        userPhoneNumber = findViewById(R.id.userPhoneNumber);
    }

    @Override
    public void nextActivity() {
        Intent intent = new Intent(getApplicationContext(), ModifyUserActivity.class);
        startActivity(intent);
    }

    @Override
    public void showInformation(UserVo userVo) {
        if(userVo.isImg_flag() == true){
            UserImageUri = Uri.parse(userVo.getKakao_profile_img());
        }else{
            UserImageUri = Uri.parse(userVo.getS3_profile_img());
        }
        Glide.with(this).load(UserImageUri).into(userImageView);

        userName.setText(userVo.getName());
        userAge.setText(Integer.toString(userVo.getAge()));
        userPhoneNumber.setText(userVo.getCellphone());

        presenter.loadItems(userVo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.push_modify, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.modify){
            nextActivity();
        }
        return super.onOptionsItemSelected(item);
    }
}
