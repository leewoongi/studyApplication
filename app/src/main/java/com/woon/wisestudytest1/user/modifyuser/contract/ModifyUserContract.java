package com.woon.wisestudytest1.user.modifyuser.contract;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import com.woon.wisestudytest1.user.Entity.UpdateUserVo;
import com.woon.wisestudytest1.user.Entity.UserVo;

public interface ModifyUserContract  {
    interface view{
        //서버에서 받아온 데이터 뿌리기
        void showInformation(UserVo item);
        // 액티비티 이동
        void nextActivity();
    }

    interface presenter{
        String getJwt(Context context);

        //유저정보 받기
        void retrieveUserInformation(String userKey);

         //이미지 보내기
        void upLoadImage(Activity activity,String userKey, Uri uri);

        // 유저 업데이트할 유저정보 보내기
        void updateUserInformation(String userKey, UpdateUserVo updateUserVo);

        //원격모델에서 업데이트할 데이터 받아오기
        void responseUserInformation(UserVo userVo);
    }

    interface remoteModel{

        //서버에서 유저 정보 받아오기
        void getUserInformation(String userKey);

        //서버에 유저 정보 보내기
        void patchUserInformation(String userKey, UpdateUserVo updateUserVo);
    }

    interface localModel{
        // 갤러리에서 사진 가져오기
        void postPicture(Activity activity, String userKey, Uri uri);
    }

}
