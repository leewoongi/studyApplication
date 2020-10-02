package com.woon.wisestudytest1.main.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.main.contract.MainContract;
import com.woon.wisestudytest1.main.contract.MainFragmentContract;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.createstudy.view.CreateStudyActivity;

import static com.woon.wisestudytest1.user.modifyuser.view.ModifyUserActivity.SELECT_IMAGE;

public class CreateStudyFragment extends Fragment implements MainFragmentContract.fragmentView, View.OnClickListener {

    private MainContract.presenter presenter;
    private MaterialTextView createStudyImageGuide;
    private ShapeableImageView userMakeStudy_studyImage;

    private ViewGroup rootView;

    private Uri uri;
    private String stringUri = "";

    public CreateStudyFragment(MainContract.presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_main__createstudy_fragment, container, false);

        initialized();
        if(stringUri != ""){
            showTextView();
        }

        createStudyImageGuide.setOnClickListener(this);
        userMakeStudy_studyImage.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void initialized() {
        createStudyImageGuide = rootView.findViewById(R.id.createStudyImageGuide);
        userMakeStudy_studyImage = rootView.findViewById(R.id.userMakeStudy_studyImage);
    }

    private void showTextView() {
        createStudyImageGuide.setVisibility(View.INVISIBLE);
    }


    @Override
    public void showFragment(UserVo userVo) {

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        uri = data.getData();
        Glide.with(this).load(uri).diskCacheStrategy(DiskCacheStrategy.RESOURCE).fitCenter().into(userMakeStudy_studyImage);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
