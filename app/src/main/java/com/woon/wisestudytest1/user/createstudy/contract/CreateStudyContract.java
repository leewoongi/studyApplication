package com.woon.wisestudytest1.user.createstudy.contract;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.woon.wisestudytest1.user.Entity.StudyVo;

import okhttp3.MultipartBody;

public interface CreateStudyContract {
    interface view{

        void upLoadInformation();
        void nextActivity();

    };

    interface presenter {

        String getJwt(Context context);
        MultipartBody.Part registerImage(Activity activity, String userKey, Uri uri);
        void registerStudyInformation(String userKey, StudyVo studyVo);
    };

    interface remoteModel{
        void requestStudyInformation(String userKey, StudyVo studyVo);
    }

    interface localModel{
        MultipartBody.Part getPicture(Activity activity, String userKey, Uri uri);
    }

}
