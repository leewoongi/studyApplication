package com.woon.wisestudytest1.user.modifyuser.contract;

import android.content.Context;

import com.woon.wisestudytest1.user.Entity.UserVo;

public interface ModifyUserContract {
    interface view{
        void showInformation(UserVo item);
    }

    interface presenter{
        String getJwt(Context context);
        void updateUserInformation(String userKey);
        void responseUserInformation(UserVo userVo);

        void requestModifyUser(String userKey);
    }

    interface remoteModel{

        void getUserInformation(String userKey);
        void putUserInformation(String userKey);
    }

    interface localModel{
        void getPicture();
    }

}
