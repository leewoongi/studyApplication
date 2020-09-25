package com.woon.wisestudytest1.user.applystudy.contract;

import android.net.Uri;

import com.woon.wisestudytest1.user.Entity.DetailStudyVo;

public interface ApplyStudyContract {

    interface view{
        void showInformation(Uri uri, String name, String des, int member);
        void nextActivity();
    }

    interface presenter{
        void retrieveStudyInformation(int studyId);
        void responseStudyInformation(DetailStudyVo detailStudyVo);

        void registerApplyMember(int studyId, String userKey);
        void responseApplyMember();
    }
}
