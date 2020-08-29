package com.woon.wisestudytest1.user.modifyuser.model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.woon.wisestudytest1.login.entity.LoginVo;
import com.woon.wisestudytest1.network.ApiClient;
import com.woon.wisestudytest1.network.ApiResponse;
import com.woon.wisestudytest1.user.Entity.UserVo;
import com.woon.wisestudytest1.user.modifyuser.contract.ModifyUserContract;
import com.woon.wisestudytest1.user.networking.UserApiInterface;
import retrofit2.Call;

public class RemoteModifyUserModel implements ModifyUserContract.remoteModel {


    @Override
    public void putUserInformation(String userKey) {

    }
}
