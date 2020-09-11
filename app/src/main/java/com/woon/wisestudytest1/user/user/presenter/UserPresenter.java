package com.woon.wisestudytest1.user.user.presenter;

import com.woon.wisestudytest1.user.Entity.UserStudies;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.user.contract.UserAdapterContract;
import com.woon.wisestudytest1.user.user.contract.UserContract;
import com.woon.wisestudytest1.user.user.model.RemoteUserModel;
import com.woon.wisestudytest1.util.SuccessCallback;

import java.util.List;

public class UserPresenter implements UserContract.presenter, SuccessCallback {

    private UserContract.view view;
    private UserContract.remoteModel remoteModel;
    private UserAdapterContract.adapterView adapterView;
    private UserAdapterContract.adapterModel adapterModel;

    public UserPresenter(UserContract.view view) {
        this.view = view;
        remoteModel = new RemoteUserModel(this, this);
    }

    @Override
    public void retrieveInformation(String userKey) {
        remoteModel.getUserInformation(userKey);
    }

    @Override
    public void responseUserInformation(UserVo userVo) {
        // 스터디 목록은 adapter로
        List<UserStudies> studies = userVo.getStudy_list();

        // 나머지는 view
        view.showInformation(userVo);
    }

    @Override
    public void onSuccess() {

    }
}
