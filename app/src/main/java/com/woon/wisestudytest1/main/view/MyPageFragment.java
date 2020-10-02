package com.woon.wisestudytest1.main.view;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.woon.wisestudytest1.R;
import com.woon.wisestudytest1.main.contract.MainContract;
import com.woon.wisestudytest1.main.contract.MainFragmentContract;
import com.woon.wisestudytest1.main.presenter.MainPresenter;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.util.UiHelper;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyPageFragment extends Fragment implements MainFragmentContract.fragmentView {

    public static int myId;

    private MainPresenter presenter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MainMyPageAdapter adapter;

    private String userKey;
    private ViewGroup rootView;

    private Uri UserImageUri;
    private CircleImageView userImageView;
    private MaterialTextView userName;
    private MaterialTextView userAge;
    private MaterialTextView userPhoneNumber;

    public MyPageFragment(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.activity_main_user_fragment,container,false);
        userKey = getArguments().getString("JWT");

        initialized();
        recyclerViewInitialized();

        presenter.retrieveInformation(userKey);

        adapter = new MainMyPageAdapter();
        recyclerView.setAdapter(adapter);

        presenter.setRecyclerView(adapter);
        presenter.setRecyclerModel(adapter);

        return rootView;
    }

    private void recyclerViewInitialized() {
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void initialized() {

        recyclerView = rootView.findViewById(R.id.userApplyStudyRecyclerView);
        userImageView = rootView.findViewById(R.id.userImageView);
        userName = rootView.findViewById(R.id.userName);
        userAge = rootView.findViewById(R.id.userAge);
        userPhoneNumber = rootView.findViewById(R.id.userPhoneNumber);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.push_modify, menu);
    }

    @Override
    public void showFragment(UserVo userVo) {
        myId = userVo.getUser_id();
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
}
