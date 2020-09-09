package com.woon.wisestudytest1.user.createstudy.model;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.woon.wisestudytest1.user.createstudy.contract.CreateStudyContract;
import com.woon.wisestudytest1.util.SuccessCallback;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LocalModelCreateStudy implements CreateStudyContract.localModel {

    private CreateStudyContract.presenter presenter;
    public LocalModelCreateStudy(CreateStudyContract.presenter presenter, SuccessCallback successCallback) {

    }

    @Override
    public MultipartBody.Part getPicture(Activity activity, String userKey, Uri uri) {

        String realPath = getPathFromUri(activity, uri);
        File file = new File(realPath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("study_image", file.getName(), requestFile);

        return body;
    }

    private String getPathFromUri(Activity activity, Uri uri) {
        Cursor cursor = activity.getContentResolver().query(uri,null, null, null);
        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));

        return path;
    }
}
