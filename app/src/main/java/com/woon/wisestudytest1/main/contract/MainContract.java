package com.woon.wisestudytest1.main.contract;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.woon.wisestudytest1.main.Entity.StudyVo;
import com.woon.wisestudytest1.user.Entity.UserStudies;
import com.woon.wisestudytest1.user.Entity.UserVo;

import java.util.List;

import okhttp3.MultipartBody;

public interface MainContract {

    interface view{

    }

    interface fragmentView{
        void initialized();
        void showFragment(Object data);
    }

    interface presenter{

        //유저 정보 가져오기
        void retrieveInformation(String userKey);
        void responseUserInformation(UserVo userVo);

        void loadFragment(Bundle bundle, String userKey);
        void loadItems(UserVo userVo);

        // 스터디 생성
        void registerStudyInformation(Activity activity, String userKey, Uri uri, String category, String title, Integer limit, String description);


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
