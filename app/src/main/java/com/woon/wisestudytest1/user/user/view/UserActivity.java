package com.woon.wisestudytest1.user.user.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.modifyuser.view.ModifyUserActivity;
import com.woon.wisestudytest1.user.user.contract.UserContract;
import com.woon.wisestudytest1.user.user.presenter.UserPresenter;
import com.woon.wisestudytest1.util.TokenManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends AppCompatActivity implements UserContract.view {

    private UserContract.presenter presenter;
    private String userKey = "";

    private CircleImageView userImageView;
    private MaterialTextView userName;
    private MaterialTextView userAge;
    private MaterialTextView userPhoneNumber;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initialized();
        userKey = TokenManager.read(getApplicationContext());

        presenter = new UserPresenter(UserActivity.this);
        presenter.retrieveInformation(userKey);
    }

    private void initialized() {
        userImageView = findViewById(R.id.userImageView);
        userName = findViewById(R.id.userName);
        userAge = findViewById(R.id.userAge);
        userPhoneNumber = findViewById(R.id.userPhoneNumber);
    }

    @Override
    public void nextActivity() {

    }

    @Override
    public void showInformation(UserVo userVo) {

    }

    @Override
    public void showJoinedStudy() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.push_modify, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.modify){
            Intent intent = new Intent(getApplicationContext(), ModifyUserActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
