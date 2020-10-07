package com.woon.wisestudytest1.main.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.main.contract.MainContract;
import com.woon.wisestudytest1.util.UiHelper;

import static com.woon.wisestudytest1.user.modifyuser.view.ModifyUserActivity.SELECT_IMAGE;

public class CreateStudyFragment extends Fragment implements MainContract.fragmentView, View.OnClickListener {

    private MainContract.presenter presenter;
    private MaterialToolbar userMakeStudy_toolbar;

    private MaterialTextView createStudyImageGuide;
    private ShapeableImageView userMakeStudy_studyImage;
    private TextInputEditText title;
    private TextInputEditText description;
    private TextInputEditText limit;

    private String userKey;
    private ViewGroup rootView;

    private Uri uri;
    private String stringUri = "";


    public CreateStudyFragment(MainContract.presenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.activity_main_createstudy_fragment, container, false);
        userKey = getArguments().getString("JWT");


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
        userMakeStudy_toolbar = rootView.findViewById(R.id.userMakeStudy_toolbar);
        createStudyImageGuide = rootView.findViewById(R.id.createStudyImageGuide);
        userMakeStudy_studyImage = rootView.findViewById(R.id.userMakeStudy_studyImage);
        title = rootView.findViewById(R.id.userMakeStudy_studyName);
        description = rootView.findViewById(R.id.userMakeStudy_studyDescription);
        limit = rootView.findViewById(R.id.userMakeStudy_studyNeedMember);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.push_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.ok :
                String category = "";
                String tmpTitle = title.getText().toString();
                String tmpDescription = description.getText().toString();
                int tmpLimit = Integer.parseInt(limit.getText().toString());
                presenter.registerStudyInformation(getActivity(), userKey, uri, category, tmpTitle, tmpLimit, tmpDescription);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showFragment(Object data) {
        //등록부분이라 보여줄거는 없음
    }

    private void showTextView() {
        createStudyImageGuide.setVisibility(View.INVISIBLE);
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
        Glide.with(this).load(uri).diskCacheStrategy(DiskCacheStrategy.RESOURCE).centerCrop().into(userMakeStudy_studyImage);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
