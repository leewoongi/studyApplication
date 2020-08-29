package com.woon.wisestudytest1.user.modifyuser.contract;

import android.content.Context;

public interface ModifyUserContract {
    interface view{
        void showInformation();
        void showImage();
    }

    interface presenter{
        String getJwt(Context context);
        void changeImage();
        void requestModifyUser(String userKey);
    }

    interface remoteModel{
        void putUserInformation(String userKey);
    }

    interface localModel{
        void getPicture();
    }

}
