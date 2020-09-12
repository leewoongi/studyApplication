package com.woon.wisestudytest1.user.user.contract;

import com.woon.wisestudytest1.user.Entity.UserVo;

public interface UserContract {
    interface view{
        void nextActivity();
        void showInformation(UserVo userVo);
        void showJoinedStudy();
    }

    interface presenter{
        //유저 정보 가져오기
        void retrieveInformation(String userKey);
        void responseUserInformation(UserVo userVo);

        void setImageAdapterView(UserAdapterContract.adapterView adapterView);
        void setImageAdapterModel(UserAdapterContract.adapterModel adapterModel);
    }

    interface remoteModel{
        // 유저 정보 서버에서 가져오기
        void getUserInformation(String userKey);
    }

    interface localModel{
        void getPicture();
    }
}
