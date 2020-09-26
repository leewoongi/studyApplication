package com.woon.wisestudytest1.user.user.presenter;

import com.woon.wisestudytest1.user.Entity.UserStudies;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.user.contract.UserAdapterContract;
import com.woon.wisestudytest1.user.user.contract.UserContract;
import com.woon.wisestudytest1.user.user.model.RemoteUserModel;
import com.woon.wisestudytest1.user.user.view.UserStudiesAdapter;
import com.woon.wisestudytest1.util.SuccessCallback;

import java.util.List;

public class UserPresenter implements UserContract.presenter, SuccessCallback {

    private UserContract.view view;
    private UserContract.remoteModel remoteModel;
    private UserStudiesAdapter adapter;

    public UserPresenter(UserContract.view view, UserStudiesAdapter adapter) {
        this.view = view;
        remoteModel = new RemoteUserModel(this, this);
        this.adapter = adapter;
    }



    @Override
    public void retrieveInformation(String userKey) {
        remoteModel.getUserInformation(userKey);
    }

    @Override
    public void responseUserInformation(UserVo userVo){
        // 나머지는 view
        view.showInformation(userVo);
    }

    @Override
    public void loadItems(UserVo userVo) {
        List<UserStudies> studies = userVo.getStudy_list();
        adapter.addItems(studies);
        adapter.refresh();
    }

    @Override
    public void onSuccess() {

    }
}
