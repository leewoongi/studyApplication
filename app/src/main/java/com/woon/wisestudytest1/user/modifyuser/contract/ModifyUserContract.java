package com.woon.wisestudytest1.user.modifyuser.contract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

public interface ModifyUserContract {
    interface view{
        void showInformation();
        void showImage(Bitmap img);
    }

    interface presenter{
        String getJwt(Context context);
        void changeImage(Activity activity, Intent intent);
        void requestModifyUser(String userKey);
    }

    interface remoteModel{
        void uploadImage(Activity activity, Uri uri);
        void putUserInformation(String userKey);
    }

    interface localModel{
        void getPicture();
    }

}
