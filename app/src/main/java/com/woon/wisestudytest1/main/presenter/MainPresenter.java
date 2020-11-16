package com.woon.wisestudytest1.main.presenter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.woon.wisestudytest1.main.Entity.StudyVo;
import com.woon.wisestudytest1.main.contract.MainContract;
import com.woon.wisestudytest1.main.model.MainLocalModel;
import com.woon.wisestudytest1.main.model.MainRemoteModel;
import com.woon.wisestudytest1.main.view.CreateStudyFragment;
import com.woon.wisestudytest1.main.view.MyPageFragment;
import com.woon.wisestudytest1.main.view.ScheduleFragment;
import com.woon.wisestudytest1.main.view.SearchFragment;
import com.woon.wisestudytest1.user.Entity.UserStudies;
import com.woon.wisestudytest1.user.Entity.UserVo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

public class MainPresenter implements MainContract.presenter {

    private MainContract.view view;
    private MainRemoteModel remoteModel;
    private MainLocalModel localModel;
    private MainContract.recyclerAdapterView recyclerAdapterView;
    private MainContract.recyclerAdapterModel recyclerAdapterModel;
    private MainContract.pageAdapterView pageAdapterView;
    private MainContract.pageAdapterModel pageAdapterModel;

    private MyPageFragment myPageFragment;
    private CreateStudyFragment createStudyFragment;
    private ScheduleFragment scheduleFragment;
    private SearchFragment searchFragment;

    public MainPresenter(MainContract.view view) {

        this.view = view;
        myPageFragment = new MyPageFragment(this);
        createStudyFragment = new CreateStudyFragment(this);
        scheduleFragment = new ScheduleFragment(this);
        searchFragment = new SearchFragment(this);
        remoteModel = new MainRemoteModel(this);
        localModel = new MainLocalModel(this);
    }


    @Override
    public void retrieveInformation(String userKey) {
        remoteModel.getUserInformation(userKey);
    }

    @Override
    public void responseUserInformation(UserVo userVo) {
        myPageFragment.showFragment(userVo);
    }

    @Override
    public void loadFragment(Bundle bundle, String userKey) {
        List<Fragment> page = new ArrayList<>();
        bundle.putString("JWT", userKey);

        page.add(myPageFragment);
        page.add(createStudyFragment);
        page.add(scheduleFragment);
        page.add(searchFragment);

        page.get(0).setArguments(bundle);
        page.get(1).setArguments(bundle);

        pageAdapterModel.addFragments(page);
        pageAdapterView.refresh();
    }

    @Override
    public void loadItems(UserVo userVo) {
        List<UserStudies> studies = userVo.getStudy_list();
        recyclerAdapterModel.addItems(studies);
        recyclerAdapterView.refresh();
    }

    //스터디 생성 registerImage , registerStudyInformation

    @Override
    public void registerStudyInformation(Activity activity, String userKey, Uri uri, String category, String title, Integer limit, String description) {
        remoteModel.requestStudyInformation(activity, userKey, uri, category, title, limit, description);
    }


    @Override
    public void setViewPagerView(MainContract.pageAdapterView pageAdapterView) {
        this.pageAdapterView = pageAdapterView;
    }

    @Override
    public void setViewPagerModel(MainContract.pageAdapterModel pageAdapterModel) {
        this.pageAdapterModel = pageAdapterModel;
    }

    @Override
    public void setRecyclerView(MainContract.recyclerAdapterView recyclerAdapterView) {
        this.recyclerAdapterView = recyclerAdapterView;
    }

    @Override
    public void setRecyclerModel(MainContract.recyclerAdapterModel recyclerAdapterModel) {
        this.recyclerAdapterModel = recyclerAdapterModel;
    }

}
