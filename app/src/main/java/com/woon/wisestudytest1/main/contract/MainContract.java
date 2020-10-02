package com.woon.wisestudytest1.main.contract;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.woon.wisestudytest1.user.Entity.UserStudies;
import com.woon.wisestudytest1.user.Entity.UserVo;

import java.util.List;

public interface MainContract {

    interface view{
        void showFragment();
    }

    interface presenter{

        //유저 정보 가져오기
        void retrieveInformation(String userKey);
        void responseUserInformation(UserVo userVo);

        void loadFragment(Bundle bundle, String userKey);
        void loadItems(UserVo userVo);

        void setViewPagerView(pageAdapterView pageAdapterView);
        void setViewPagerModel(pageAdapterModel pageAdapterModel);

        void setRecyclerView(recyclerAdapterView view);
        void setRecyclerModel(recyclerAdapterModel model);
    }

    interface pageAdapterView {
        void refresh();
    }

    interface pageAdapterModel {
        void addFragments(List<Fragment> page);
    }

    interface recyclerAdapterView{
        void refresh();
    }

    interface recyclerAdapterModel{
        void addItems(List<UserStudies> studies);
    }
}
